package com.narendra.sams.admission.domain;

import java.util.Date;

public class DateWisePaidFee {
    private Date date;
    private String dateStr;
    private Long paidFee;

    public String getDateStr() {
        return this.dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(Long paidFee) {
        this.paidFee = paidFee;
    }
}
