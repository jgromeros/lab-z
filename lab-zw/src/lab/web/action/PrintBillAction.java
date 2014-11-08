/**
 * 
 */
package lab.web.action;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.controller.LabzController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.SessionFactoryImplementor;

/**
 * @author juanromero
 *
 */
public class PrintBillAction extends Action {

    private static Logger logger = Logger.getLogger(PrintBillAction.class);

	private LabzController controller;

	public PrintBillAction(String actionPath, String action) {
		super(actionPath, action);
	}

	public PrintBillAction(String actionPath, String action, LabzController controller) {
		super(actionPath, action);
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest, org.hibernate.Session, org.hibernate.Transaction)
	 */
	@Override
	public Map<String, Object> perform(HttpServletRequest request, HttpServletResponse response,
			Session session, Transaction tx) {
        logger.debug(logger.getName() + ": perform with the following params:");
        for (String paramName : request.getParameterMap().keySet()){
            logger.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        Integer billNumber = new Integer(request.getParameter("number"));
        cleanModel();
		if (controller != null){
			byte[] bytes = null;
			String nombreReporte = "reports/bill.jasper";
			File reportFile = new File(controller.getServletContext().getRealPath(nombreReporte));
		    Map<String,Object> parameters = new HashMap<String,Object>();
		    parameters.put("billNumber", billNumber);
		    try {
			    Connection con = ((SessionFactoryImplementor)session.getSessionFactory()).getConnectionProvider().getConnection();
			    bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, con);
				getModel().put("report", bytes);
			} catch (JRException e) {
                logger.error(logger.getName() + ": " + e.getLocalizedMessage());
                for (StackTraceElement ste : e.getStackTrace()){
                    logger.error(ste);
                }
			} catch (SQLException e) {
			    logger.error(logger.getName() + ": " + e.getLocalizedMessage());
                for (StackTraceElement ste : e.getStackTrace()){
                    logger.error(ste);
                }
			}
		}
        logger.debug(logger.getName() + ": perform finished successfully");
		return getModel();
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(LabzController controller) {
		this.controller = controller;
	}

	/**
	 * @return the controller
	 */
	public LabzController getController() {
		return controller;
	}

}
