package lab.web.controller;

import javax.servlet.ServletException;

import lab.web.action.EnterpriseAction;

/**
 * The task of this controller is to allow the creation/edition/deletion of
 * several parameters from the application. This is, the CRUD operations of
 * things like clients, species, races, tests and may be even test parameters
 * and reference values.
 * @author juano
 */
public class ParamsController extends LabzController {

	private String enterpriseActionPath = "admin"; 

	@Override
	public void init() throws ServletException {
		EnterpriseAction enterpriseAction = new EnterpriseAction(enterpriseActionPath,
				EnterpriseAction.ENTERPRISE);
		actions.put(enterpriseAction.nameToString(), enterpriseAction);
		EnterpriseAction listEnterpriseAction = new EnterpriseAction(enterpriseActionPath,
				EnterpriseAction.LIST);
		actions.put(listEnterpriseAction.nameToString(), listEnterpriseAction);
	}

}
