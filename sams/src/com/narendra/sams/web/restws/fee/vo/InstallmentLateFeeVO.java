package com.narendra.sams.web.restws.fee.vo;

public class InstallmentLateFeeVO {
    private Long daysOverDueId;
    private int daysOverdue;
    private Boolean disableLateFeeCalculation;
    private Integer discountGiven;
    private String installmentName;
    private long lateFee;
    private long payableLateFee;

    public String getInstallmentName() {
        return this.installmentName;
    }

    public void setInstallmentName(String installmentName) {
        this.installmentName = installmentName;
    }

    public int getDaysOverdue() {
        return this.daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public Integer getDiscountGiven() {
        return this.discountGiven;
    }

    public void setDiscountGiven(Integer discountGiven) {
        this.discountGiven = discountGiven;
    }

    public Boolean getDisableLateFeeCalculation() {
        return this.disableLateFeeCalculation;
    }

    public void setDisableLateFeeCalculation(Boolean disableLateFeeCalculation) {
        this.disableLateFeeCalculation = disableLateFeeCalculation;
    }

    public long getLateFee() {
        return this.lateFee;
    }

    public void setLateFee(long lateFee) {
        this.lateFee = lateFee;
    }

    public long getPayableLateFee() {
        this.payableLateFee = this.lateFee;
        if (this.discountGiven != null) {
            this.payableLateFee -= this.discountGiven.longValue();
        }
        return this.payableLateFee;
    }

    public Long getDaysOverDueId() {
        return this.daysOverDueId;
    }

    public void setDaysOverDueId(Long daysOverDueId) {
        this.daysOverDueId = daysOverDueId;
    }
}
