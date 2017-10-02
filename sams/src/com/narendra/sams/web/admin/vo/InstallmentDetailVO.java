package com.narendra.sams.web.admin.vo;

public class InstallmentDetailVO {
    private Long academicYearFeeInstallmentDetailId;
    private Long academicYearFeeInstallmentId;
    private Long amount;
    private Long installmentId;

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAcademicYearFeeInstallmentId() {
        return this.academicYearFeeInstallmentId;
    }

    public void setAcademicYearFeeInstallmentId(Long academicYearFeeInstallmentId) {
        this.academicYearFeeInstallmentId = academicYearFeeInstallmentId;
    }

    public Long getAcademicYearFeeInstallmentDetailId() {
        return this.academicYearFeeInstallmentDetailId;
    }

    public void setAcademicYearFeeInstallmentDetailId(Long academicYearFeeInstallmentDetailId) {
        this.academicYearFeeInstallmentDetailId = academicYearFeeInstallmentDetailId;
    }
}
