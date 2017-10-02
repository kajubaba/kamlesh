package com.narendra.sams.transportation.service;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import com.narendra.sams.transportation.domain.RouteStudent;
import java.util.List;

public interface RoutePlanService {
    void addBusStopsInRoute(Long l, List<Integer> list, Long l2);

    void addStudentsToRoute(Long l, List<Integer> list, Long l2);

    void deleteRouteBusStop(Long l);

    void deleteRouteStudent(Long l);

    List<BusFee> getBusStopsToBeAssignedToRoute(Long l);

    RouteBusStop getRouteBusStop(Long l);

    List<RouteBusStop> getRouteBusStops(Long l);

    Route getRouteOfBusStop(Long l);

    List<RouteStudent> getRouteStudentsWithPickDropPints(Long l, Long l2);

    List<Student> getStudentsNotAssginedToBusStopInAnyRoute(Long l, String str);

    List<RouteStudent> getStudentsOfRoute(Long l);
}
