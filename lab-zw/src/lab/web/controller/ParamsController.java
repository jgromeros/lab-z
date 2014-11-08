package lab.web.controller;

import javax.servlet.ServletException;

import lab.web.action.BillingAction;
import lab.web.action.EnterpriseAction;
import lab.web.action.PrintBillAction;

/**
 * The task of this controller is to allow the creation/edition/deletion of
 * several parameters from the application. This is, the CRUD operations of
 * things like clients, species, races, tests and may be even test parameters
 * and reference values.
 * @author juano
 */
public class ParamsController extends LabzController {

	private static String ENTERPRISE_ACTION_PATH = "admin"; 
	private static String BILL_ACTION_PATH = "admin/bill"; 

	@Override
	public void init() throws ServletException {
		EnterpriseAction enterpriseAction = new EnterpriseAction(ENTERPRISE_ACTION_PATH,
				EnterpriseAction.ENTERPRISE);
		actions.put(enterpriseAction.nameToString(), enterpriseAction);
		EnterpriseAction listEnterpriseAction = new EnterpriseAction(ENTERPRISE_ACTION_PATH,
				EnterpriseAction.LIST);
		actions.put(listEnterpriseAction.nameToString(), listEnterpriseAction);
		BillingAction selectBillingAction = new BillingAction(BILL_ACTION_PATH,
				BillingAction.SELECT);
		actions.put(selectBillingAction.nameToString(), selectBillingAction);
        BillingAction saveBillAction = new BillingAction(BILL_ACTION_PATH,
                BillingAction.SAVE);
        actions.put(saveBillAction.nameToString(), saveBillAction);
        PrintBillAction printBillAction = new PrintBillAction(BILL_ACTION_PATH,
                BillingAction.PRINT, this);
        actions.put(printBillAction.nameToString(), printBillAction);
	}

}
