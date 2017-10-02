package com.narendra.sams.transportation.domain;

import com.narendra.sams.core.domain.BusFee;
import java.util.Set;

public class RouteBusStop {
    private BusFee busStop;
    private Set<RouteStudent> busStopStudents;
    private Long id;
    private Route route;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public BusFee getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusFee busStop) {
        this.busStop = busStop;
    }

    public Set<RouteStudent> getBusStopStudents() {
        return this.busStopStudents;
    }

    public void setBusStopStudents(Set<RouteStudent> busStopStudents) {
        this.busStopStudents = busStopStudents;
    }
}
