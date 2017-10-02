package com.narendra.sams.transportation.service;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.transportation.domain.BusStopPoint;
import com.narendra.sams.transportation.domain.RouteInfo;
import java.util.List;

public interface RouteStopService {
    void addBusStopPickUpDropPoint(BusStopPoint busStopPoint, Boolean bool, Long l);

    BusFee getAcademicYearBusStop(Long l, Long l2);

    List<RouteInfo> getActiveBusStopsInAcademicYear(Long l);

    BusStop getBusStop(Long l);

    Long getBusStopCount(Long l);

    List<BusStopPoint> getBusStopPickUpDropPoints(Long l);

    List<BusStopPoint> getBusStopPickUpDropPoints(Long l, Long l2);
}
