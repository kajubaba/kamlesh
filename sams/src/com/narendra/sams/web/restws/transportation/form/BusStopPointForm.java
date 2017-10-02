package com.narendra.sams.web.restws.transportation.form;

public class BusStopPointForm {
    private Long busStopId;
    private Boolean createReversePoint;
    private Long id;
    private String ladmark;
    private String name;
    private String type;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public String getLadmark() {
        return this.ladmark;
    }

    public void setLadmark(String ladmark) {
        this.ladmark = ladmark;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCreateReversePoint() {
        return this.createReversePoint;
    }

    public void setCreateReversePoint(Boolean createReversePoint) {
        this.createReversePoint = createReversePoint;
    }
}
