package com.narendra.sams.transportation.dao;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import com.narendra.sams.transportation.domain.RouteStudent;
import java.util.Collection;
import java.util.List;

public interface RoutePlanDAO {
    void addBusStopsInRoute(Collection<RouteBusStop> collection);

    void addStdeuntsToRoute(Long l, Collection<RouteStudent> collection);

    void deleteRouteBusStop(Long l);

    void deleteRouteStudent(RouteStudent routeStudent);

    void deleteRouteStudent(Long l);

    List<BusFee> getAcademicYearBusStops(Long l);

    List<Student> getBusFacilityAdoptedAllAdmissions(Long l, Long l2);

    RouteBusStop getRouteBusStop(Long l);

    List<RouteBusStop> getRouteBusStops(Long l);

    Route getRouteOfBusStop(Long l);

    RouteStudent getStudentAssociatedWithRoute(Long l, String str);

    List<RouteStudent> getStudentsOfBusStop(Long l, Long l2, String str);

    List<RouteStudent> getStudentsOfRoute(Long l);
}
