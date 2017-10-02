package com.narendra.sams.web.restws.vo;

public class AcademicSessionBusStopVO {
    private Long busFee;
    private Long busStopId;
    private String busStopName;
    private Float distance;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public String getBusStopName() {
        return this.busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
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
}
