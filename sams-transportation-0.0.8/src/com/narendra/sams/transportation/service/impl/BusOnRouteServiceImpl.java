package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.transportation.dao.BusOnRouteDAO;
import com.narendra.sams.transportation.dao.RouteDAO;
import com.narendra.sams.transportation.dao.StudentPickupDropPointDAO;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import com.narendra.sams.transportation.domain.VehicleRoute;
import com.narendra.sams.transportation.service.BusOnRouteService;
import java.util.List;

public class BusOnRouteServiceImpl implements BusOnRouteService {
    private BusOnRouteDAO busOnRouteDAO;
    private RouteDAO routeDAO;
    private StudentPickupDropPointDAO studentPickupDropPointDAO;

    public BusOnRouteDAO getBusOnRouteDAO() {
        return this.busOnRouteDAO;
    }

    public void setBusOnRouteDAO(BusOnRouteDAO busOnRouteDAO) {
        this.busOnRouteDAO = busOnRouteDAO;
    }

    public StudentPickupDropPointDAO getStudentPickupDropPointDAO() {
        return this.studentPickupDropPointDAO;
    }

    public void setStudentPickupDropPointDAO(StudentPickupDropPointDAO studentPickupDropPointDAO) {
        this.studentPickupDropPointDAO = studentPickupDropPointDAO;
    }

    public VehicleRoute getVehicleAssignedToRoute(Long routeId) {
        return this.busOnRouteDAO.getVehicleAssignedToRoute(routeId);
    }

    public RouteDAO getRouteDAO() {
        return this.routeDAO;
    }

    public void setRouteDAO(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    public Long updateVehicleOnRoute(Long routeId, Long academicSessionVehicleId) {
        VehicleRoute vehicleRoute = this.busOnRouteDAO.getVehicleRoute(academicSessionVehicleId, this.routeDAO.getRoute(routeId).getType());
        if (vehicleRoute != null) {
            this.busOnRouteDAO.deleteVehicleRoute(vehicleRoute.getId());
        }
        return this.busOnRouteDAO.updateVehicleOnRoute(routeId, academicSessionVehicleId);
    }

    public List<RouteStudent> getStudents(Long routeId) {
        Route route = this.routeDAO.getRoute(routeId);
        if (route == null) {
            return null;
        }
        List<RouteStudent> routeStudents = this.busOnRouteDAO.getRouteStudents(route.getId());
        if (routeStudents == null) {
            return routeStudents;
        }
        for (RouteStudent routeStudent : routeStudents) {
            StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointDAO.getStudentPickupDropPoint(routeStudent.getStudent().getId(), route.getAcademicYear().getId());
            if (studentPickupDropPoint != null) {
                if (studentPickupDropPoint.getPickupPoint() != null) {
                    routeStudent.setPickupPointId(studentPickupDropPoint.getPickupPoint().getId());
                    routeStudent.setPickupPoint(studentPickupDropPoint.getPickupPoint().getLocationName());
                }
                if (studentPickupDropPoint.getDropPoint() != null) {
                    routeStudent.setDropPointId(studentPickupDropPoint.getDropPoint().getId());
                    routeStudent.setDropPoint(studentPickupDropPoint.getDropPoint().getLocationName());
                }
            }
        }
        return routeStudents;
    }

    public List<Route> getStudentRoutes(Long studentId, Long academicSessionId) {
        return this.busOnRouteDAO.getStudentRoutes(studentId, academicSessionId);
    }

    public List<Route> getBusRoutes(Long academicSessionBusId, String routeType) {
        return this.busOnRouteDAO.getBusRoutes(academicSessionBusId, routeType);
    }
}
