package com.narendra.sams.admission.utils;

public class LateFeeRuleVO {
    private Short fineAmount;
    private String finePeriod;
    private String fineRule;
    private Short from;
    private Short to;

    public Short getFrom() {
        return this.from;
    }

    public void setFrom(Short from) {
        this.from = from;
    }

    public Short getTo() {
        return this.to;
    }

    public void setTo(Short to) {
        this.to = to;
    }

    public Short getFineAmount() {
        return this.fineAmount;
    }

    public void setFineAmount(Short fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getFineRule() {
        return this.fineRule;
    }

    public void setFineRule(String fineRule) {
        this.fineRule = fineRule;
    }

    public String getFinePeriod() {
        return this.finePeriod;
    }

    public void setFinePeriod(String finePeriod) {
        this.finePeriod = finePeriod;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.from).append("-");
        if (this.to == null) {
            sb.append("NA");
        } else {
            sb.append(this.to);
        }
        sb.append(" ").append(this.fineAmount).append(" ").append(this.fineRule).append(" ").append(this.finePeriod);
        return sb.toString();
    }
}
