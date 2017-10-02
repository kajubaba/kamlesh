package com.narendra.sams.transportation.domain;

public class RouteInfo {
    private Long busStopId;
    private String busStopName;
    private Float distance;
    private long dropPointCount;
    private Long id;
    private long pickupPointCount;

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

    public long getPickupPointCount() {
        return this.pickupPointCount;
    }

    public void setPickupPointCount(long pickupPointCount) {
        this.pickupPointCount = pickupPointCount;
    }

    public long getDropPointCount() {
        return this.dropPointCount;
    }

    public void setDropPointCount(long dropPointCount) {
        this.dropPointCount = dropPointCount;
    }
}
