package com.narendra.sams.web.restws.fee.vo;

public class PaidFeeVO {
    private Long amount;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
}
