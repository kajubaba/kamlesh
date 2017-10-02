package com.narendra.sams.web.restws.transportation.vo;

import java.util.List;

public class RouteBusStopInfoVO {
    private RouteBusStopVO busStopInfo;
    private List<RouteStudentVO> busStopStudents;

    public List<RouteStudentVO> getBusStopStudents() {
        return this.busStopStudents;
    }

    public void setBusStopStudents(List<RouteStudentVO> busStopStudents) {
        this.busStopStudents = busStopStudents;
    }

    public RouteBusStopVO getBusStopInfo() {
        return this.busStopInfo;
    }

    public void setBusStopInfo(RouteBusStopVO busStopInfo) {
        this.busStopInfo = busStopInfo;
    }
}
