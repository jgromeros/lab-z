package lab.model.bill;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lab.config.ConfigUtil;
import lab.exceptions.LabcaseException;
import lab.model.Entity;
import lab.model.test.Test;
import lab.model.test.TestProfile;

import org.apache.log4j.Logger;

/**
 * This class represents the detail of a bill. It is not a real bill yet. It is just the
 * name that best describes the intention of this class. It's been built to manage the billing
 * information for a client.
 * @author jgromero 11/11/2013
 */
public class BillDetail extends Entity {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_TAX = "default.tax";

    private static Logger logger = Logger.getLogger(BillDetail.class);

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
        try {
            return price.subtract(computeTax());
        } catch (LabcaseException e) {
            return getDefaultTax();
        }
    }

    /**
     * Compute the tax for this detail
     * @return
     */
    private BigDecimal computeTax() {
        BigDecimal taxRate = null;
        try {
            taxRate = test != null ? test.getTestDescription().currentPrice().getTax() :
                    testProfile.getProfile().currentPrice().getTax();
        } catch (LabcaseException e) {
            logger.warn(e);
            taxRate = getDefaultTax();
        }
        tax = taxRate == null ? new BigDecimal(0) : price.subtract(price.divide(
                taxRate.divide(new BigDecimal(100)).add(new BigDecimal(1)), 0, RoundingMode.HALF_UP));
        return tax;
    }

    public BigDecimal getDefaultTax() {
        return new BigDecimal(ConfigUtil.getProperty(DEFAULT_TAX));
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
