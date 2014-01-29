/**
 * 
 */
package lab.model.test;

import java.math.BigDecimal;
import java.util.Date;

import lab.model.Entity;

/**
 * This class represents each testPrice
 * @author jgromero 07/11/2013
 */
public class TestPrice extends Entity {

    private static final long serialVersionUID = 1L;

    private BigDecimal price;
    /**
     * The value of the tax as a percentage
     */
    private BigDecimal tax;
    private Date validFrom;
    private Date validUntil;
    private TestDescription testDescription;

    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTax() {
        return tax;
    }
    
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    public Date getValidFrom() {
        return validFrom;
    }
    
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }
    
    public Date getValidUntil() {
        return validUntil;
    }
    
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public TestDescription getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(TestDescription testDescription) {
        this.testDescription = testDescription;
    }

}
