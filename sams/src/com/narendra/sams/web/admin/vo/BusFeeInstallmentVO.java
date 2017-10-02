package com.narendra.sams.web.admin.vo;

import com.narendra.sams.core.domain.BusFeeInstallmentDetail;

public class BusFeeInstallmentVO {
    private BusFeeInstallmentDetail busFeeFirstInstallment;
    private BusFeeInstallmentDetail busFeeSecondInstallment;

    public BusFeeInstallmentDetail getBusFeeFirstInstallment() {
        return this.busFeeFirstInstallment;
    }

    public void setBusFeeFirstInstallment(BusFeeInstallmentDetail busFeeFirstInstallment) {
        this.busFeeFirstInstallment = busFeeFirstInstallment;
    }

    public BusFeeInstallmentDetail getBusFeeSecondInstallment() {
        return this.busFeeSecondInstallment;
    }

    public void setBusFeeSecondInstallment(BusFeeInstallmentDetail busFeeSecondInstallment) {
        this.busFeeSecondInstallment = busFeeSecondInstallment;
    }
}
