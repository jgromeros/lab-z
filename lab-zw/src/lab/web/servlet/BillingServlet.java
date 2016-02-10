package lab.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.dto.BillDetailDto;
import lab.exceptions.LabcaseException;
import lab.model.animal.Animal;
import lab.model.bill.Bill;
import lab.model.bill.BillDetail;
import lab.model.labcase.Labcase;
import lab.model.persistence.HibernateUtil;
import lab.model.test.Test;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

public class BillingServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(BillingServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enterprise = request.getParameter("enterprise");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String beginString = request.getParameter("begin");
        String endString = request.getParameter("end");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query hql = session.createQuery("from Labcase l where l.enterpriseSender.id = " +
        		":enterprise and l.receptionDate between :begin and :end order by l.id");
        hql.setParameter("enterprise", Long.parseLong(enterprise));
        try {
	        hql.setParameter("begin", df.parse(beginString));
	        hql.setParameter("end", df.parse(endString));
        } catch (HibernateException e) {
            logger.error(e.getStackTrace());
        } catch (ParseException e) {
            logger.error(e.getStackTrace());
        }
        Set<BillDetailDto> billDetails = new LinkedHashSet<BillDetailDto>();
        for (Labcase labcase: (List<Labcase>)hql.list()){
            mapBillDetails(session, billDetails, labcase, df);
        }
        if (!billDetails.isEmpty()) {
            request.getSession().setAttribute("billingClient", enterprise);
        }
        tx.commit();
        sendJsonResponse(response, billDetails);
    }

    /**
     * 
     * @param response
     * @param object
     * @throws IOException
     */
    private void sendJsonResponse(HttpServletResponse response, Object object) throws IOException {
        String json = null ;
        json = new Gson().toJson(object);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);    	
    }

    /**
     * Retorna un arreglo de detalles para factura. Los valores los toma del labcase
     * @param billDetails
     * @param labcase
     * @param df
     */
    private void mapBillDetails(Session session, Set<BillDetailDto> billDetails,
            Labcase labcase, DateFormat df) {
        for (Animal animal : labcase.getAnimals()){
            for (Test test : animal.getTests()){
                if (!Test.CANCELLED.equals(test.getStatus())){
                    Query query = session.createQuery("from BillDetail bd where (bd.test = :test" +
                    		" or bd.testProfile = :testProfile)" +
                    		" and bd.bill.status != :cancelledStatus");
                    query.setParameter("test", test);
                    query.setParameter("testProfile", test.getTestProfile());
                    query.setParameter("cancelledStatus", Bill.CANCELLED);
                    BillDetail detail = (BillDetail) query.uniqueResult();
                    if (detail == null){
                        if (test.getTestProfile() == null){
                            BillDetailDto billDetail = new BillDetailDto();
                            billDetail.setLabcaseCode(labcase.getCode());
                            billDetail.setComment(labcase.getAnalysisPurpose());
                            billDetail.setReceptionDate(df.format(labcase.getReceptionDate()));
                            billDetail.setPatientName(animal.getName());
                            billDetail.setTestId(test.getId());
                            billDetail.setTestDescription(test.getTestDescription().getDescription());
                            try {
                                billDetail.setPrice(test.getTestDescription().currentPrice().getPrice());                                
                            } catch (LabcaseException e) {
                                logger.warn(e);
                                billDetail.setPrice(new BigDecimal("0"));
                            }
                            billDetails.add(billDetail);
                        } else {
                            BillDetailDto billDetail = new BillDetailDto();
                            billDetail.setLabcaseCode(labcase.getCode());
                            billDetail.setComment(labcase.getAnalysisPurpose());
                            billDetail.setReceptionDate(df.format(labcase.getReceptionDate()));
                            billDetail.setPatientName(animal.getName());
                            billDetail.setTestProfile(test.getTestProfile().getId());
                            billDetail.setTestDescription(
                                    test.getTestProfile().getProfile().getDescription());
                            try {
                                billDetail.setPrice(
                                        test.getTestProfile().getProfile().currentPrice().getPrice());
                            } catch (LabcaseException e) {
                                logger.warn(e);
                                billDetail.setPrice(new BigDecimal("0"));
                            }
                            billDetails.add(billDetail);
                        }
                    }
                }
            }
        }
    }

}
