package com.narendra.sams.core.domain;

import java.io.Serializable;

public class AcademicYearFeeInstallmentDetail implements Serializable {
    private static final long serialVersionUID = -7690581878452365786L;
    private AcademicYearFeeInstallment academicYearFeeInstallment;
    private Long amount;
    private FeeHead feeHead;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYearFeeInstallment getAcademicYearFeeInstallment() {
        return this.academicYearFeeInstallment;
    }

    public void setAcademicYearFeeInstallment(AcademicYearFeeInstallment academicYearFeeInstallment) {
        this.academicYearFeeInstallment = academicYearFeeInstallment;
    }

    public FeeHead getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(FeeHead feeHead) {
        this.feeHead = feeHead;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
