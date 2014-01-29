package lab.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.exceptions.LabcaseException;
import lab.model.persistence.HibernateUtil;
import lab.web.action.Action;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Servlet implementation class LabzController
 */
public abstract class LabzController extends HttpServlet {

    private static Logger logger = Logger.getLogger(LabzController.class);

	private static final long serialVersionUID = 1L;
	protected Map<String, Action> actions;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabzController() {
        super();
        actions = new HashMap<String, Action>();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public abstract void init() throws ServletException;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> model = processRequest(request, response);
		request.setAttribute("model", model);
		if ((model != null) && (model.containsKey("report"))){
			showReport(response, (byte[]) model.get("report"));
		} else {
			dispatchView(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> model = processRequest(request, response);
		request.setAttribute("model", model);
		if (model.containsKey("report")){
			showReport(response, (byte[]) model.get("report"));
		} else {
			dispatchView(request, response);
		}
	}

	private Map<String, Object> processRequest(HttpServletRequest request, HttpServletResponse response) {
		String urlToCompare = request.getRequestURI().substring(request.getContextPath().length() + 1, request.getRequestURI().length() - 4);
		String actionString = decomposeURL(urlToCompare);
		Action action = getActions().get(actionString);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Map<String, Object> model = null;
		try{
			model = action.perform(request, response, session, tx);
			tx.commit();
		} catch (LabcaseException e){
		    logger.error(logger.getName() + ": Exception occured on lab stuff.");
            logger.error(logger.getName() + ": " + e.getMessage());
			tx.rollback();
			model = new HashMap<String, Object>();
			model.put("errores", e.getMessage());
		}
		session.close();
		return model;
	}

	private String decomposeURL(String URI){
		for (String s : getActions().keySet()){
			if (URI.equals(s))
				return s;
		}
		return null;
	}

	private void dispatchView(HttpServletRequest request, HttpServletResponse response){
		ServletContext sc = this.getServletContext();
		String urlToCompare = request.getRequestURI().substring(request.getContextPath().length() + 1, request.getRequestURI().length() - 4);
		String actionString = decomposeURL(urlToCompare);
		Action action = getActions().get(actionString);
		RequestDispatcher rd = sc.getRequestDispatcher("/" + action.nameToString() + ".jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			logger.error(e.getStackTrace());
		} catch (IOException e) {
		    logger.error(e.getStackTrace());
		}
	}

	private void showReport(HttpServletResponse response, byte[] bytes) {
		try {
		    response.setContentType("application/pdf");
	        response.setContentLength(bytes.length);
		    ServletOutputStream ouputStream = response.getOutputStream();
		    ouputStream.write(bytes, 0, bytes.length);
		    ouputStream.flush();
		    ouputStream.close();
		} catch (IOException e) {
		    logger.error(e.getStackTrace());
	    }
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(Map<String, Action> actions) {
		this.actions = actions;
	}

	/**
	 * @return the actions
	 */
	public Map<String, Action> getActions() {
		return actions;
	}

}
