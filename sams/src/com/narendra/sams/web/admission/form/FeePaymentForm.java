package com.narendra.sams.web.admission.form;

import java.util.List;

public class FeePaymentForm {
    private Long academciYearClassId;
    private Long academicYearFeeInstallmentId;
    private Long classHistoryId;
    private String comments;
    private Long custFeeInstallmentId;
    private Long feeRecieptHeaderId;
    private List<FeeTransactionDetailForm> feeTransactionDetailForms;
    private Short installmentId;
    private Short[] installmentIds;
    private Boolean printReciept;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAcademciYearClassId() {
        return this.academciYearClassId;
    }

    public void setAcademciYearClassId(Long academciYearClassId) {
        this.academciYearClassId = academciYearClassId;
    }

    public List<FeeTransactionDetailForm> getFeeTransactionDetailForms() {
        return this.feeTransactionDetailForms;
    }

    public void setFeeTransactionDetailForms(List<FeeTransactionDetailForm> feeTransactionDetailForms) {
        this.feeTransactionDetailForms = feeTransactionDetailForms;
    }

    public Short[] getInstallmentIds() {
        return this.installmentIds;
    }

    public void setInstallmentIds(Short[] installmentIds) {
        this.installmentIds = installmentIds;
    }

    public Short getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Short installmentId) {
        this.installmentId = installmentId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getAcademicYearFeeInstallmentId() {
        return this.academicYearFeeInstallmentId;
    }

    public void setAcademicYearFeeInstallmentId(Long academicYearFeeInstallmentId) {
        this.academicYearFeeInstallmentId = academicYearFeeInstallmentId;
    }

    public Long getCustFeeInstallmentId() {
        return this.custFeeInstallmentId;
    }

    public void setCustFeeInstallmentId(Long custFeeInstallmentId) {
        this.custFeeInstallmentId = custFeeInstallmentId;
    }

    public Long getFeeRecieptHeaderId() {
        return this.feeRecieptHeaderId;
    }

    public void setFeeRecieptHeaderId(Long feeRecieptHeaderId) {
        this.feeRecieptHeaderId = feeRecieptHeaderId;
    }

    public Long getClassHistoryId() {
        return this.classHistoryId;
    }

    public void setClassHistoryId(Long classHistoryId) {
        this.classHistoryId = classHistoryId;
    }

    public Boolean getPrintReciept() {
        return this.printReciept;
    }

    public void setPrintReciept(Boolean printReciept) {
        this.printReciept = printReciept;
    }
}
