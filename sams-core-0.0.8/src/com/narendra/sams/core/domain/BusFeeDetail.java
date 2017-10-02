package com.narendra.sams.core.domain;

public class BusFeeDetail {
    private BusFee busFee;
    private Long fee;
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

    public Long getFee() {
        return this.fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public BusFee getBusFee() {
        return this.busFee;
    }

    public void setBusFee(BusFee busFee) {
        this.busFee = busFee;
    }
}
