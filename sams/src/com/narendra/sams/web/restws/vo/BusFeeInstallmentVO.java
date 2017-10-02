package com.narendra.sams.web.restws.vo;

import java.util.List;

public class BusFeeInstallmentVO {
    private List<BusFeeInstallmentPercentageVO> busFeeInstallmentDetails;
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

    public List<BusFeeInstallmentPercentageVO> getBusFeeInstallmentDetails() {
        return this.busFeeInstallmentDetails;
    }

    public void setBusFeeInstallmentDetails(List<BusFeeInstallmentPercentageVO> busFeeInstallmentDetails) {
        this.busFeeInstallmentDetails = busFeeInstallmentDetails;
    }
}
