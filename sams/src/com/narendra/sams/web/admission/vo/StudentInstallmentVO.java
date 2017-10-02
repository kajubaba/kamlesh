package com.narendra.sams.web.admission.vo;

import com.narendra.sams.admission.utils.LateFeeCalculator;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.util.DateUtil;
import java.util.Date;

public class StudentInstallmentVO {
    private Long academicYearFeeInstallmentId;
    private long busFee;
    private Boolean calculate = Boolean.TRUE;
    private Long custFeeInstallmentId;
    private long daysOverdue;
    private long depositeFee;
    private Date dueDate;
    private Long installmentId;
    private String installmentName;
    private long lateFeeDiscount;
    private LateFeeRule lateFeeRule;
    private long payable;
    private long totalFee;

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public String getInstallmentName() {
        return this.installmentName;
    }

    public void setInstallmentName(String installmentName) {
        this.installmentName = installmentName;
    }

    public long getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public long getDepositeFee() {
        return this.depositeFee;
    }

    public void setDepositeFee(long depositeFee) {
        this.depositeFee = depositeFee;
    }

    public long getDueFee() {
        return (((this.totalFee + this.busFee) + LateFeeCalculator.calculateLateFee(this.daysOverdue, this.lateFeeRule)) - this.depositeFee) - this.lateFeeDiscount;
    }

    public long getPayable() {
        return this.payable;
    }

    public void setPayable(long payable) {
        this.payable = payable;
    }

    public long getDiscount() {
        return this.totalFee - this.payable;
    }

    public long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(long busFee) {
        this.busFee = busFee;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public long getDaysOverdue() {
        return this.daysOverdue;
    }

    public void setDaysOverdue(long daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public long calculateFeeDaysOverdue() {
        if (this.calculate != null && this.calculate.booleanValue() && getDueFee() > 0 && this.dueDate != null) {
            this.daysOverdue = LateFeeCalculator.findOverDueDays(this.dueDate, DateUtil.getSystemDate());
        }
        return this.daysOverdue;
    }

    public long getLateFee() {
        return LateFeeCalculator.calculateLateFee(this.daysOverdue, this.lateFeeRule) - this.lateFeeDiscount;
    }

    public long getLateFeeDiscount() {
        return this.lateFeeDiscount;
    }

    public void setLateFeeDiscount(long lateFeeDiscount) {
        this.lateFeeDiscount = lateFeeDiscount;
    }

    public Boolean getCalculate() {
        return this.calculate;
    }

    public void setCalculate(Boolean calculate) {
        this.calculate = calculate;
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

    public LateFeeRule getLateFeeRule() {
        return this.lateFeeRule;
    }

    public void setLateFeeRule(LateFeeRule lateFeeRule) {
        this.lateFeeRule = lateFeeRule;
    }
}
