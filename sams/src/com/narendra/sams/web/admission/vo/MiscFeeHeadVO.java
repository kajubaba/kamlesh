package com.narendra.sams.web.admission.vo;

public class MiscFeeHeadVO {
    private long amount;
    private long deplositFee;
    private Long feeHeadId;
    private String feeHeadName;

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

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getDeplositFee() {
        return this.deplositFee;
    }

    public void setDeplositFee(long deplositFee) {
        this.deplositFee = deplositFee;
    }
}
