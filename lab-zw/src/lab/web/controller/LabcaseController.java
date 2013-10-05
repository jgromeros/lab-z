/**
 * 
 */
package lab.web.controller;

import javax.servlet.ServletException;

import lab.web.action.AssemblyAction;
import lab.web.action.LabcaseAction;
import lab.web.action.LoadAnimalsForCaseAction;
import lab.web.action.LoadedResultsAction;
import lab.web.action.PrintResultsAction;
import lab.web.action.TypedResultsAction;

/**
 * @author juanromero
 *
 */
public class LabcaseController extends LabzController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String labcaseActionPath = "labcase"; 
	private String assemblyActionPath = "assembly";
	private String resultsActionPath = "results";

	@Override
	public void init() throws ServletException {
		LabcaseAction newLabcaseAction = new LabcaseAction(labcaseActionPath, LabcaseAction.FORM1);
		actions.put(newLabcaseAction.nameToString(), newLabcaseAction);
		LabcaseAction page2LabcaseAction = new LabcaseAction(
				labcaseActionPath, LabcaseAction.FORM2);
		actions.put(page2LabcaseAction.nameToString(), page2LabcaseAction);
		LabcaseAction page3LabcaseAction = new LabcaseAction(
				labcaseActionPath, LabcaseAction.FORM3);
		actions.put(page3LabcaseAction.nameToString(), page3LabcaseAction);
		LabcaseAction saveLabcaseAction = new LabcaseAction(labcaseActionPath, LabcaseAction.DONE);
		actions.put(saveLabcaseAction.nameToString(), saveLabcaseAction);
		LabcaseAction searchLabcaseAction = new LabcaseAction(
				labcaseActionPath, LabcaseAction.SEARCH);
		actions.put(searchLabcaseAction.nameToString(), searchLabcaseAction);
		LabcaseAction foundLabcaseAction = new LabcaseAction(
				labcaseActionPath, LabcaseAction.FOUND);
		actions.put(foundLabcaseAction.nameToString(), foundLabcaseAction);
		AssemblyAction page1AssemblyAction = new AssemblyAction(
				assemblyActionPath, AssemblyAction.TESTDESCRIPTIONS);
		actions.put(page1AssemblyAction.nameToString(), page1AssemblyAction);
		AssemblyAction page2AssemblyAction = new AssemblyAction(
				assemblyActionPath, AssemblyAction.SELECTCASES);
		actions.put(page2AssemblyAction.nameToString(), page2AssemblyAction);
		AssemblyAction page3AssemblyAction = new AssemblyAction(
				assemblyActionPath, AssemblyAction.NEWASSEMBLY);
		actions.put(page3AssemblyAction.nameToString(), page3AssemblyAction);
		TypedResultsAction page1TypedResultsAction = new TypedResultsAction(resultsActionPath,
				TypedResultsAction.LISTPENDINGCASES);
		actions.put(page1TypedResultsAction.nameToString(), page1TypedResultsAction);
		TypedResultsAction page2TypedResultsAction = new TypedResultsAction(resultsActionPath,
				TypedResultsAction.CASESELECTED);
		actions.put(page2TypedResultsAction.nameToString(), page2TypedResultsAction);
		TypedResultsAction page3TypedResultsAction = new TypedResultsAction(resultsActionPath,
				TypedResultsAction.TESTSELECTED);
		actions.put(page3TypedResultsAction.nameToString(), page3TypedResultsAction);
		TypedResultsAction page4TypedResultsAction = new TypedResultsAction(resultsActionPath,
				TypedResultsAction.REGISTERED);
		actions.put(page4TypedResultsAction.nameToString(), page4TypedResultsAction);
		LoadedResultsAction listAssembliesAction = new LoadedResultsAction(resultsActionPath,
				LoadedResultsAction.LISTASSEMBLIES);
		actions.put(listAssembliesAction.nameToString(), listAssembliesAction);
		LoadedResultsAction loadFileAction = new LoadedResultsAction(resultsActionPath,
				LoadedResultsAction.LOADFILE);
		actions.put(loadFileAction.nameToString(), loadFileAction);
		LoadedResultsAction loadResultsAction = new LoadedResultsAction(resultsActionPath,
				LoadedResultsAction.LOADRESULTS);
		actions.put(loadResultsAction.nameToString(), loadResultsAction);
		PrintResultsAction formResultPrintingAction = new PrintResultsAction(resultsActionPath,
				PrintResultsAction.FORM, this);
		actions.put(formResultPrintingAction.nameToString(), formResultPrintingAction);
		LoadAnimalsForCaseAction loadAnimalsForCaseAction = new LoadAnimalsForCaseAction(
				labcaseActionPath, LoadAnimalsForCaseAction.LOADPAGE);
		actions.put(loadAnimalsForCaseAction.nameToString(), loadAnimalsForCaseAction);
		LabcaseAction closureLabcaseAction = new LabcaseAction(
				labcaseActionPath, LabcaseAction.CLOSURE);
		actions.put(closureLabcaseAction.nameToString(), closureLabcaseAction);
		LabcaseAction closeLabcaseAction = new LabcaseAction(
				labcaseActionPath, LabcaseAction.CLOSE);
		actions.put(closeLabcaseAction.nameToString(), closeLabcaseAction);

	}

}
