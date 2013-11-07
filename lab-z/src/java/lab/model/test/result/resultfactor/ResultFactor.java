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

    private static final long serialVersionUID = 1L;
    
    private String unit;
    private String group;
    private Boolean calculated;
    private Boolean computedValue;
    private Integer numberOrder;
    private String valueType;

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroup() {
		return group;
	}

	public void setCalculated(Boolean calculated) {
		this.calculated = calculated;
	}

	public Boolean getCalculated() {
		return calculated;
	}

	public void setComputedValue(Boolean computedValue) {
		this.computedValue = computedValue;
	}

	public Boolean getComputedValue() {
		return computedValue;
	}

	public Integer getNumberOrder() {
		return numberOrder;
	}

	public void setNumberOrder(Integer numberOrder) {
		this.numberOrder = numberOrder;
	}

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

}
