/**
 * 
 */
package lab.model.test.result.resultfactor;

import lab.model.NamedEntity;

/**
 * @author JuanGa
 * This persistent class save the description of factors that are measured in a test
 *  
 */
public class ResultFactor extends NamedEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String unit;
    private String group;
    private Boolean calculated;
    private Boolean computedValue;
    private Integer numberOrder;

    /**
     * @param unit The unit to set.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return Returns the unit.
     */
    public String getUnit() {
        return unit;
    }

    /**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param calculated the calculated to set
	 */
	public void setCalculated(Boolean calculated) {
		this.calculated = calculated;
	}

	/**
	 * @return the calculated
	 */
	public Boolean getCalculated() {
		return calculated;
	}

	/**
	 * @param computedValue the computedValue to set
	 */
	public void setComputedValue(Boolean computedValue) {
		this.computedValue = computedValue;
	}

	/**
	 * @return the computedValue
	 */
	public Boolean getComputedValue() {
		return computedValue;
	}

	public Integer getNumberOrder() {
		return numberOrder;
	}

	public void setNumberOrder(Integer numberOrder) {
		this.numberOrder = numberOrder;
	}

}
