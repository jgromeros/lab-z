/**
 * 
 */
package lab.model.test.result.resultfactor;

import java.math.BigDecimal;

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

    private BigDecimal minAbsoluteValue;
    private BigDecimal maxAbsoluteValue;
    private Specie specie;
    private ResultFactor resultFactor;
    private String unit;
    private BigDecimal minRelativeValue;
    private BigDecimal maxRelativeValue;

	public BigDecimal getMinAbsoluteValue() {
		return minAbsoluteValue;
	}

	public void setMinAbsoluteValue(BigDecimal minAbsoluteValue) {
		this.minAbsoluteValue = minAbsoluteValue;
	}

	public BigDecimal getMaxAbsoluteValue() {
		return maxAbsoluteValue;
	}

	public void setMaxAbsoluteValue(BigDecimal maxAbsoluteValue) {
		this.maxAbsoluteValue = maxAbsoluteValue;
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

	public BigDecimal getMinRelativeValue() {
		return minRelativeValue;
	}

	public void setMinRelativeValue(BigDecimal minRelativeValue) {
		this.minRelativeValue = minRelativeValue;
	}

	public BigDecimal getMaxRelativeValue() {
		return maxRelativeValue;
	}

	public void setMaxRelativeValue(BigDecimal maxRelativeValue) {
		this.maxRelativeValue = maxRelativeValue;
	}

}
