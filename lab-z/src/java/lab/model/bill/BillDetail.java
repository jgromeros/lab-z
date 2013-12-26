package lab.model.bill;

import java.math.BigDecimal;

import lab.model.Entity;
import lab.model.test.Test;

/**
 * This class represents the detail of a bill. It is not a real bill yet. It is just the
 * name that best describes the intention of this class. It's been built to manage the billing
 * information for a client.
 * @author jgromero 11/11/2013
 */
public class BillDetail extends Entity {

	private static final long serialVersionUID = 1L;

	private Bill bill;
	private Test test;
	private BigDecimal price;
	private BigDecimal tax;

	/**
	 * Compute the total value with taxes.
	 * Preconditions:
	 * - The tax saved is the value of the taxes, not the percentage.
	 * @return the price with taxes applied
	 */
    public BigDecimal computeTotalPrice() {
        return tax == null ? price : price.add(computeTax());
    }

    /**
     * Compute the tax for this detail
     * @return
     */
    private BigDecimal computeTax() {
        tax = price.multiply(test.getTestDescription().currentPrice().getTax()).
                divide(new BigDecimal(100));
        return tax;
    }

    public Bill getBill() {
    	return bill;
    }
	
    public void setBill(Bill bill) {
    	this.bill = bill;
    }
	
    public Test getTest() {
    	return test;
    }
	
    public void setTest(Test test) {
    	this.test = test;
    }
	
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

}