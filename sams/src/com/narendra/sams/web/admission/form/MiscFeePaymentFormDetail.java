package com.narendra.sams.web.admission.form;

public class MiscFeePaymentFormDetail {
    private Long amount;
    private Long feeHeadId;

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
}
