package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.FeeHead;

public class CustomizeInstallmentDetail {
    private Long amount;
    private CustomizeInstallment customizeInstallment;
    private FeeHead feeHead;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeeHead getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(FeeHead feeHead) {
        this.feeHead = feeHead;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public CustomizeInstallment getCustomizeInstallment() {
        return this.customizeInstallment;
    }

    public void setCustomizeInstallment(CustomizeInstallment customizeInstallment) {
        this.customizeInstallment = customizeInstallment;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CustomizeInstallmentDetail [id=").append(this.id).append(", feeHead=").append(this.feeHead).append(", amount=").append(this.amount).append(", customizeInstallment=").append(this.customizeInstallment).append("]");
        return builder.toString();
    }
}
