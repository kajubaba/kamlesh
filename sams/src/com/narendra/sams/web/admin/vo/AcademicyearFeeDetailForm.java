package com.narendra.sams.web.admin.vo;

public class AcademicyearFeeDetailForm {
    private Long feeHeadId;
    private String feeHeadName;
    private NewAdmissionFeeForm newAdmissionFeeForm;
    private RegularAdmissionFeeForm regularAdmissionFeeForm;

    public Long getFeeHeadId() {
        return this.feeHeadId;
    }

    public void setFeeHeadId(Long feeHeadId) {
        this.feeHeadId = feeHeadId;
    }

    public String getFeeHeadName() {
        return this.feeHeadName;
    }

    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }

    public NewAdmissionFeeForm getNewAdmissionFeeForm() {
        return this.newAdmissionFeeForm;
    }

    public void setNewAdmissionFeeForm(NewAdmissionFeeForm newAdmissionFeeForm) {
        this.newAdmissionFeeForm = newAdmissionFeeForm;
    }

    public RegularAdmissionFeeForm getRegularAdmissionFeeForm() {
        return this.regularAdmissionFeeForm;
    }

    public void setRegularAdmissionFeeForm(RegularAdmissionFeeForm regularAdmissionFeeForm) {
        this.regularAdmissionFeeForm = regularAdmissionFeeForm;
    }
}
