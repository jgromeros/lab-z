package lab.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.model.enterprise.Enterprise;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Deals with the persistence operations of Enterprise 
 * @author juano
 */
public class EnterpriseAction extends Action {

	public static final String LIST = "enterprises";
	public static final String ENTERPRISE = "enterprise";

	public EnterpriseAction(String actionPath, String action) {
		super(actionPath, action);
	}

	@Override
	public Map<String, Object> perform(HttpServletRequest request,
			HttpServletResponse response, Session session, Transaction tx) {
		if (LIST.equals(getAction())){
			if (request.getParameter("idnumber") != null){
				Enterprise enterprise = new Enterprise();
				enterprise.setIdentityNumber(Long.parseLong(request.getParameter("idnumber")));
				enterprise.setName(request.getParameter("name"));
				enterprise.setAddress(request.getParameter("address"));
				enterprise.setPhone(request.getParameter("phone"));
				enterprise.setEmail(request.getParameter("email"));
				session.saveOrUpdate(enterprise);
			}
			getModel().put("enterprises", session.
					createQuery("from Enterprise e order by e.lastName, e.name").list());
		}
		return getModel();
	}

}
