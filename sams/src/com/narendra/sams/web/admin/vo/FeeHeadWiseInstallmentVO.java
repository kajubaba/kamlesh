package com.narendra.sams.web.admin.vo;

public class FeeHeadWiseInstallmentVO {
    private Long amount;
    private Long feeHeadId;
    private Long feeHeadInstallmentSum;
    private String feeHeadName;
    private InstallmentDetailVO[] installmentDetailVOs;

    public Long getFeeHeadId() {
        return this.feeHeadId;
    }

    public void setFeeHeadId(Long feeHeadId) {
        this.feeHeadId = feeHeadId;
    }

    public String getFeeHeadName() {
        return this.feeHeadName;
    }

    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public InstallmentDetailVO[] getInstallmentDetailVOs() {
        return this.installmentDetailVOs;
    }

    public void setInstallmentDetailVOs(InstallmentDetailVO[] installmentDetailVOs) {
        this.installmentDetailVOs = installmentDetailVOs;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InstallmentFeeHeadVO [feeHeadId=").append(this.feeHeadId).append(", feeHeadName=").append(this.feeHeadName).append(", amount=").append(this.amount).append(", installmentDetailVOs=").append(this.installmentDetailVOs).append("]");
        return builder.toString();
    }

    public Long getFeeHeadInstallmentSum() {
        return this.feeHeadInstallmentSum;
    }

    public void setFeeHeadInstallmentSum(Long feeHeadInstallmentSum) {
        this.feeHeadInstallmentSum = feeHeadInstallmentSum;
    }
}
