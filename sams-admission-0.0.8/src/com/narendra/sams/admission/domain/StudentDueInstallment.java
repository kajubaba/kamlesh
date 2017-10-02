package com.narendra.sams.admission.domain;

import java.util.Date;

public class StudentDueInstallment {
    private Date dueDate;
    private Long fee;
    private String installment;
    private Long installmentId;
    private Long paidFee;
    private Long unpaidFee;

    public String getInstallment() {
        return this.installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getFee() {
        if (this.fee == null) {
            return Long.valueOf(0);
        }
        return this.fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getPaidFee() {
        if (this.paidFee == null) {
            return Long.valueOf(0);
        }
        return this.paidFee;
    }

    public void setPaidFee(Long paidFee) {
        this.paidFee = paidFee;
    }

    public Long getUnpaidFee() {
        return Long.valueOf(getFee().longValue() - getPaidFee().longValue());
    }

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }
}
