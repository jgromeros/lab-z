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
public class EnterpriseAction extends Action {

    private static Logger logger = Logger.getLogger(EnterpriseAction.class);

    public static final String LIST = "enterprises";
	public static final String ENTERPRISE = "enterprise";
	public static final String LIST_URL = "/lab-zw/admin/enterprises.htm";

	public EnterpriseAction(String actionPath, String action) {
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
		if (LIST_URL.equals(request.getRequestURI())){
			if (request.getParameter("idnumber") != null &&
					!request.getParameter("idnumber").isEmpty()){
				Enterprise enterprise = new Enterprise();
				enterprise.setIdentityNumber(request.getParameter("idnumber").isEmpty() ? null :
				        Long.parseLong(request.getParameter("idnumber")));
				enterprise.setName(request.getParameter("name"));
				enterprise.setAddress(request.getParameter("address"));
				enterprise.setCity(request.getParameter("city"));
				enterprise.setPhone(request.getParameter("phone"));
				enterprise.setEmail(request.getParameter("email"));
				enterprise.setId(request.getParameter("id").isEmpty() ? null :
				        Long.parseLong(request.getParameter("id")));
				session.saveOrUpdate(enterprise);
			}
			try {
				getModel().put("enterprises", session.
						createQuery("from Enterprise e order by e.lastName, e.name").list());
				this.setAction(LIST);
			} catch (ConstraintViolationException e){
				this.setAction(ENTERPRISE);
				throw new LabcaseException("No se pudo crear la entidad. Parece que ya existe.");
			}
		} else if (request.getParameter("id") != null) {
		    Enterprise enterprise = (Enterprise) session.get(Enterprise.class,
		            Long.parseLong(request.getParameter("id")));
		    getModel().put("enterprise", enterprise);
		} else {
		    getModel().remove("enterprise");
		}
        logger.debug(logger.getName() + ": perform finished successfully");
		return getModel();
	}

}
