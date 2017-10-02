package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class FeeCollectionForm {
    private Long academicYearFeeInstallmentId;
    private Long custFeeInstallmentId;
    List<FeeHeadPayment> feeHeadPayments = null;
    private Long feeRecieptHeaderId;
    private Short installmentId;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Short getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Short installmentId) {
        this.installmentId = installmentId;
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

    public List<FeeHeadPayment> getFeeHeadPayments() {
        return this.feeHeadPayments;
    }

    public void setFeeHeadPayments(List<FeeHeadPayment> feeHeadPayments) {
        this.feeHeadPayments = feeHeadPayments;
    }
}
