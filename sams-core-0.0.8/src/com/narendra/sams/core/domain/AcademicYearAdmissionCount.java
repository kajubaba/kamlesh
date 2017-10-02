package com.narendra.sams.core.domain;

import java.io.Serializable;

public class AcademicYearAdmissionCount implements Serializable {
    private static final long serialVersionUID = -3610357741506236247L;
    private AcademicYear academicYear;
    private Long admissionCount;
    private Long feeRecieptNo;
    private Long feeRecieptStart;
    private Short id;
    private Long miscFeeRecieptNo;
    private Long miscFeeRecieptStart;
    private Long tempAdmissionCount;
    private Long transactionCount;

    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Long getAdmissionCount() {
        return this.admissionCount;
    }

    public void setAdmissionCount(Long admissionCount) {
        this.admissionCount = admissionCount;
    }

    public Long getTransactionCount() {
        return this.transactionCount;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Long getTempAdmissionCount() {
        return this.tempAdmissionCount;
    }

    public void setTempAdmissionCount(Long tempAdmissionCount) {
        this.tempAdmissionCount = tempAdmissionCount;
    }

    public Long getFeeRecieptStart() {
        return this.feeRecieptStart;
    }

    public void setFeeRecieptStart(Long feeRecieptStart) {
        this.feeRecieptStart = feeRecieptStart;
    }

    public Long getFeeRecieptNo() {
        return this.feeRecieptNo;
    }

    public void setFeeRecieptNo(Long feeRecieptNo) {
        this.feeRecieptNo = feeRecieptNo;
    }

    public Long getMiscFeeRecieptStart() {
        return this.miscFeeRecieptStart;
    }

    public void setMiscFeeRecieptStart(Long miscFeeRecieptStart) {
        this.miscFeeRecieptStart = miscFeeRecieptStart;
    }

    public Long getMiscFeeRecieptNo() {
        return this.miscFeeRecieptNo;
    }

    public void setMiscFeeRecieptNo(Long miscFeeRecieptNo) {
        this.miscFeeRecieptNo = miscFeeRecieptNo;
    }
}
