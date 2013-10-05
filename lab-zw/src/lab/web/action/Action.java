/**
 * 
 */
package lab.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author juanromero
 *
 */
public abstract class Action {

	private String action;
	private String actionPath;
	private Map<String, Object> model;

	public abstract Map<String, Object> perform(HttpServletRequest request, HttpServletResponse response,
			Session session, Transaction tx);

	/**
	 * 
	 * @param actionPath
	 * @param action
	 */
	public Action(String actionPath, String action){
		this.actionPath = actionPath;
		this.action = action;
		this.model = new HashMap<String, Object>();
	}

	/**
	 * 
	 * @return
	 */
	public String nameToString(){
		return this.getActionPath() + "/" + this.getAction();
	}

	public void cleanModel() {
		getModel().keySet().removeAll(getModel().keySet());
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param actionPath the actionPath to set
	 */
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	/**
	 * @return the actionPath
	 */
	public String getActionPath() {
		return actionPath;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	/**
	 * @return the model
	 */
	public Map<String, Object> getModel() {
		return model;
	}

}
