package com.narendra.sams.web.restws.transportation.vo;

public class AcademicYearBusStopVO {
    private Long busStopFee;
    private String busStopName;
    private String distance;
    private Long id;

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

    public Long getBusStopFee() {
        return this.busStopFee;
    }

    public void setBusStopFee(Long busStopFee) {
        this.busStopFee = busStopFee;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
