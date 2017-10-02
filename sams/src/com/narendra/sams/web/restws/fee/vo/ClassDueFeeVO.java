package com.narendra.sams.web.restws.fee.vo;

public class ClassDueFeeVO {
    private Long academicYearId;
    private Long admissionStatus;
    private String className;
    private Long courseYearId;
    private String dueDate;
    private long paidAdvance = 0;
    private long paidFee = 0;
    private long paidLateFee = 0;
    private long payableBusFee = 0;
    private long projectedFee = 0;
    private long totalPaid = 0;
    private long unpaidFee = 0;

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getProjectedFee() {
        return this.projectedFee;
    }

    public void setProjectedFee(long projectedFee) {
        this.projectedFee = projectedFee;
    }

    public long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(long paidFee) {
        this.paidFee = paidFee;
    }

    public long getUnpaidFee() {
        return this.unpaidFee;
    }

    public void setUnpaidFee(long unpaidFee) {
        this.unpaidFee = unpaidFee;
    }

    public long getPaidAdvance() {
        return this.paidAdvance;
    }

    public void setPaidAdvance(long paidAdvance) {
        this.paidAdvance = paidAdvance;
    }

    public long getPaidLateFee() {
        return this.paidLateFee;
    }

    public void setPaidLateFee(long paidLateFee) {
        this.paidLateFee = paidLateFee;
    }

    public long getTotalPaid() {
        return this.totalPaid;
    }

    public void setTotalPaid(long totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getCourseYearId() {
        return this.courseYearId;
    }

    public void setCourseYearId(Long courseYearId) {
        this.courseYearId = courseYearId;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Long getAdmissionStatus() {
        return this.admissionStatus;
    }

    public void setAdmissionStatus(Long admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    public long getPayableBusFee() {
        return this.payableBusFee;
    }

    public void setPayableBusFee(long payableBusFee) {
        this.payableBusFee = payableBusFee;
    }
}
