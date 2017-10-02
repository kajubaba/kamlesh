package com.narendra.sams.core.domain;

public class BusFeeInstallmentDetail {
    private BusFeeInstallment busFeeInstallment;
    private Long feePercent;
    private Long id;
    private Installment installment;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Installment getInstallment() {
        return this.installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    public Long getFeePercent() {
        return this.feePercent;
    }

    public void setFeePercent(Long feePercent) {
        this.feePercent = feePercent;
    }

    public BusFeeInstallment getBusFeeInstallment() {
        return this.busFeeInstallment;
    }

    public void setBusFeeInstallment(BusFeeInstallment busFeeInstallment) {
        this.busFeeInstallment = busFeeInstallment;
    }
}
