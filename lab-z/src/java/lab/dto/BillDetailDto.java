package lab.dto;

import java.math.BigDecimal;

public class BillDetailDto {

    private String labcaseCode;
    private Long testId;
    private String testDescription;
    private String receptionDate;
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

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
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
