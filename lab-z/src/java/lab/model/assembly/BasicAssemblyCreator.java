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
 * @author juanromero
 *
 */
public class BasicAssemblyCreator implements AssemblyCreator {

	public static final String BASE = "Base";
	public static final String TOXOPLASM = "Toxoplasma";
	public static final String T3T4 = "T3 total, T4 total y T4 libre";
	public static final String TSH = "TSH";

	private AssemblyDelegator delegator;

	public BasicAssemblyCreator() {
		this.delegator = new SimpleAssemblyDelegator();
	}

	/* (non-Javadoc)
	 * @see lab.model.assembly.AssemblyCreator#doMount(java.util.Set, int, int)
	 */
	@Override
	public Assembly doMount(Set<Test> tests, int initRow, int initCol) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see lab.model.assembly.AssemblyCreator#doMount(java.util.List, lab.model.test.TestDescription)
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

	private void loadAssemblyTests(Assembly assembly, List<Labcase> labcases, TestDescription testDescription) {
		Map<String, List<Test>> labcaseTests = delegator.getTestsFromLabCase(labcases, testDescription);
		List<AssemblyTest> assemblyTests = new ArrayList<AssemblyTest>();
		assembly.setTests(assemblyTests);
		for (String labcaseCode : labcaseTests.keySet()) {
			for (Test test : labcaseTests.get(labcaseCode)){
				AssemblyTest at1 = new AssemblyTest(labcaseCode, test);
				assemblyTests.add(at1);
			}
		}
		delegator.assignPositions(assembly);
	}

}
