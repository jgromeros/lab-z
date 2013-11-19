package lab.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.exceptions.LabcaseException;

import org.apache.log4j.Logger;
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
            String[] selectedTests = request.getParameterValues("selected");
        }
        logger.debug(logger.getName() + ": perform finished successfully");
		return getModel();
	}

}
