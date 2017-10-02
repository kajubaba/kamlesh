package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.transportation.dao.RouteStopDAO;
import com.narendra.sams.transportation.domain.BusStopPoint;
import com.narendra.sams.transportation.domain.RouteInfo;
import com.narendra.sams.transportation.service.RouteStopService;
import java.util.ArrayList;
import java.util.List;

public class RouteStopServiceImpl implements RouteStopService {
    private RouteStopDAO routeStopDAO;

    public RouteStopDAO getRouteStopDAO() {
        return this.routeStopDAO;
    }

    public void setRouteStopDAO(RouteStopDAO routeStopDAO) {
        this.routeStopDAO = routeStopDAO;
    }

    public List<RouteInfo> getActiveBusStopsInAcademicYear(Long academicYearId) {
        List<RouteInfo> routeBusStops = null;
        List<BusFee> busFees = this.routeStopDAO.getActiveBusStopsInAcademicYear(academicYearId);
        if (busFees != null) {
            routeBusStops = new ArrayList();
            for (BusFee busFee : busFees) {
                RouteInfo routeBusStop = new RouteInfo();
                routeBusStop.setId(busFee.getId());
                routeBusStop.setBusStopId(busFee.getBusStop().getId());
                routeBusStop.setBusStopName(busFee.getBusStop().getName());
                routeBusStop.setDistance(busFee.getBusStop().getDistance());
                List<BusStopPoint> busStopPoints = this.routeStopDAO.getBusStopPickUpDropPoints(busFee.getId());
                long pickupPointCount = 0;
                long dropPointCount = 0;
                if (busStopPoints != null) {
                    for (BusStopPoint busStopPoint : busStopPoints) {
                        if ("Pickup".equals(busStopPoint.getType())) {
                            pickupPointCount++;
                        } else if ("Drop".equals(busStopPoint.getType())) {
                            dropPointCount++;
                        }
                    }
                }
                routeBusStop.setPickupPointCount(pickupPointCount);
                routeBusStop.setDropPointCount(dropPointCount);
                routeBusStops.add(routeBusStop);
            }
        }
        return routeBusStops;
    }

    public Long getBusStopCount(Long academicYearId) {
        return this.routeStopDAO.getBusStopCount(academicYearId);
    }

    public List<BusStopPoint> getBusStopPickUpDropPoints(Long busFeeId) {
        return this.routeStopDAO.getBusStopPickUpDropPoints(busFeeId);
    }

    public void addBusStopPickUpDropPoint(BusStopPoint busStopPoint, Boolean createReversePoint, Long userId) {
        this.routeStopDAO.addBusStopPickUpDropPoint(busStopPoint, userId);
        if (createReversePoint != null && createReversePoint.equals(Boolean.TRUE)) {
            BusStopPoint reversePoint = new BusStopPoint();
            reversePoint.setLocationName(busStopPoint.getLocationName());
            reversePoint.setLandmark(busStopPoint.getLandmark());
            reversePoint.setBusFee(busStopPoint.getBusFee());
            if ("Pickup".equals(busStopPoint.getType())) {
                reversePoint.setType("Drop");
            } else {
                reversePoint.setType("Pickup");
            }
            this.routeStopDAO.addBusStopPickUpDropPoint(reversePoint, userId);
        }
    }

    public BusStop getBusStop(Long academicYearBusStopId) {
        return this.routeStopDAO.getBusStop(academicYearBusStopId);
    }

    public List<BusStopPoint> getBusStopPickUpDropPoints(Long academicYearId, Long busStopId) {
        return this.routeStopDAO.getBusStopPickUpDropPoints(academicYearId, busStopId);
    }

    public BusFee getAcademicYearBusStop(Long academicYearId, Long busStopId) {
        return this.routeStopDAO.getAcademicYearBusStop(academicYearId, busStopId);
    }
}
