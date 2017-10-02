package com.narendra.sams.web.restws.vo;

import java.util.List;

public class BusFeeVO {
    private Long academicSessionId;
    private Long busFeeInstallmentId;
    private List<BusFeeInstallmentPercentageVO> busFeeInstallmentInPercentage;
    private List<BusStopFeeVO> busStopFees;
    private Long installments;
    private String setupType;

    public List<BusStopFeeVO> getBusStopFees() {
        return this.busStopFees;
    }

    public void setBusStopFees(List<BusStopFeeVO> busStopFees) {
        this.busStopFees = busStopFees;
    }

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }

    public Long getInstallments() {
        return this.installments;
    }

    public void setInstallments(Long installments) {
        this.installments = installments;
    }

    public String getSetupType() {
        return this.setupType;
    }

    public void setSetupType(String setupType) {
        this.setupType = setupType;
    }

    public List<BusFeeInstallmentPercentageVO> getBusFeeInstallmentInPercentage() {
        return this.busFeeInstallmentInPercentage;
    }

    public void setBusFeeInstallmentInPercentage(List<BusFeeInstallmentPercentageVO> busFeeInstallmentInPercentage) {
        this.busFeeInstallmentInPercentage = busFeeInstallmentInPercentage;
    }

    public Long getBusFeeInstallmentId() {
        return this.busFeeInstallmentId;
    }

    public void setBusFeeInstallmentId(Long busFeeInstallmentId) {
        this.busFeeInstallmentId = busFeeInstallmentId;
    }
}
