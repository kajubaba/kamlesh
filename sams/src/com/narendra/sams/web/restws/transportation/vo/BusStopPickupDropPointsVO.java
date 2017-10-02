package com.narendra.sams.web.restws.transportation.vo;

import java.util.ArrayList;
import java.util.List;

public class BusStopPickupDropPointsVO {
    private Long busStopId;
    private String busStopName;
    private List<BusStopPointVO> dropPoints = new ArrayList();
    private List<BusStopPointVO> pickupPoints = new ArrayList();

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

    public List<BusStopPointVO> getPickupPoints() {
        return this.pickupPoints;
    }

    public void setPickupPoints(List<BusStopPointVO> pickupPoints) {
        this.pickupPoints = pickupPoints;
    }

    public List<BusStopPointVO> getDropPoints() {
        return this.dropPoints;
    }

    public void setDropPoints(List<BusStopPointVO> dropPoints) {
        this.dropPoints = dropPoints;
    }
}
