package com.narendra.sams.web.admission.form;

public class FeeTransactionDetailForm {
    private Long amount;
    private Long feeHeadId;
    private String feeHeadName;

    public FeeTransactionDetailForm(Long feeHeadId, Long amount) {
        this.feeHeadId = feeHeadId;
        this.amount = amount;
    }

    public Long getFeeHeadId() {
        return this.feeHeadId;
    }

    public void setFeeHeadId(Long feeHeadId) {
        this.feeHeadId = feeHeadId;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getFeeHeadName() {
        return this.feeHeadName;
    }

    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }
}
