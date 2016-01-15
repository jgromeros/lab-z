package lab.model.bill;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import lab.model.Entity;
import lab.model.test.Test;
import lab.model.test.TestProfile;

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
	private TestProfile testProfile;

	/**
	 * Compute the total value after subtracting taxes.
	 * @return the price with taxes applied
	 */
    public BigDecimal computeTaxes() {
        return price.subtract(computeTax());
    }

    /**
     * Compute the tax for this detail
     * @return
     */
    private BigDecimal computeTax() {
        BigDecimal taxRate = test != null ? test.getTestDescription().currentPrice().getTax() :
                testProfile.getProfile().currentPrice().getTax();
        tax = taxRate == null ? new BigDecimal(0) : price.subtract(price.divide(
                taxRate.divide(new BigDecimal(100)).add(new BigDecimal(1)), 0, RoundingMode.HALF_UP));
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

    public TestProfile getTestProfile() {
        return testProfile;
    }

    public void setTestProfile(TestProfile testProfile) {
        this.testProfile = testProfile;
    }

}
