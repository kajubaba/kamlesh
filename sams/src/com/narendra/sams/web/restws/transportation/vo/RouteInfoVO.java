package com.narendra.sams.web.restws.transportation.vo;

public class RouteInfoVO {
    private Long busStopId;
    private Float distance;
    private long dropPointCount = 0;
    private Long id;
    private String name;
    private long pickupPointCount = 0;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
