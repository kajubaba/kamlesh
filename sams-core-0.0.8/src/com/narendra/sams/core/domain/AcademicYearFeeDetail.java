package com.narendra.sams.core.domain;

public class AcademicYearFeeDetail {
    private AcademicYearFee academicYearFee;
    private Long amount;
    private FeeHead feeHead;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYearFee getAcademicYearFee() {
        return this.academicYearFee;
    }

    public void setAcademicYearFee(AcademicYearFee academicYearFee) {
        this.academicYearFee = academicYearFee;
    }

    public FeeHead getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(FeeHead feeHead) {
        this.feeHead = feeHead;
    }

    public Long getAmount() {
        if (this.amount == null) {
            return Long.valueOf(0);
        }
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
