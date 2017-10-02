package com.narendra.sams.web.restws.fee.vo;

public class InstallmentHeadFee {
    private Long amount = Long.valueOf(0);
    private Long feeHeadId;
    private String feeHeadName;
    private Long paid = Long.valueOf(0);
    private Long payable = Long.valueOf(0);
    private Long unpaid = Long.valueOf(0);

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

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPaid() {
        return this.paid;
    }

    public void setPaid(Long paid) {
        this.paid = paid;
    }

    public Long getPayable() {
        return this.payable;
    }

    public void setPayable(Long payable) {
        this.payable = payable;
    }

    public Long getUnpaid() {
        return Long.valueOf(this.amount.longValue() - this.paid.longValue());
    }

    public void setUnpaid(Long unpaid) {
        this.unpaid = unpaid;
    }
}
