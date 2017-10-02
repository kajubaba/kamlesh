package com.narendra.sams.web.utils;

public class DueFeeNoticeInstallmentDetail {
    private String dueDate;
    private Long fee;
    private String installment;
    private Long paid;
    private Long unpaid;

    public String getInstallment() {
        return this.installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Long getFee() {
        return this.fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getPaid() {
        return this.paid;
    }

    public void setPaid(Long paid) {
        this.paid = paid;
    }

    public Long getUnpaid() {
        return this.unpaid;
    }

    public void setUnpaid(Long unpaid) {
        this.unpaid = unpaid;
    }
}
