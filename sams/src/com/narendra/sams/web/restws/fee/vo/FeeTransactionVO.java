package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class FeeTransactionVO {
    private String collectedBy;
    private String depositedBy;
    private String fatherName;
    private String feeInstallment;
    private Long paidFee;
    private List<FeeTransactionDetailVO> paidFees;
    private String paymentAcademicYear;
    private String paymentClass;
    private String paymentComments;
    private String paymentDate;
    private String paymentMode;
    private Long receiptNo;
    private Long studentDBId;
    private String studentId;
    private String studentName;
    private String transactionDateTime;
    private Long transactionId;
    private String transactionNo;

    public Long getStudentDBId() {
        return this.studentDBId;
    }

    public void setStudentDBId(Long studentDBId) {
        this.studentDBId = studentDBId;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionNo() {
        return this.transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Long getReceiptNo() {
        return this.receiptNo;
    }

    public void setReceiptNo(Long receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(Long paidFee) {
        this.paidFee = paidFee;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionDateTime() {
        return this.transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getPaymentComments() {
        return this.paymentComments;
    }

    public void setPaymentComments(String paymentComments) {
        this.paymentComments = paymentComments;
    }

    public String getDepositedBy() {
        return this.depositedBy;
    }

    public void setDepositedBy(String depositedBy) {
        this.depositedBy = depositedBy;
    }

    public String getCollectedBy() {
        return this.collectedBy;
    }

    public void setCollectedBy(String collectedBy) {
        this.collectedBy = collectedBy;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentAcademicYear() {
        return this.paymentAcademicYear;
    }

    public void setPaymentAcademicYear(String paymentAcademicYear) {
        this.paymentAcademicYear = paymentAcademicYear;
    }

    public String getPaymentClass() {
        return this.paymentClass;
    }

    public void setPaymentClass(String paymentClass) {
        this.paymentClass = paymentClass;
    }

    public String getFeeInstallment() {
        return this.feeInstallment;
    }

    public void setFeeInstallment(String feeInstallment) {
        this.feeInstallment = feeInstallment;
    }

    public List<FeeTransactionDetailVO> getPaidFees() {
        return this.paidFees;
    }

    public void setPaidFees(List<FeeTransactionDetailVO> paidFees) {
        this.paidFees = paidFees;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}
