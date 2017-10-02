package com.narendra.sams.web.restws.fee.vo;

import com.narendra.sams.admission.utils.LateFeeCalculator;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.util.DateUtil;
import java.text.ParseException;
import org.codehaus.jackson.annotate.JsonIgnore;

public class FeeInstallmentVO {
    private Long academicFee = Long.valueOf(0);
    private Long academicYearFeeInstallmentId;
    private Long admissionSchemeDiscount = Long.valueOf(0);
    private Long admissionSchemeDiscountOnBusFee = Long.valueOf(0);
    private Long busFee = Long.valueOf(0);
    private Boolean calculateLateFee;
    private Long custFeeInstallmentId;
    private Long daysOverDue = Long.valueOf(0);
    private Long dueFee = Long.valueOf(0);
    private String installment;
    private String installmentDueDate;
    private Long installmentId;
    private Long lateFee = Long.valueOf(0);
    private Long lateFeeDiscount = Long.valueOf(0);
    @JsonIgnore
    private LateFeeRule lateFeeRule;
    private Long paidFee = Long.valueOf(0);
    private Long totalPayable = Long.valueOf(0);

    public String getInstallment() {
        return this.installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getInstallmentDueDate() {
        return this.installmentDueDate;
    }

    public void setInstallmentDueDate(String installmentDueDate) {
        this.installmentDueDate = installmentDueDate;
    }

    public Long getAcademicFee() {
        return this.academicFee;
    }

    public void setAcademicFee(Long academicFee) {
        this.academicFee = academicFee;
    }

    public Long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(Long busFee) {
        this.busFee = busFee;
    }

    public Long getLateFee() {
        this.lateFee = Long.valueOf(LateFeeCalculator.calculateLateFee(this.daysOverDue.longValue(), this.lateFeeRule));
        return this.lateFee;
    }

    public void setLateFee(Long lateFee) {
        this.lateFee = lateFee;
    }

    public Long getTotalPayable() {
        this.totalPayable = Long.valueOf(((((this.academicFee.longValue() + this.busFee.longValue()) + this.lateFee.longValue()) - this.lateFeeDiscount.longValue()) - this.admissionSchemeDiscount.longValue()) - this.admissionSchemeDiscountOnBusFee.longValue());
        return this.totalPayable;
    }

    public void setTotalPayable(Long totalPayable) {
        this.totalPayable = totalPayable;
    }

    public Long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(Long paidFee) {
        this.paidFee = paidFee;
    }

    public Long getDueFee() {
        this.dueFee = Long.valueOf(getTotalPayable().longValue() - getPaidFee().longValue());
        return this.dueFee;
    }

    public void setDueFee(Long dueFee) {
        this.dueFee = dueFee;
    }

    public LateFeeRule getLateFeeRule() {
        return this.lateFeeRule;
    }

    public void setLateFeeRule(LateFeeRule lateFeeRule) {
        this.lateFeeRule = lateFeeRule;
    }

    public Long getDaysOverDue() {
        return this.daysOverDue;
    }

    public void setDaysOverDue(Long daysOverDue) {
        this.daysOverDue = daysOverDue;
    }

    public Long getLateFeeDiscount() {
        return this.lateFeeDiscount;
    }

    public void setLateFeeDiscount(Long lateFeeDiscount) {
        this.lateFeeDiscount = lateFeeDiscount;
    }

    public Boolean getCalculateLateFee() {
        return this.calculateLateFee;
    }

    public void setCalculateLateFee(Boolean calculateLateFee) {
        this.calculateLateFee = calculateLateFee;
    }

    public long calculateFeeDaysOverdue() {
        if (this.calculateLateFee != null && this.calculateLateFee.booleanValue() && this.installmentDueDate != null && getDueFee().longValue() > 0) {
            try {
                this.daysOverDue = Long.valueOf(LateFeeCalculator.findOverDueDays(DateUtil.parseDate(this.installmentDueDate, "dd-MMM-yyyy"), DateUtil.getSystemDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this.daysOverDue.longValue();
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

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public Long getAdmissionSchemeDiscount() {
        return this.admissionSchemeDiscount;
    }

    public void setAdmissionSchemeDiscount(Long admissionSchemeDiscount) {
        this.admissionSchemeDiscount = admissionSchemeDiscount;
    }

    public Long getAdmissionSchemeDiscountOnBusFee() {
        return this.admissionSchemeDiscountOnBusFee;
    }

    public void setAdmissionSchemeDiscountOnBusFee(Long admissionSchemeDiscountOnBusFee) {
        this.admissionSchemeDiscountOnBusFee = admissionSchemeDiscountOnBusFee;
    }
}
