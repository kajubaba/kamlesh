package com.narendra.sams.transportation.dao;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.transportation.domain.BusStopPoint;
import java.util.List;

public interface RouteStopDAO {
    void addBusStopPickUpDropPoint(BusStopPoint busStopPoint, Long l);

    BusFee getAcademicYearBusStop(Long l, Long l2);

    List<BusFee> getActiveBusStopsInAcademicYear(Long l);

    BusStop getBusStop(Long l);

    Long getBusStopCount(Long l);

    List<BusStopPoint> getBusStopPickUpDropPoints(Long l);

    List<BusStopPoint> getBusStopPickUpDropPoints(Long l, Long l2);
}
