/**
 * 
 */
package lab.model.assembly;

import lab.model.Entity;
import lab.model.test.TestDescription;
import lab.model.test.result.Result;

/**
 * @author juanromero
 *
 */
public class AssemblyControl extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Result result;
	private TestDescription testDescription;
	private AssemblyDescriptor assemblyDescriptor;

	/**
	 * @param result the result to set
	 */
	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * @param testDescription the testDescription to set
	 */
	public void setTestDescription(TestDescription testDescription) {
		this.testDescription = testDescription;
	}

	/**
	 * @return the testDescription
	 */
	public TestDescription getTestDescription() {
		return testDescription;
	}

	/**
	 * @param assemblyDescriptor the assemblyDescriptor to set
	 */
	public void setAssemblyDescriptor(AssemblyDescriptor assemblyDescriptor) {
		this.assemblyDescriptor = assemblyDescriptor;
	}

	/**
	 * @return the assemblyDescriptor
	 */
	public AssemblyDescriptor getAssemblyDescriptor() {
		return assemblyDescriptor;
	}

}
