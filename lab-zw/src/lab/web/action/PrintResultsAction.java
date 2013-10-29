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

import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.TestDescription;
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
public class PrintResultsAction extends Action {

    private static Logger logger = Logger.getLogger(PrintResultsAction.class);

    public static final String FORM = "printresults";
	private LabzController controller;
	private Map<String, String> reports;

	public PrintResultsAction(String actionPath, String action) {
		super(actionPath, action);
		initializeReports();
	}

	public PrintResultsAction(String actionPath, String action, LabzController controller) {
		super(actionPath, action);
		this.controller = controller;
		initializeReports();
	}

	private void initializeReports() {
		reports = new HashMap<String, String>();
		reports.put("" + 28, "reports/res_bru_ofi.jasper");
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
		Labcase labcase = (Labcase) session.get(Labcase.class,
				Long.parseLong(request.getParameter("id")));
		Test test = (Test) session.get(Test.class, Long.parseLong(request.getParameter("test")));
		if (labcase.getLabProfessionalForTest(test) == null ||
				labcase.getTechnicalDirector() == null){
			setAction(FORM);
			//TODO Aqui hay que hacer algo
		} else {
//			setAction("mockpage");
			cleanModel();
			if (controller != null){
				byte[] bytes = null;
				String nombreReporte = obtenerNombreReporte(test);
				File reportFile = new File(controller.getServletContext().getRealPath(nombreReporte));
//				File reportFile = new File(controller.getServletContext().getRealPath("reports/res_bru_ofi.jasper"));
			    Map<String,Object> parameters = new HashMap<String,Object>();
			    parameters.put("labcase", labcase.getId());
			    parameters.put("testNumber", test.getId());
			    try {
				    Connection con = ((SessionFactoryImplementor)session.getSessionFactory()).getConnectionProvider().getConnection();
				    bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, con);
//					bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, session.connection());
					getModel().put("report", bytes);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        logger.debug(logger.getName() + ": perform finished successfully");
		return getModel();
	}

	private String obtenerNombreReporte(Test test) {
		if (getReports().get(test.getTestDescription().getId().toString()) == null){
			if (test.getTestDescription().getId() == 57){//Cuadro hematico
				return "reports/results-cbc.jasper";
			}
			return "reports/results.jasper";
		} else
		return getReports().get(test.getTestDescription().getId().toString());
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

	/**
	 * @param reports the reports to set
	 */
	public void setReports(Map<String, String> reports) {
		this.reports = reports;
	}

	/**
	 * @return the reports
	 */
	public Map<String, String> getReports() {
		return reports;
	}

}
