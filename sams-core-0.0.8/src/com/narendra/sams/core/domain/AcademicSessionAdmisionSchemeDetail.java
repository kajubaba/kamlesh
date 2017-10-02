package com.narendra.sams.core.domain;

public class AcademicSessionAdmisionSchemeDetail {
    private AcademicYearAdmissionScheme academicYearAdmissionScheme;
    private Long discount;
    private FeeHead feeHead;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYearAdmissionScheme getAcademicYearAdmissionScheme() {
        return this.academicYearAdmissionScheme;
    }

    public void setAcademicYearAdmissionScheme(AcademicYearAdmissionScheme academicYearAdmissionScheme) {
        this.academicYearAdmissionScheme = academicYearAdmissionScheme;
    }

    public FeeHead getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(FeeHead feeHead) {
        this.feeHead = feeHead;
    }

    public Long getDiscount() {
        return this.discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }
}
