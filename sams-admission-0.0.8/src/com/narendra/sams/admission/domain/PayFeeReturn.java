package com.narendra.sams.admission.domain;

public class PayFeeReturn {
    private Long dbTransactionId;
    private Long recieptNo;
    private String transactionId;

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getRecieptNo() {
        return this.recieptNo;
    }

    public void setRecieptNo(Long recieptNo) {
        this.recieptNo = recieptNo;
    }

    public Long getDbTransactionId() {
        return this.dbTransactionId;
    }

    public void setDbTransactionId(Long dbTransactionId) {
        this.dbTransactionId = dbTransactionId;
    }
}
