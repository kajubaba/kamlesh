package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.dao.RouteDAO;
import com.narendra.sams.transportation.dao.RoutePlanDAO;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.service.RouteService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteServiceImpl implements RouteService {
    private RouteDAO routeDAO;
    private RoutePlanDAO routePlanDAO;
    private UserViewDAO userViewDAO;

    public RouteDAO getRouteDAO() {
        return this.routeDAO;
    }

    public void setRouteDAO(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public RoutePlanDAO getRoutePlanDAO() {
        return this.routePlanDAO;
    }

    public void setRoutePlanDAO(RoutePlanDAO routePlanDAO) {
        this.routePlanDAO = routePlanDAO;
    }

    public List<Route> getAllRoutes(Long academicYearId) {
        return this.routeDAO.getAllRoutes(academicYearId);
    }

    public Long getRouteCount(Long academicYearId) {
        return this.routeDAO.getRouteCount(academicYearId);
    }

    public Long addRoute(Route route, Long userId) {
        return this.routeDAO.addRoute(route, userId);
    }

    public void updateRoute(Route route, Long userId) {
        this.routeDAO.updateRoute(route, userId);
    }

    public Route getRoute(Long routeId) {
        return this.routeDAO.getRoute(routeId);
    }

    public Long createRouteFromRoute(Route flipRoute, Boolean forceCopy, Long userId) {
        Route existingRoute = this.routeDAO.getRoute(flipRoute.getId());
        if (existingRoute == null) {
            return null;
        }
        UserView user = this.userViewDAO.loadUser(userId);
        Route newRoute = new Route();
        newRoute.setAcademicYear(existingRoute.getAcademicYear());
        newRoute.setName(flipRoute.getName());
        newRoute.setFrom(flipRoute.getFrom());
        newRoute.setTo(flipRoute.getTo());
        newRoute.setType(flipRoute.getType());
        newRoute.setPlannedStudents(flipRoute.getPlannedStudents());
        newRoute.setCreatedDateTime(DateUtil.getSystemDateTime());
        newRoute.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
        newRoute.setCreatedBy(user);
        newRoute.setLastUpdatedBy(user);
        copyRouteBusStopsAndStudents(newRoute, existingRoute.getBusStops(), forceCopy, user);
        return this.routeDAO.addRoute(newRoute, userId);
    }

    private void copyRouteBusStopsAndStudents(Route newRoute, Set<RouteBusStop> busStopsToBeCopied, Boolean forceCopy, UserView user) {
        if (newRoute != null && busStopsToBeCopied != null) {
            Set<RouteBusStop> destRouteBusStops = new HashSet();
            for (RouteBusStop routeBusStop : busStopsToBeCopied) {
                RouteBusStop newRouteBusStop = new RouteBusStop();
                newRouteBusStop.setBusStop(routeBusStop.getBusStop());
                newRouteBusStop.setRoute(newRoute);
                if (routeBusStop.getBusStopStudents() != null) {
                    Set<RouteStudent> destRouteStudents = new HashSet();
                    for (RouteStudent routeStudent : routeBusStop.getBusStopStudents()) {
                        RouteStudent associatedStudent = this.routePlanDAO.getStudentAssociatedWithRoute(routeStudent.getStudent().getId(), newRoute.getType());
                        RouteStudent newRouteStudent;
                        if (associatedStudent == null) {
                            newRouteStudent = new RouteStudent();
                            newRouteStudent.setRouteBusStop(newRouteBusStop);
                            newRouteStudent.setStudent(routeStudent.getStudent());
                            newRouteStudent.setCreatedBy(user);
                            newRouteStudent.setLastUpdatedBy(user);
                            newRouteStudent.setCreatedDateTime(DateUtil.getSystemDateTime());
                            newRouteStudent.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
                            destRouteStudents.add(newRouteStudent);
                        } else if (forceCopy.booleanValue()) {
                            this.routePlanDAO.deleteRouteStudent(associatedStudent.getId());
                            newRouteStudent = new RouteStudent();
                            newRouteStudent.setRouteBusStop(newRouteBusStop);
                            newRouteStudent.setStudent(routeStudent.getStudent());
                            newRouteStudent.setCreatedBy(user);
                            newRouteStudent.setLastUpdatedBy(user);
                            newRouteStudent.setCreatedDateTime(DateUtil.getSystemDateTime());
                            newRouteStudent.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
                            destRouteStudents.add(newRouteStudent);
                        }
                    }
                    newRouteBusStop.setBusStopStudents(destRouteStudents);
                }
                destRouteBusStops.add(newRouteBusStop);
            }
            newRoute.setBusStops(destRouteBusStops);
        }
    }
}
