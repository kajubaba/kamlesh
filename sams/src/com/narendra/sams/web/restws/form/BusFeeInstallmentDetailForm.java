package com.narendra.sams.web.restws.form;

public class BusFeeInstallmentDetailForm {
    private Long displayOrder;
    private Long feePercent;
    private Long id;
    private Long installmentId;
    private String installmentName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public String getInstallmentName() {
        return this.installmentName;
    }

    public void setInstallmentName(String installmentName) {
        this.installmentName = installmentName;
    }

    public Long getFeePercent() {
        return this.feePercent;
    }

    public void setFeePercent(Long feePercent) {
        this.feePercent = feePercent;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }
}
