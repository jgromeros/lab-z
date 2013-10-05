/**
 * 
 */
package lab.model.test.result.resultfactor;

import lab.model.DescribedEntity;
import lab.model.animal.Specie;

/**
 * @author JuanGa
 *
 */
public class ReferenceValue extends DescribedEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String value;
    private Specie specie;
    private ResultFactor resultFactor;
    private String unit;
    private String relativeValue;

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param specie the specie to set
	 */
	public void setSpecie(Specie specie) {
		this.specie = specie;
	}

	/**
	 * @return the specie
	 */
	public Specie getSpecie() {
		return specie;
	}

	/**
	 * @param resultFactor the resultFactor to set
	 */
	public void setResultFactor(ResultFactor resultFactor) {
		this.resultFactor = resultFactor;
	}

	/**
	 * @return the resultFactor
	 */
	public ResultFactor getResultFactor() {
		return resultFactor;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param relativeValue the relativeValue to set
	 */
	public void setRelativeValue(String relativeValue) {
		this.relativeValue = relativeValue;
	}

	/**
	 * @return the relativeValue
	 */
	public String getRelativeValue() {
		return relativeValue;
	}

}
