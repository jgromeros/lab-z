/**
 * 
 */
package lab.model.assembly;

import lab.model.NamedEntity;

/**
 * @author juanromero
 *
 */
public class AssemblyDescriptor extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int row;
	private int col;
	private AssemblyType assemblyType;

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

}
