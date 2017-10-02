package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.dao.RouteDAO;
import com.narendra.sams.transportation.dao.RoutePlanDAO;
import com.narendra.sams.transportation.dao.StudentPickupDropPointDAO;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import com.narendra.sams.transportation.service.RoutePlanService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoutePlanServiceImpl implements RoutePlanService {
    private RouteDAO routeDAO;
    private RoutePlanDAO routePlanDAO;
    private StudentPickupDropPointDAO studentPickupDropPointDAO;

    public StudentPickupDropPointDAO getStudentPickupDropPointDAO() {
        return this.studentPickupDropPointDAO;
    }

    public void setStudentPickupDropPointDAO(StudentPickupDropPointDAO studentPickupDropPointDAO) {
        this.studentPickupDropPointDAO = studentPickupDropPointDAO;
    }

    public RouteDAO getRouteDAO() {
        return this.routeDAO;
    }

    public void setRouteDAO(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    public RoutePlanDAO getRoutePlanDAO() {
        return this.routePlanDAO;
    }

    public void setRoutePlanDAO(RoutePlanDAO routePlanDAO) {
        this.routePlanDAO = routePlanDAO;
    }

    public List<RouteBusStop> getRouteBusStops(Long routeId) {
        Route route = this.routeDAO.getRoute(routeId);
        List<RouteBusStop> routeBusStops = this.routePlanDAO.getRouteBusStops(routeId);
        if (routeBusStops == null) {
            return null;
        }
        for (RouteBusStop routeBusStop : routeBusStops) {
            if (routeBusStop.getBusStopStudents() != null) {
                for (RouteStudent routeStudent : routeBusStop.getBusStopStudents()) {
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
            }
        }
        return routeBusStops;
    }

    public List<BusFee> getBusStopsToBeAssignedToRoute(Long routeId) {
        Route route = this.routeDAO.getRoute(routeId);
        if (route == null) {
            return null;
        }
        List<BusFee> academicYearBusStops = this.routePlanDAO.getAcademicYearBusStops(route.getAcademicYear().getId());
        if (route.getBusStops() == null) {
            return academicYearBusStops;
        }
        List<BusFee> notAssignedBusstops = new ArrayList();
        for (BusFee academicYearBusStop : academicYearBusStops) {
            Boolean found = Boolean.FALSE;
            for (RouteBusStop routeBusStop : route.getBusStops()) {
                if (routeBusStop.getBusStop().getId().equals(academicYearBusStop.getId())) {
                    found = Boolean.TRUE;
                    break;
                }
            }
            if (!found.booleanValue()) {
                notAssignedBusstops.add(academicYearBusStop);
            }
        }
        return notAssignedBusstops;
    }

    public void addBusStopsInRoute(Long routeId, List<Integer> academicYearBusStopIds, Long userId) {
        if (academicYearBusStopIds != null) {
            List<RouteBusStop> routeBusStops = new ArrayList();
            Route route = new Route();
            route.setId(routeId);
            for (Integer busStopId : academicYearBusStopIds) {
                RouteBusStop routeBusStop = new RouteBusStop();
                routeBusStop.setRoute(route);
                BusFee academicYearBusStop = new BusFee();
                academicYearBusStop.setId(Long.valueOf(busStopId.longValue()));
                routeBusStop.setBusStop(academicYearBusStop);
                routeBusStops.add(routeBusStop);
            }
            this.routePlanDAO.addBusStopsInRoute(routeBusStops);
        }
    }

    public List<RouteStudent> getStudentsOfRoute(Long routeBusStopId) {
        return this.routePlanDAO.getStudentsOfRoute(routeBusStopId);
    }

    public List<RouteStudent> getRouteStudentsWithPickDropPints(Long routeBusStopId, Long academicYearId) {
        List<RouteStudent> routeStudents = this.routePlanDAO.getStudentsOfRoute(routeBusStopId);
        if (routeStudents != null) {
            for (RouteStudent routeStudent : routeStudents) {
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointDAO.getStudentPickupDropPoint(routeStudent.getStudent().getId(), academicYearId);
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
        }
        return routeStudents;
    }

    public Route getRouteOfBusStop(Long routeBusStopId) {
        return this.routePlanDAO.getRouteOfBusStop(routeBusStopId);
    }

    public List<Student> getStudentsNotAssginedToBusStopInAnyRoute(Long routeBusStopId, String routeType) {
        RouteBusStop routeBusStop = this.routeDAO.getRouteBusStop(routeBusStopId);
        List<Student> studentsOfBusStop = this.routePlanDAO.getBusFacilityAdoptedAllAdmissions(routeBusStop.getBusStop().getAcademicYear().getId(), routeBusStop.getBusStop().getBusStop().getId());
        List<RouteStudent> studentsAssignedToBusStop = this.routePlanDAO.getStudentsOfBusStop(routeBusStop.getBusStop().getBusStop().getId(), routeBusStop.getBusStop().getAcademicYear().getId(), routeType);
        if (studentsAssignedToBusStop == null) {
            return studentsOfBusStop;
        }
        if (studentsOfBusStop == null) {
            return null;
        }
        List<Student> unAssignedStudents = new ArrayList();
        for (Student busStopStudent : studentsOfBusStop) {
            Boolean found = Boolean.FALSE;
            for (RouteStudent routeStudent : studentsAssignedToBusStop) {
                if (busStopStudent.getId().equals(routeStudent.getStudent().getId())) {
                    found = Boolean.TRUE;
                    break;
                }
            }
            if (!found.booleanValue()) {
                unAssignedStudents.add(busStopStudent);
            }
        }
        return unAssignedStudents;
    }

    public void addStudentsToRoute(Long routeBusStopId, List<Integer> studentIds, Long userId) {
        if (studentIds != null) {
            List<RouteStudent> routeStudents = new ArrayList();
            UserView user = new UserView();
            user.setId(userId);
            Date systemDateTime = DateUtil.getSystemDateTime();
            RouteBusStop routeBusStop = new RouteBusStop();
            routeBusStop.setId(routeBusStopId);
            for (Integer studentId : studentIds) {
                RouteStudent routeStudent = new RouteStudent();
                routeStudent.setRouteBusStop(routeBusStop);
                Student student = new Student();
                student.setId(Long.valueOf(studentId.longValue()));
                routeStudent.setStudent(student);
                routeStudent.setCreatedBy(user);
                routeStudent.setLastUpdatedBy(user);
                routeStudent.setCreatedDateTime(systemDateTime);
                routeStudent.setLastUpdatedDateTime(systemDateTime);
                routeStudents.add(routeStudent);
            }
            this.routePlanDAO.addStdeuntsToRoute(routeBusStopId, routeStudents);
        }
    }

    public void deleteRouteStudent(Long routeStudentId) {
        this.routePlanDAO.deleteRouteStudent(routeStudentId);
    }

    public void deleteRouteBusStop(Long routeBusStopId) {
        this.routePlanDAO.deleteRouteBusStop(routeBusStopId);
    }

    public RouteBusStop getRouteBusStop(Long routeBusStopId) {
        return this.routePlanDAO.getRouteBusStop(routeBusStopId);
    }
}
