package com.narendra.sams.web.restws.fee.vo;

public class AnnualFeeReportVO {
    private long actualPayable = 0;
    private String className;
    private long discount = 0;
    private long paidFee = 0;
    private long paidLateFee = 0;
    private long projectedFee = 0;
    private long studentCount = 0;
    private long unpaidFee = 0;

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getStudentCount() {
        return this.studentCount;
    }

    public void setStudentCount(long studentCount) {
        this.studentCount = studentCount;
    }

    public long getProjectedFee() {
        return this.projectedFee;
    }

    public void setProjectedFee(long projectedFee) {
        this.projectedFee = projectedFee;
    }

    public long getDiscount() {
        return this.discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public long getActualPayable() {
        return this.actualPayable;
    }

    public void setActualPayable(long actualPayable) {
        this.actualPayable = actualPayable;
    }

    public long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(long paidFee) {
        this.paidFee = paidFee;
    }

    public long getPaidLateFee() {
        return this.paidLateFee;
    }

    public void setPaidLateFee(long paidLateFee) {
        this.paidLateFee = paidLateFee;
    }

    public long getUnpaidFee() {
        return this.unpaidFee;
    }

    public void setUnpaidFee(long unpaidFee) {
        this.unpaidFee = unpaidFee;
    }
}
