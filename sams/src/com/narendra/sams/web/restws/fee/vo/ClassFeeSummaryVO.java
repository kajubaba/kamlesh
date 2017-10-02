package com.narendra.sams.web.restws.fee.vo;

public class ClassFeeSummaryVO {
    private String academicYearName;
    private Long busFeeTotal = Long.valueOf(0);
    private Long classHistoryId;
    private String className;
    private Long feeTotal = Long.valueOf(0);
    private Long lateFeeDiscountTotal = Long.valueOf(0);
    private Long lateFeeTotal = Long.valueOf(0);
    private Long paidTotal = Long.valueOf(0);
    private Long totalPayable = Long.valueOf(0);

    public Long getClassHistoryId() {
        return this.classHistoryId;
    }

    public void setClassHistoryId(Long classHistoryId) {
        this.classHistoryId = classHistoryId;
    }

    public String getAcademicYearName() {
        return this.academicYearName;
    }

    public void setAcademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }

    public Long getFeeTotal() {
        return this.feeTotal;
    }

    public void setFeeTotal(Long feeTotal) {
        this.feeTotal = feeTotal;
    }

    public Long getBusFeeTotal() {
        return this.busFeeTotal;
    }

    public void setBusFeeTotal(Long busFeeTotal) {
        this.busFeeTotal = busFeeTotal;
    }

    public Long getLateFeeTotal() {
        return this.lateFeeTotal;
    }

    public void setLateFeeTotal(Long lateFeeTotal) {
        this.lateFeeTotal = lateFeeTotal;
    }

    public Long getTotalPayable() {
        this.totalPayable = Long.valueOf(((this.feeTotal.longValue() + this.busFeeTotal.longValue()) + this.lateFeeTotal.longValue()) - this.lateFeeDiscountTotal.longValue());
        return this.totalPayable;
    }

    public Long getPaidTotal() {
        return this.paidTotal;
    }

    public void setPaidTotal(Long paidTotal) {
        this.paidTotal = paidTotal;
    }

    public Long getUnpaidTotal() {
        return Long.valueOf(getTotalPayable().longValue() - getPaidTotal().longValue());
    }

    public Long getLateFeeDiscountTotal() {
        return this.lateFeeDiscountTotal;
    }

    public void setLateFeeDiscountTotal(Long lateFeeDiscountTotal) {
        this.lateFeeDiscountTotal = lateFeeDiscountTotal;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
