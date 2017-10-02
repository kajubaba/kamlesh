package com.narendra.sams.web.admission.vo;

import java.util.List;

public class StudentInstallmentsVO {
    private List<StudentInstallmentVO> installments;
    private long totalBusFee;
    private long totalDepositedFee;
    private long totalInstallmentFee;

    public long getTotalInstallmentFee() {
        return this.totalInstallmentFee;
    }

    public void setTotalInstallmentFee(long totalInstallmentFee) {
        this.totalInstallmentFee = totalInstallmentFee;
    }

    public long getTotalDepositedFee() {
        return this.totalDepositedFee;
    }

    public void setTotalDepositedFee(long totalDepositedFee) {
        this.totalDepositedFee = totalDepositedFee;
    }

    public List<StudentInstallmentVO> getInstallments() {
        return this.installments;
    }

    public void setInstallments(List<StudentInstallmentVO> installments) {
        this.installments = installments;
    }

    public long getTotalBusFee() {
        return this.totalBusFee;
    }

    public void sumTotal() {
        if (this.installments != null) {
            for (StudentInstallmentVO installment : this.installments) {
                this.totalInstallmentFee += installment.getTotalFee();
                this.totalDepositedFee += installment.getDepositeFee();
                this.totalBusFee += installment.getBusFee();
            }
        }
    }

    public long getTotalDueFee() {
        return (this.totalInstallmentFee + this.totalBusFee) - this.totalDepositedFee;
    }
}
