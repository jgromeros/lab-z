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
    private Long testProfile;

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

    public Long getTestProfile() {
        return testProfile;
    }

    public void setTestProfile(Long testProfile) {
        this.testProfile = testProfile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((testId == null) ? 0 : testId.hashCode());
        result = prime * result + ((testProfile == null) ? 0 : testProfile.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BillDetailDto other = (BillDetailDto) obj;
        if (testId == null) {
            if (other.testId != null)
                return false;
        } else if (!testId.equals(other.testId))
            return false;
        if (testProfile == null) {
            if (other.testProfile != null)
                return false;
        } else if (!testProfile.equals(other.testProfile))
            return false;
        return true;
    }

}
