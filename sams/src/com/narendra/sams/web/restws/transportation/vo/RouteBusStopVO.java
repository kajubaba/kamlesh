package com.narendra.sams.web.restws.transportation.vo;

public class RouteBusStopVO {
    private Long assignedStudentCount;
    private Long busStopId;
    private String busStopName;
    private Long id;
    private Long unAssignedStudenCount;

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

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

    public Long getAssignedStudentCount() {
        return this.assignedStudentCount;
    }

    public void setAssignedStudentCount(Long assignedStudentCount) {
        this.assignedStudentCount = assignedStudentCount;
    }

    public Long getUnAssignedStudenCount() {
        return this.unAssignedStudenCount;
    }

    public void setUnAssignedStudenCount(Long unAssignedStudenCount) {
        this.unAssignedStudenCount = unAssignedStudenCount;
    }
}
