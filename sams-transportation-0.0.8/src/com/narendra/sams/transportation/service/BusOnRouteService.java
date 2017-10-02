package com.narendra.sams.transportation.service;

import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.domain.VehicleRoute;
import java.util.List;

public interface BusOnRouteService {
    List<Route> getBusRoutes(Long l, String str);

    List<Route> getStudentRoutes(Long l, Long l2);

    List<RouteStudent> getStudents(Long l);

    VehicleRoute getVehicleAssignedToRoute(Long l);

    Long updateVehicleOnRoute(Long l, Long l2);
}
