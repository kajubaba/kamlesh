package com.narendra.sams.web.restws.vo;

public class AdmissionSchemeDetailVO {
    private Long discount;
    private String feeHead;
    private Long feeHeadId;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeeHeadId() {
        return this.feeHeadId;
    }

    public void setFeeHeadId(Long feeHeadId) {
        this.feeHeadId = feeHeadId;
    }

    public Long getDiscount() {
        return this.discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public String getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(String feeHead) {
        this.feeHead = feeHead;
    }
}
