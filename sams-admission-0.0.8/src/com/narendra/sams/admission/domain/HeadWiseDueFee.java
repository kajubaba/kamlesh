package com.narendra.sams.admission.domain;

public class HeadWiseDueFee {
    private Long due;
    private Long headId;
    private String headName;
    private Long paid;
    private Long total;

    public Long getHeadId() {
        return this.headId;
    }

    public void setHeadId(Long headId) {
        this.headId = headId;
    }

    public String getHeadName() {
        return this.headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public Long getTotal() {
        if (this.total == null) {
            return Long.valueOf(0);
        }
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPaid() {
        if (this.paid == null) {
            return Long.valueOf(0);
        }
        return this.paid;
    }

    public void setPaid(Long paid) {
        this.paid = paid;
    }

    public Long getDue() {
        return Long.valueOf(getTotal().longValue() - getPaid().longValue());
    }

    public void setDue(Long due) {
        this.due = due;
    }
}
