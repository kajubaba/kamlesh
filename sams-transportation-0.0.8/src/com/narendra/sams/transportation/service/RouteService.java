package com.narendra.sams.transportation.service;

import com.narendra.sams.transportation.domain.Route;
import java.util.List;

public interface RouteService {
    Long addRoute(Route route, Long l);

    Long createRouteFromRoute(Route route, Boolean bool, Long l);

    List<Route> getAllRoutes(Long l);

    Route getRoute(Long l);

    Long getRouteCount(Long l);

    void updateRoute(Route route, Long l);
}
