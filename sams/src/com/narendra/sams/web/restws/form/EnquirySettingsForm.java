package com.narendra.sams.web.restws.form;

public class EnquirySettingsForm {
    private Boolean enableCompetitiveExam;
    private Boolean enableDuplicateEnq;
    private Boolean enableInternalExam;
    private Boolean enablePreviousClass;
    private Boolean enableRegistered;
    private Long enquiryAcademicSessionId;
    private Long formFee;
    private Long nextFormReceiptNo;

    public Long getEnquiryAcademicSessionId() {
        return this.enquiryAcademicSessionId;
    }

    public void setEnquiryAcademicSessionId(Long enquiryAcademicSessionId) {
        this.enquiryAcademicSessionId = enquiryAcademicSessionId;
    }

    public Boolean getEnableRegistered() {
        return this.enableRegistered;
    }

    public void setEnableRegistered(Boolean enableRegistered) {
        this.enableRegistered = enableRegistered;
    }

    public Boolean getEnablePreviousClass() {
        return this.enablePreviousClass;
    }

    public void setEnablePreviousClass(Boolean enablePreviousClass) {
        this.enablePreviousClass = enablePreviousClass;
    }

    public Boolean getEnableInternalExam() {
        return this.enableInternalExam;
    }

    public void setEnableInternalExam(Boolean enableInternalExam) {
        this.enableInternalExam = enableInternalExam;
    }

    public Boolean getEnableCompetitiveExam() {
        return this.enableCompetitiveExam;
    }

    public void setEnableCompetitiveExam(Boolean enableCompetitiveExam) {
        this.enableCompetitiveExam = enableCompetitiveExam;
    }

    public Boolean getEnableDuplicateEnq() {
        return this.enableDuplicateEnq;
    }

    public void setEnableDuplicateEnq(Boolean enableDuplicateEnq) {
        this.enableDuplicateEnq = enableDuplicateEnq;
    }

    public Long getNextFormReceiptNo() {
        return this.nextFormReceiptNo;
    }

    public void setNextFormReceiptNo(Long nextFormReceiptNo) {
        this.nextFormReceiptNo = nextFormReceiptNo;
    }

    public Long getFormFee() {
        return this.formFee;
    }

    public void setFormFee(Long formFee) {
        this.formFee = formFee;
    }
}
