/**
 * 
 */
package lab.model.assembly;

import java.util.Date;
import java.util.List;

import lab.model.Entity;

/**
 * @author juango
 *
 */
public class Assembly extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalCols = 12;
	private int totalRows = 8;

	private Date assemblyDate;
	private int initRow;
	private int initCol;
	private List<AssemblyTest> tests;
	private AssemblyType assemblyType;
	private int numberOfPlaques;

	public Assembly(int initRow, int initCol){
		setInitRow(initRow);
		setInitCol(initCol);
	}

	public Assembly(){
		setInitRow(0);
		setInitCol(0);
	}

	/**
	 * @param assemblyDate the assemblyDate to set
	 */
	public void setAssemblyDate(Date assemblyDate) {
		this.assemblyDate = assemblyDate;
	}

	/**
	 * @return the assemblyDate
	 */
	public Date getAssemblyDate() {
		return assemblyDate;
	}

	/**
	 * @param initRow the initRow to set
	 */
	public void setInitRow(int initRow) {
		this.initRow = initRow;
	}

	/**
	 * @return the initRow
	 */
	public int getInitRow() {
		return initRow;
	}

	/**
	 * @param initCol the initCol to set
	 */
	public void setInitCol(int initCol) {
		this.initCol = initCol;
	}

	/**
	 * @return the initCol
	 */
	public int getInitCol() {
		return initCol;
	}

	/**
	 * @param tests the tests to set
	 */
	public void setTests(List<AssemblyTest> tests) {
		this.tests = tests;
		this.setNumberOfPlaques();
	}

	/**
	 * @return the tests
	 */
	public List<AssemblyTest> getTests() {
		return tests;
	}

	/**
	 * @param assemblyType the assemblyType to set
	 */
	public void setAssemblyType(AssemblyType assemblyType) {
		this.assemblyType = assemblyType;
	}

	/**
	 * @return the assemblyType
	 */
	public AssemblyType getAssemblyType() {
		return assemblyType;
	}

	public AssemblyTest[][][] toArray() {
		AssemblyTest[][][] tests = new AssemblyTest[getNumberOfPlaques() + 1][getTotalRows()][getTotalCols()];
		for (AssemblyTest at : getTests()){
			tests[at.getPlaque()][at.getRow()][at.getCol()] = at;
		}
		return tests;
	}

	public String[][][] toStringArray() {
		String [][][] testsIds = new String[getNumberOfPlaques() + 1][getTotalRows()][getTotalCols()];
		for (AssemblyTest at : getTests()){
			if (at.getTest() != null){
				testsIds[at.getPlaque()][at.getRow()][at.getCol()] =
						at.getLabCaseCode() + "-" + at.getTest().getAnimal().getName();
			} else {
				testsIds[at.getPlaque()][at.getRow()][at.getCol()] = at.getControl().getAssemblyDescriptor().getName();
			}
		}
		return testsIds;
	}

	private int assignNumberOfPlaques(){
		int plaques = -1;
		for (AssemblyTest t : getTests()){
			if (plaques < t.getPlaque()){
				plaques = t.getPlaque();
			}
		}
		return plaques;
	}

	/**
	 * 
	 */
	public void setNumberOfPlaques() {
		this.numberOfPlaques = assignNumberOfPlaques();
	}

	/**
	 * @return the numberOfPlaques
	 */
	public int getNumberOfPlaques() {
		numberOfPlaques = assignNumberOfPlaques();
		return numberOfPlaques;
	}

	/**
	 * @param totalCols the totalCols to set
	 */
	public void setTotalCols(int totalCols) {
		this.totalCols = totalCols;
	}

	/**
	 * @return the totalCols
	 */
	public int getTotalCols() {
		return totalCols;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

}
