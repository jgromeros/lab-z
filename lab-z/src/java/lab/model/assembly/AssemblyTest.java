/**
 * 
 */
package lab.model.assembly;

import lab.model.Entity;
import lab.model.test.Test;
import lab.model.test.result.Result;

/**
 * @author juango
 *
 */
public class AssemblyTest extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Test test;
	private int row;
	private int col;
	private int plaque;
	private String labCaseCode;
	private AssemblyControl control;
	private Assembly assembly;
	private Result result;

	public AssemblyTest() {
	}

	public AssemblyTest(String labCaseCode, Test test) {
		setTest(test);
		setLabCaseCode(labCaseCode);
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
	}

	/**
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param plaque the plaque to set
	 */
	public void setPlaque(int plaque) {
		this.plaque = plaque;
	}

	/**
	 * @return the plaque
	 */
	public int getPlaque() {
		return plaque;
	}

	/**
	 * @param labCaseCode the labCaseCode to set
	 */
	public void setLabCaseCode(String labCaseCode) {
		this.labCaseCode = labCaseCode;
	}

	/**
	 * @return the labCaseCode
	 */
	public String getLabCaseCode() {
		return labCaseCode;
	}

	/**
	 * @param control the control to set
	 */
	public void setControl(AssemblyControl control) {
		this.control = control;
	}

	/**
	 * @return the control
	 */
	public AssemblyControl getControl() {
		return control;
	}

	/**
	 * @param assembly the assembly to set
	 */
	public void setAssembly(Assembly assembly) {
		this.assembly = assembly;
	}

	/**
	 * @return the assembly
	 */
	public Assembly getAssembly() {
		return assembly;
	}

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

}
