package com.narendra.sams.web.restws.fee.vo;

public class FeeReceiptDataBean {
    private String feeHeadName;
    private String paidAmount;

    public String getFeeHeadName() {
        return this.feeHeadName;
    }

    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }

    public String getPaidAmount() {
        return this.paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }
}
