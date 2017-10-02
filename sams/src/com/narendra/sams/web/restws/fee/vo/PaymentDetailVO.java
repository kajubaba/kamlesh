package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class PaymentDetailVO {
    private String bankBranchName;
    private String chequeDDBankName;
    private String chequeDDDate;
    private String chequeDDNo;
    private String comments;
    private Long custInstallmentId;
    private String depositedBy;
    private Long feeInstallmentId;
    private List<InstallmentHeadFee> installmentHeads = null;
    private Long installmentId;
    private Long paymentClassId;
    private String paymentDate;
    private String paymentMode;
    private Boolean printReceipt;
    private Long receiptHeaderId;
    private Long studentId;

    public Long getFeeInstallmentId() {
        return this.feeInstallmentId;
    }

    public void setFeeInstallmentId(Long feeInstallmentId) {
        this.feeInstallmentId = feeInstallmentId;
    }

    public Long getCustInstallmentId() {
        return this.custInstallmentId;
    }

    public void setCustInstallmentId(Long custInstallmentId) {
        this.custInstallmentId = custInstallmentId;
    }

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<InstallmentHeadFee> getInstallmentHeads() {
        return this.installmentHeads;
    }

    public void setInstallmentHeads(List<InstallmentHeadFee> installmentHeads) {
        this.installmentHeads = installmentHeads;
    }

    public Boolean getPrintReceipt() {
        return this.printReceipt;
    }

    public void setPrintReceipt(Boolean printReceipt) {
        this.printReceipt = printReceipt;
    }

    public String getDepositedBy() {
        return this.depositedBy;
    }

    public void setDepositedBy(String depositedBy) {
        this.depositedBy = depositedBy;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getChequeDDNo() {
        return this.chequeDDNo;
    }

    public void setChequeDDNo(String chequeDDNo) {
        this.chequeDDNo = chequeDDNo;
    }

    public String getChequeDDDate() {
        return this.chequeDDDate;
    }

    public void setChequeDDDate(String chequeDDDate) {
        this.chequeDDDate = chequeDDDate;
    }

    public String getChequeDDBankName() {
        return this.chequeDDBankName;
    }

    public void setChequeDDBankName(String chequeDDBankName) {
        this.chequeDDBankName = chequeDDBankName;
    }

    public String getBankBranchName() {
        return this.bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentClassId() {
        return this.paymentClassId;
    }

    public void setPaymentClassId(Long paymentClassId) {
        this.paymentClassId = paymentClassId;
    }

    public Long getReceiptHeaderId() {
        return this.receiptHeaderId;
    }

    public void setReceiptHeaderId(Long receiptHeaderId) {
        this.receiptHeaderId = receiptHeaderId;
    }
}
