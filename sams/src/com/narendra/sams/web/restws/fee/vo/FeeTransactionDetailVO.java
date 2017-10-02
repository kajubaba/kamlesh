package com.narendra.sams.web.restws.fee.vo;

public class FeeTransactionDetailVO {
    private String feeHeadName;
    private Long paidAmount;

    public String getFeeHeadName() {
        return this.feeHeadName;
    }

    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }

    public Long getPaidAmount() {
        return this.paidAmount;
    }

    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }
}
