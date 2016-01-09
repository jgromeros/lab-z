package lab.model.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lab.model.Entity;
import lab.model.enterprise.Enterprise;

/**
 * This class represents an invoice. It is not a real invoice. It is just the
 * name that best describes the intention of this class. It's been built to
 * manage the billing information for a client.
 * 
 * @author jgromero 09/11/2013
 */
public class Bill extends Entity {

	private static final long serialVersionUID = 1L;

	public static final String VALID = "V";
    public static final String CANCELLED = "C";

    private Integer billNumber;
	private Date billDate;
	private Enterprise client;
	private BigDecimal totalBeforeTaxes;
	private BigDecimal totalAfterTaxes;
	private List<BillDetail> billedDetails;
	private String status;

	public Bill() {
	}

	/**
	 * Constructor that defaults date to today and creates an empty list of bill details.
	 * @param client
	 */
	public Bill(Enterprise client) {
	    this.client = client;
	    this.billDate = new Date();
	    this.billedDetails = new ArrayList<BillDetail>();
	    this.status = VALID;
	}

	/**
	 * Compute the sum of the prices of the details of this bill before including taxes
	 */
    public void computeTotalBeforeTaxes() {
        totalBeforeTaxes = new BigDecimal(0);
        for (BillDetail billDetail : billedDetails){
            totalBeforeTaxes = totalBeforeTaxes.add(billDetail.computeTaxes());
        }
    }

    /**
     * Compute the sum of the prices of the details of this bill with taxes
     */
    public void computeTotalAfterTaxes() {
        totalAfterTaxes = new BigDecimal(0);
        for (BillDetail billDetail : billedDetails){
            totalAfterTaxes = totalAfterTaxes.add(billDetail.getPrice());
        }
    }

	public Integer getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(Integer billNumber) {
		this.billNumber = billNumber;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Enterprise getClient() {
		return client;
	}

	public void setClient(Enterprise client) {
		this.client = client;
	}

	public BigDecimal getTotalBeforeTaxes() {
		return totalBeforeTaxes;
	}

    public void setTotalBeforeTaxes(BigDecimal totalBeforeTaxes) {
        this.totalBeforeTaxes = totalBeforeTaxes;
    }

    public BigDecimal getTotalAfterTaxes() {
		return totalAfterTaxes;
	}

    public void setTotalAfterTaxes(BigDecimal totalAfterTaxes) {
        this.totalAfterTaxes = totalAfterTaxes;
    }

    public List<BillDetail> getBilledDetails() {
		return billedDetails;
	}

	public void setBilledDetails(List<BillDetail> billedDetails) {
		this.billedDetails = billedDetails;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
