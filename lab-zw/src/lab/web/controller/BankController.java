/**
 * 
 */
package lab.web.controller;

import javax.servlet.ServletException;

import lab.web.action.BankAction;

/**
 * @author juanromero
 *
 */
public class BankController extends LabzController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bankActionPath = "bank";

	@Override
	public void init() throws ServletException {
		BankAction loadCasesAction = new BankAction(bankActionPath, BankAction.LABCASES);
		actions.put(loadCasesAction.nameToString(), loadCasesAction);
		BankAction saveInBankAction = new BankAction(bankActionPath, BankAction.SAVE);
		actions.put(saveInBankAction.nameToString(), saveInBankAction);
		BankAction listBankAction = new BankAction(bankActionPath, BankAction.LIST);
		actions.put(listBankAction.nameToString(), listBankAction);
		BankAction formBankAction = new BankAction(bankActionPath, BankAction.BANKFORM);
		actions.put(formBankAction.nameToString(), formBankAction);
		BankAction saveBankAction = new BankAction(bankActionPath, BankAction.SAVEBANK);
		actions.put(saveBankAction.nameToString(), saveBankAction);
		BankAction viewBankAction = new BankAction(bankActionPath, BankAction.VIEWBANK);
		actions.put(viewBankAction.nameToString(), viewBankAction);
		
	}

}
