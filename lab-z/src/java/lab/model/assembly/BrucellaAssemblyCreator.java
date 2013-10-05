/**
 * 
 */
package lab.model.assembly;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.TestDescription;

/**
 * This class makes the assembly.
 * @author juanromero
 *
 */
public class BrucellaAssemblyCreator implements AssemblyCreator {

	public static final String TYPE = "Brucella";

	private AssemblyDelegator delegator;

	public BrucellaAssemblyCreator() {
		this.delegator = new SimpleAssemblyDelegator();
	}

	/**
	 * Method to make the assembly. Receives a number of tests and an initial row and an initial column if it doesn't initiates
	 * where it usually does.
	 */
	/* (non-Javadoc)
	 * @see lab.model.assembly.AssemblyCreator#doMount(java.util.Set, int, int)
	 */
	@Override
	public Assembly doMount(Set<Test> tests, int initRow, int initCol) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to make the assembly. This receives the labcases to mount, the type of assembly, and the testDescription. Probably
	 * doesn't need the type of the assembly. This method creates the assembly, and assign the date and the type.
	 * @return the assembly
	 */
	/* (non-Javadoc)
	 * @see lab.model.assembly.AssemblyCreator#doMount(java.util.Set)
	 */
	@Override
	public Assembly doMount(List<Labcase> cases, TestDescription testDescription) {
		Assembly assembly = new Assembly();
		assembly.setAssemblyDate(new Date());
		assembly.setAssemblyType(testDescription.getAssemblyType());
		loadAssemblyTests(assembly, cases, testDescription);
		delegator.loadAssemblyDescriptors(assembly, testDescription);
		return assembly;
	}

	/**
	 * Method that receives as parameter the list of labcases calls getTestsFromLabCase to make a map that then it iterates over,
	 * and creates a list of assemblyTests that finally it puts into some position by calling assignPosition method.
	 * @param assembly
	 * @param labCases
	 * @param testDescription
	 */
	private void loadAssemblyTests(Assembly assembly, List<Labcase> labcases, TestDescription testDescription) {
		Map<String, List<Test>> labcaseTests = delegator.getTestsFromLabCase(labcases, testDescription);
		List<AssemblyTest> assemblyTests = new ArrayList<AssemblyTest>();
		assembly.setTests(assemblyTests);
		for (String labcaseCode : labcaseTests.keySet()) {
			for (Test test : labcaseTests.get(labcaseCode)){
				AssemblyTest at1 = new AssemblyTest(labcaseCode, test);
				assemblyTests.add(at1);
				AssemblyTest at2 = new AssemblyTest(labcaseCode, test);
				assemblyTests.add(at2);
			}
		}
		delegator.assignPositions(assembly);
	}

}
