package lab.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.exceptions.LabcaseException;
import lab.model.enterprise.Enterprise;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Deals with the persistence operations of Enterprise 
 * @author juano
 */
public class TestDefinitionsAction extends Action {

    private static Logger logger = Logger.getLogger(TestDefinitionsAction.class);

    public static final String PRICE_LIST = "enterprises";
	public static final String PRICE_EDIT = "enterprise";

	public TestDefinitionsAction(String actionPath, String action) {
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
        logger.debug(logger.getName() + ": perform finished successfully");
		return getModel();
	}

}
