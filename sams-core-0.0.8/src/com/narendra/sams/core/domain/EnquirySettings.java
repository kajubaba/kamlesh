package com.narendra.sams.core.domain;

public class EnquirySettings {
    private AcademicYear activeAcademicYear;
    private Boolean enableCompetitiveExam;
    private Boolean enableDuplicateEnq;
    private Boolean enableInternalExam;
    private Boolean enablePreviousClass;
    private Boolean enableRegistered;
    private Long formFee;
    private Long nextFormReceiptNo;

    public AcademicYear getActiveAcademicYear() {
        return this.activeAcademicYear;
    }

    public void setActiveAcademicYear(AcademicYear activeAcademicYear) {
        this.activeAcademicYear = activeAcademicYear;
    }

    public Boolean getEnableRegistered() {
        if (this.enableRegistered == null) {
            return Boolean.FALSE;
        }
        return this.enableRegistered;
    }

    public void setEnableRegistered(Boolean enableRegistered) {
        this.enableRegistered = enableRegistered;
    }

    public Boolean getEnablePreviousClass() {
        if (this.enablePreviousClass == null) {
            return Boolean.FALSE;
        }
        return this.enablePreviousClass;
    }

    public void setEnablePreviousClass(Boolean enablePreviousClass) {
        this.enablePreviousClass = enablePreviousClass;
    }

    public Boolean getEnableInternalExam() {
        if (this.enableInternalExam == null) {
            return Boolean.FALSE;
        }
        return this.enableInternalExam;
    }

    public void setEnableInternalExam(Boolean enableInternalExam) {
        this.enableInternalExam = enableInternalExam;
    }

    public Boolean getEnableCompetitiveExam() {
        if (this.enableCompetitiveExam == null) {
            return Boolean.FALSE;
        }
        return this.enableCompetitiveExam;
    }

    public void setEnableCompetitiveExam(Boolean enableCompetitiveExam) {
        this.enableCompetitiveExam = enableCompetitiveExam;
    }

    public Boolean getEnableDuplicateEnq() {
        if (this.enableDuplicateEnq == null) {
            return Boolean.FALSE;
        }
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
