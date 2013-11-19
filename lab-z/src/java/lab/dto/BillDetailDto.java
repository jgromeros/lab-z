package lab.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BillDetailDto {

    private String labcaseCode;
    private Long testId;
    private Date receptionDate;
    private String sender;
    private String patientName;
    private BigDecimal price;
    private String comment;

    public String getLabcaseCode() {
        return labcaseCode;
    }

    public void setLabcaseCode(String labcaseCode) {
        this.labcaseCode = labcaseCode;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
