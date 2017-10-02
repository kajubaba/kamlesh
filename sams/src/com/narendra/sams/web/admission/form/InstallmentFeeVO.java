package com.narendra.sams.web.admission.form;

public class InstallmentFeeVO {
    private Long amount;
    private long deposited;
    private Long installmentFeeId;
    private Short installmentId;

    public Long getInstallmentFeeId() {
        return this.installmentFeeId;
    }

    public void setInstallmentFeeId(Long installmentFeeId) {
        this.installmentFeeId = installmentFeeId;
    }

    public Long getAmount() {
        if (this.amount == null) {
            return Long.valueOf(0);
        }
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Short getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Short installmentId) {
        this.installmentId = installmentId;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InstallmentFeeVO [installmentFeeId=").append(this.installmentFeeId).append(", amount=").append(this.amount).append(", installmentId=").append(this.installmentId).append("]");
        return builder.toString();
    }

    public long getDeposited() {
        return this.deposited;
    }

    public void setDeposited(long deposited) {
        this.deposited = deposited;
    }
}
