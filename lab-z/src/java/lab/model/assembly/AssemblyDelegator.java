package lab.model.assembly;

import java.util.List;
import java.util.Map;

import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.TestDescription;

public interface AssemblyDelegator {

	public void loadAssemblyDescriptors(Assembly assembly,
			TestDescription testDescription);

	public Map<String, List<Test>> getTestsFromLabCase(List<Labcase> labCases,
			TestDescription testDescription);

	public void assignPositions(Assembly assembly);

}