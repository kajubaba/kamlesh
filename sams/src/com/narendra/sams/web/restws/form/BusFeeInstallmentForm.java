package com.narendra.sams.web.restws.form;

import java.util.List;

public class BusFeeInstallmentForm {
    private List<BusFeeInstallmentDetailForm> busFeeInstallmentDetails;
    private Long id;
    private Long noOfInstallment;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoOfInstallment() {
        return this.noOfInstallment;
    }

    public void setNoOfInstallment(Long noOfInstallment) {
        this.noOfInstallment = noOfInstallment;
    }

    public List<BusFeeInstallmentDetailForm> getBusFeeInstallmentDetails() {
        return this.busFeeInstallmentDetails;
    }

    public void setBusFeeInstallmentDetails(List<BusFeeInstallmentDetailForm> busFeeInstallmentDetails) {
        this.busFeeInstallmentDetails = busFeeInstallmentDetails;
    }
}
