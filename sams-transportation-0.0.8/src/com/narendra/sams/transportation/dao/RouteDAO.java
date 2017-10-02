package com.narendra.sams.transportation.dao;

import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import java.util.List;

public interface RouteDAO {
    Long addRoute(Route route, Long l);

    List<Route> getAllRoutes(Long l);

    Route getRoute(Long l);

    RouteBusStop getRouteBusStop(Long l);

    Long getRouteCount(Long l);

    void updateRoute(Route route, Long l);
}
