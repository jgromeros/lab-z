package lab.model.bill;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lab.model.Entity;
import lab.model.enterprise.Enterprise;
import lab.model.test.Test;

/**
 * This class represents an invoice. It is not a real invoice. It is just the
 * name that best describes the intention of this class. It's been built to
 * manage the billing information for a client.
 * 
 * @author jgromero 09/11/2013
 */
public class Bill extends Entity {

	private static final long serialVersionUID = 1L;

	private Integer billNumber;
	private Date billDate;
	private Enterprise client;
	private BigDecimal totalBeforeTaxes;
	private BigDecimal totalAfterTaxes;
	private List<BillDetail> billedDetails;

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

	public List<BillDetail> getBilledTests() {
		return billedDetails;
	}

	public void setBilledDetails(List<BillDetail> billedDetails) {
		this.billedDetails = billedDetails;
	}

}
