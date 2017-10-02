package com.narendra.sams.core.domain;

public class FeeSettings {
    public static String RECEIPT_TYPE_DOUBLE = "DOUBLE";
    public static String RECEIPT_TYPE_SINGLE = "SINGLE";
    private Long feeReceiptStartNo;
    private Boolean isFeeReceiptNoInCont;
    private Long lastFeeReceiptNo;
    private String receiptType;

    public Long getFeeReceiptStartNo() {
        return this.feeReceiptStartNo;
    }

    public void setFeeReceiptStartNo(Long feeReceiptStartNo) {
        this.feeReceiptStartNo = feeReceiptStartNo;
    }

    public Long getLastFeeReceiptNo() {
        return this.lastFeeReceiptNo;
    }

    public void setLastFeeReceiptNo(Long lastFeeReceiptNo) {
        this.lastFeeReceiptNo = lastFeeReceiptNo;
    }

    public String getReceiptType() {
        return this.receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public Boolean getIsFeeReceiptNoInCont() {
        return this.isFeeReceiptNoInCont;
    }

    public void setIsFeeReceiptNoInCont(Boolean isFeeReceiptNoInCont) {
        this.isFeeReceiptNoInCont = isFeeReceiptNoInCont;
    }
}
