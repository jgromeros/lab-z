/**
 * 
 */
package lab.model.assembly;

/**
 * @author juanromero
 *
 */
public class Position {

	private int plaque;
	private int row;
	private int col;

	public Position(int plaque, int row, int col) {
		setPlaque(plaque);
		setRow(row);
		setCol(col);
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

}
