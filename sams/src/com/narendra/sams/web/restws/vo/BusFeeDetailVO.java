package com.narendra.sams.web.restws.vo;

public class BusFeeDetailVO {
    private Long busFeeId;
    private Long fee;
    private Long id;
    private Long installmentId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFee() {
        return this.fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public Long getBusFeeId() {
        return this.busFeeId;
    }

    public void setBusFeeId(Long busFeeId) {
        this.busFeeId = busFeeId;
    }
}
