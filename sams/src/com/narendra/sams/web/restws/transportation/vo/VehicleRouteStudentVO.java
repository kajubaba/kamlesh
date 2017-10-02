package com.narendra.sams.web.restws.transportation.vo;

import java.util.List;

public class VehicleRouteStudentVO {
    private Long routeId;
    private List<RouteStudentVO> routeStudents;
    private List<RouteVO> routes;

    public List<RouteVO> getRoutes() {
        return this.routes;
    }

    public void setRoutes(List<RouteVO> routes) {
        this.routes = routes;
    }

    public List<RouteStudentVO> getRouteStudents() {
        return this.routeStudents;
    }

    public void setRouteStudents(List<RouteStudentVO> routeStudents) {
        this.routeStudents = routeStudents;
    }

    public Long getRouteId() {
        return this.routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}
