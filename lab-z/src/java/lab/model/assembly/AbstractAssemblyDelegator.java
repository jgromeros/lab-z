/**
 * 
 */
package lab.model.assembly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lab.model.animal.Animal;
import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.TestDescription;

/**
 * @author juanromero
 *
 */
public abstract class AbstractAssemblyDelegator implements AssemblyDelegator {

	protected abstract Position firstCellFree(Assembly assembly, AssemblyTest at);

	/* (non-Javadoc)
	 * @see lab.model.assembly.AssemblyDelegator#getTestsFromLabCase(java.util.List, lab.model.test.TestDescription)
	 */
	@Override
	public Map<String, List<Test>> getTestsFromLabCase(List<Labcase> labCases,
			TestDescription testDescription) {
		Map<String, List<Test>> labCaseTests = new HashMap<String, List<Test>>();
		for (Labcase labCase : labCases){
			List<Test> tests = new ArrayList<Test>();
			for (Animal a : labCase.getAnimals()){
				for (Test t : a.getTests()){
					if (t.getTestDescription().getId().equals(testDescription.getId())){
						tests.add(t);
					}
				}
			}
			labCaseTests.put(labCase.getCode(), tests);
		}
		return labCaseTests;
	}

	/* (non-Javadoc)
	 * @see lab.model.assembly.AssemblyDelegator#assignPositions(lab.model.assembly.Assembly)
	 */
	@Override
	public void assignPositions(Assembly assembly) {
		for (AssemblyTest at : assembly.getTests()){
			putAssemblyTest(assembly, at);
		}
	}

	/* (non-Javadoc)
	 * @see lab.model.assembly.AssemblyDelegator#loadAssemblyDescriptors(lab.model.assembly.Assembly, lab.model.test.TestDescription)
	 */
	@Override
	public void loadAssemblyDescriptors(Assembly assembly,
			TestDescription testDescription) {
		for (int i = 0; i <= assembly.getNumberOfPlaques(); i++){
			for (AssemblyDescriptor ad : assembly.getAssemblyType().getAssemblyDescriptors()) {
				AssemblyControl control = new AssemblyControl();
				control.setAssemblyDescriptor(ad);
				control.setTestDescription(testDescription);
				AssemblyTest at = new AssemblyTest();
				at.setCol(ad.getCol());
				at.setRow(ad.getRow());
				at.setPlaque(i);
				at.setControl(control);
				assembly.getTests().add(at);
			}
		}
	}

	private void putAssemblyTest(Assembly assembly, AssemblyTest at) {
		Position position = firstCellFree(assembly, at);
		at.setPlaque(position.getPlaque());
		at.setRow(position.getRow());
		at.setCol(position.getCol());
	}

	protected boolean foundAssemblyDescriptor(Assembly assembly, Position position) {
		for (AssemblyDescriptor ad : assembly.getAssemblyType().getAssemblyDescriptors()){
			if (ad.getRow() == position.getRow() && ad.getCol() == position.getCol()){
				return true;
			}
		}
		return false;
	}

	protected boolean foundAssemblyTest(Assembly assembly, Position position) {
		for (AssemblyTest at : assembly.getTests()){
			if (at.getRow() == position.getRow() &&
					at.getCol() == position.getCol() &&
					at.getPlaque() == position.getPlaque()){
				return true;
			}
		}
		return false;
	}

}
