package com.narendra.sams.web.restws.vo;

import java.util.List;

public class BusStopFeeVO {
    private Long busFee;
    private List<BusFeeDetailVO> busFeeDetails;
    private Long busStopId;
    private String busStopName;
    private Float distance;
    private Long id;
    private Long installmentTotal;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusStopName() {
        return this.busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public Float getDistance() {
        return this.distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(Long busFee) {
        this.busFee = busFee;
    }

    public List<BusFeeDetailVO> getBusFeeDetails() {
        return this.busFeeDetails;
    }

    public void setBusFeeDetails(List<BusFeeDetailVO> busFeeDetails) {
        this.busFeeDetails = busFeeDetails;
    }

    public Long getInstallmentTotal() {
        return this.installmentTotal;
    }

    public void setInstallmentTotal(Long installmentTotal) {
        this.installmentTotal = installmentTotal;
    }
}
