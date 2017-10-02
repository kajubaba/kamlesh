package com.narendra.sams.transportation.dao;

import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.domain.VehicleRoute;
import java.util.List;

public interface BusOnRouteDAO {
    void deleteVehicleRoute(Long l);

    Route getBusRoute(Long l, String str);

    List<Route> getBusRoutes(Long l, String str);

    List<RouteStudent> getRouteStudents(Long l);

    List<Route> getStudentRoutes(Long l, Long l2);

    VehicleRoute getVehicleAssignedToRoute(Long l);

    VehicleRoute getVehicleRoute(Long l, String str);

    Long updateVehicleOnRoute(Long l, Long l2);
}
