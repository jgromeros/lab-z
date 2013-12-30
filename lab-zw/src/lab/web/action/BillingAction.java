package lab.web.action;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.exceptions.LabcaseException;
import lab.model.bill.Bill;
import lab.model.bill.BillDetail;
import lab.model.enterprise.Enterprise;
import lab.model.test.Test;
import lab.model.test.TestProfile;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Deals with the persistence operations of Enterprise 
 * @author juano
 */
public class BillingAction extends Action {

    private static Logger logger = Logger.getLogger(BillingAction.class);

    public static final String SELECT = "selecting";
    public static final String SAVE = "billed";

	public BillingAction(String actionPath, String action) {
		super(actionPath, action);
	}

	@Override
	public Map<String, Object> perform(HttpServletRequest request,
			HttpServletResponse response, Session session, Transaction tx)
			throws LabcaseException {
        logger.debug(logger.getName() + ": perform with the following params:");
        for (String paramName : request.getParameterMap().keySet()){
            logger.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        if (request.getRequestURI().contains(SELECT)){
    		getModel().put("enterprises", session.
    				createQuery("from Enterprise e order by e.lastName, e.name").list());
        } else if (request.getRequestURI().contains(SAVE)){
            Bill bill = createNewBill(session, request);
            session.save(bill);
        }
        logger.debug(logger.getName() + ": perform finished successfully");
		return getModel();
	}

	/**
	 * Creates a new bill from the data in the request. The price is set as selected by the user.
	 * i.e. if the user sends a new value, then that value is used. Otherwise the value in the
	 * TestDescription is used. The tax value for the detail is computed multiplying this price
	 * and the tax from TestDescription
	 * @param session
	 * @param request
	 */
    private Bill createNewBill(Session session, HttpServletRequest request) {
        Enterprise client = (Enterprise) session.get(Enterprise.class,
                Long.valueOf(request.getParameter("enterprise")));
        String[] selected = request.getParameterValues("selected");
        Bill bill = new Bill(client);
        for (String selectedIter : selected){
            BillDetail billDetail = new BillDetail();
            billDetail.setBill(bill);
            String priceString = request.getParameter("price" + selectedIter);
            String[] selectedSplitted = selectedIter.split("&");
            if (!selectedSplitted[0].equals("undefined")){
                Test test = (Test) session.get(Test.class, Long.valueOf(selectedSplitted[0]));
                billDetail.setTest(test);
                if (StringUtils.isNotBlank(priceString) && StringUtils.isNumeric(priceString)) {
                    billDetail.setPrice(new BigDecimal(request.getParameter("price" + selectedIter)));
                } else {
                    billDetail.setPrice(test.getTestDescription().currentPrice().getPrice());
                }
            } else {
                TestProfile testProfile = (TestProfile) session.get(TestProfile.class,
                        Long.valueOf(selectedSplitted[1]));
                billDetail.setTestProfile(testProfile);
                if (StringUtils.isNotBlank(priceString) && StringUtils.isNumeric(priceString)) {
                    billDetail.setPrice(new BigDecimal(request.getParameter("price" + selectedIter)));
                } else {
                    billDetail.setPrice(testProfile.getProfile().currentPrice().getPrice());
                }
            }
            bill.getBilledDetails().add(billDetail);
        }
        bill.computeTotalBeforeTaxes();
        bill.computeTotalAfterTaxes();
        bill.setBillNumber(getNewBillNumber(session));
        return bill;
    }

    /**
     * Returns the next number for a bill. It is executing a transactional function that is
     * written in the database. The parameter of the function refers to the specific
     * folio number
     * @param session
     * @return
     */
    private Integer getNewBillNumber(Session session) {
        Query query = session.createSQLQuery("select numero_foliado(2);");
        Integer newBillNumber = (Integer) query.uniqueResult();
        return newBillNumber;
    }

}
