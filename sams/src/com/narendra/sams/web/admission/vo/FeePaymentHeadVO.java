package com.narendra.sams.web.admission.vo;

public class FeePaymentHeadVO {
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

    public long getDueFee() {
        return this.amount - this.deplositFee;
    }

    public int hashCode() {
        return (this.feeHeadId == null ? 0 : this.feeHeadId.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FeePaymentHeadVO)) {
            return false;
        }
        FeePaymentHeadVO other = (FeePaymentHeadVO) obj;
        if (this.feeHeadId == null) {
            if (other.feeHeadId != null) {
                return false;
            }
            return true;
        } else if (this.feeHeadId.equals(other.feeHeadId)) {
            return true;
        } else {
            return false;
        }
    }
}
