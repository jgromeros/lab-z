package lab.web.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.dto.BillDetailDto;
import lab.model.animal.Animal;
import lab.model.labcase.Labcase;
import lab.model.persistence.HibernateUtil;
import lab.model.test.Test;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

public class BillingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enterprise = request.getParameter("enterprise");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        String beginString = request.getParameter("begin");
        String endString = request.getParameter("end");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query hql = session.createQuery("from Labcase l where l.enterpriseSender.id = " +
        		":enterprise and l.receptionDate between :begin and :end");
        hql.setParameter("enterprise", Long.parseLong(enterprise));
        try {
	        hql.setParameter("begin", df.parse(beginString));
	        hql.setParameter("end", df.parse(endString));
        } catch (HibernateException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<BillDetailDto> billDetails = new ArrayList<BillDetailDto>();
        for (Labcase labcase: (List<Labcase>)hql.list()){
            for (Animal animal : labcase.getAnimals()){
                for (Test test : animal.getTests()){
                    if (!Test.CANCELLED.equals(test.getStatus())){
                        BillDetailDto billDetail = new BillDetailDto();
                        billDetail.setLabcaseCode(labcase.getCode());
                        billDetail.setComment(labcase.getAnalysisPurpose());
                        billDetail.setReceptionDate(df.format(labcase.getReceptionDate()));
                        billDetail.setPatientName(animal.getName());
                        billDetail.setTestId(test.getId());
                        billDetail.setTestDescription(test.getTestDescription().getDescription());
                        billDetail.setPrice(test.getTestDescription().currentPrice());
                        billDetails.add(billDetail);
                    }
                }
            }
        }
        tx.commit();
        sendJsonResponse(response, billDetails);
    }

    private void sendJsonResponse(HttpServletResponse response, Object object) throws IOException {
        String json = null ;
        json = new Gson().toJson(object);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);    	
    }

}
