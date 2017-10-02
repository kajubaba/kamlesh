package com.narendra.sams.web.restws.fee.vo;

public class CountVO {
    private Long count;
    private String countOf;

    public Long getCount() {
        if (this.count == null) {
            return Long.valueOf(0);
        }
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCountOf() {
        return this.countOf;
    }

    public void setCountOf(String countOf) {
        this.countOf = countOf;
    }
}
