package com.narendra.sams.web.restws.form;

public class ClassHeadFeeForm {
    private String feeHead;
    private Long headId;
    private HeadFeeForm newAdmissionFee;
    private HeadFeeForm regularAdmissionFee;

    public Long getHeadId() {
        return this.headId;
    }

    public void setHeadId(Long headId) {
        this.headId = headId;
    }

    public String getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(String feeHead) {
        this.feeHead = feeHead;
    }

    public HeadFeeForm getNewAdmissionFee() {
        return this.newAdmissionFee;
    }

    public void setNewAdmissionFee(HeadFeeForm newAdmissionFee) {
        this.newAdmissionFee = newAdmissionFee;
    }

    public HeadFeeForm getRegularAdmissionFee() {
        return this.regularAdmissionFee;
    }

    public void setRegularAdmissionFee(HeadFeeForm regularAdmissionFee) {
        this.regularAdmissionFee = regularAdmissionFee;
    }
}
