package com.narendra.sams.web.restws.transportation.mapper.vo;

import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.web.restws.transportation.vo.RouteVO;
import java.util.ArrayList;
import java.util.List;

public class RouteMapper {
    public static RouteVO mapToVO(Route route) {
        if (route == null) {
            return null;
        }
        RouteVO routeVO = new RouteVO();
        routeVO.setId(route.getId());
        routeVO.setName(route.getName());
        routeVO.setFrom(route.getFrom());
        routeVO.setTo(route.getTo());
        routeVO.setType(route.getType());
        routeVO.setPlannedStudents(route.getPlannedStudents());
        if (route.getAcademicYearVehicle() != null) {
            routeVO.setBusName(route.getAcademicYearVehicle().getVehicle().getVehicleName());
        }
        if (route.getBusStops() != null) {
            routeVO.setBusstops(Long.valueOf((long) route.getBusStops().size()));
        }
        routeVO.setStudentCount(route.getrouteStudentsCount());
        return routeVO;
    }

    public static List<RouteVO> mapToVOs(List<Route> routes) {
        if (routes == null) {
            return null;
        }
        List<RouteVO> routeVOs = new ArrayList();
        for (Route route : routes) {
            routeVOs.add(mapToVO(route));
        }
        return routeVOs;
    }
}
