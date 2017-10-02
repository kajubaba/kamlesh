package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class ClassFeeDetailVO {
    private Long academicFeeDiscount = Long.valueOf(0);
    private Long academicFeeTotal = Long.valueOf(0);
    private String academicYearName;
    private Long busFee = Long.valueOf(0);
    private Long busFeeDiscount = Long.valueOf(0);
    private Long classHistoryId = Long.valueOf(0);
    private String className;
    private Boolean feeCustomized = Boolean.FALSE;
    private List<FeeInstallmentVO> installmentFees;
    private Long lateFeeDiscountTotal = Long.valueOf(0);
    private Long lateFeeTotal = Long.valueOf(0);
    private Long paidTotal = Long.valueOf(0);
    private Long totalPayable = Long.valueOf(0);
    private Long unpaidTotal = Long.valueOf(0);

    public Long getClassHistoryId() {
        return this.classHistoryId;
    }

    public void setClassHistoryId(Long classHistoryId) {
        this.classHistoryId = classHistoryId;
    }

    public Boolean getFeeCustomized() {
        return this.feeCustomized;
    }

    public void setFeeCustomized(Boolean feeCustomized) {
        this.feeCustomized = feeCustomized;
    }

    public Long getAcademicFeeTotal() {
        return this.academicFeeTotal;
    }

    public void setAcademicFeeTotal(Long academicFeeTotal) {
        this.academicFeeTotal = academicFeeTotal;
    }

    public Long getAcademicFeeDiscount() {
        return this.academicFeeDiscount;
    }

    public void setAcademicFeeDiscount(Long academicFeeDiscount) {
        this.academicFeeDiscount = academicFeeDiscount;
    }

    public Long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(Long busFee) {
        this.busFee = busFee;
    }

    public Long getBusFeeDiscount() {
        return this.busFeeDiscount;
    }

    public void setBusFeeDiscount(Long busFeeDiscount) {
        this.busFeeDiscount = busFeeDiscount;
    }

    public List<FeeInstallmentVO> getInstallmentFees() {
        return this.installmentFees;
    }

    public void setInstallmentFees(List<FeeInstallmentVO> installmentFees) {
        this.installmentFees = installmentFees;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAcademicYearName() {
        return this.academicYearName;
    }

    public void setAcademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }

    public Long getLateFeeTotal() {
        if (this.installmentFees != null) {
            for (FeeInstallmentVO feeInstallmentVO : this.installmentFees) {
                this.lateFeeTotal = Long.valueOf(this.lateFeeTotal.longValue() + feeInstallmentVO.getLateFee().longValue());
            }
        }
        return this.lateFeeTotal;
    }

    public Long getLateFeeDiscountTotal() {
        if (this.installmentFees != null) {
            for (FeeInstallmentVO feeInstallmentVO : this.installmentFees) {
                this.lateFeeDiscountTotal = Long.valueOf(this.lateFeeDiscountTotal.longValue() + feeInstallmentVO.getLateFeeDiscount().longValue());
            }
        }
        return this.lateFeeDiscountTotal;
    }

    public Long getTotalPayable() {
        if (this.busFee == null) {
            this.totalPayable = Long.valueOf((((this.academicFeeTotal.longValue() - this.academicFeeDiscount.longValue()) - this.busFeeDiscount.longValue()) + this.lateFeeTotal.longValue()) - this.lateFeeDiscountTotal.longValue());
        } else {
            this.totalPayable = Long.valueOf(((((this.academicFeeTotal.longValue() - this.academicFeeDiscount.longValue()) + this.busFee.longValue()) - this.busFeeDiscount.longValue()) + this.lateFeeTotal.longValue()) - this.lateFeeDiscountTotal.longValue());
        }
        return this.totalPayable;
    }

    public Long getPaidTotal() {
        if (this.installmentFees != null) {
            for (FeeInstallmentVO feeInstallmentVO : this.installmentFees) {
                this.paidTotal = Long.valueOf(this.paidTotal.longValue() + feeInstallmentVO.getPaidFee().longValue());
            }
        }
        return this.paidTotal;
    }

    public Long getUnpaidTotal() {
        this.unpaidTotal = Long.valueOf(getTotalPayable().longValue() - getPaidTotal().longValue());
        return this.unpaidTotal;
    }
}
