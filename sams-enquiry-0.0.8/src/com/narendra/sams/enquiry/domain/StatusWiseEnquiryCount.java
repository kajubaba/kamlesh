package com.narendra.sams.enquiry.domain;

public class StatusWiseEnquiryCount {
    private Long academicSessionId;
    private Long enquiryCount;
    private Long statusId;
    private String statusName;

    public Long getStatusId() {
        return this.statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getEnquiryCount() {
        return this.enquiryCount;
    }

    public void setEnquiryCount(Long enquiryCount) {
        this.enquiryCount = enquiryCount;
    }

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }
}
