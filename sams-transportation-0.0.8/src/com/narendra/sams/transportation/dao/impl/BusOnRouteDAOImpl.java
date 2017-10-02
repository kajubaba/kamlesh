package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.transportation.dao.BusOnRouteDAO;
import com.narendra.sams.transportation.domain.AcademicYearVehicle;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.domain.VehicleRoute;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusOnRouteDAOImpl extends HibernateDaoSupport implements BusOnRouteDAO {
    public Route getBusRoute(Long academicSessionBusId, String routeType) {
        Criteria crt = getSession().createCriteria(Route.class);
        crt.createAlias("academicYearVehicle", "academicYearVehicle");
        crt.add(Restrictions.eq("type", routeType));
        crt.add(Restrictions.eq("academicYearVehicle.id", academicSessionBusId));
        return (Route) crt.uniqueResult();
    }

    public VehicleRoute getVehicleAssignedToRoute(Long routeId) {
        Criteria crt = getSession().createCriteria(VehicleRoute.class);
        crt.add(Restrictions.eq("route.id", routeId));
        return (VehicleRoute) crt.uniqueResult();
    }

    public Long updateVehicleOnRoute(Long routeId, Long academicSessionVehicleId) {
        Route route = (Route) getHibernateTemplate().load(Route.class, routeId);
        route.setAcademicYearVehicle((AcademicYearVehicle) getHibernateTemplate().load(AcademicYearVehicle.class, academicSessionVehicleId));
        getHibernateTemplate().update(route);
        return null;
    }

    public List<RouteStudent> getRouteStudents(Long routeId) {
        Criteria crt = getSession().createCriteria(RouteStudent.class);
        crt.createAlias("routeBusStop", "routeBusStop");
        crt.createAlias("routeBusStop.route", "route");
        crt.add(Restrictions.eq("route.id", routeId));
        return crt.list();
    }

    public void deleteVehicleRoute(Long vehicleRouteId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(VehicleRoute.class, vehicleRouteId));
    }

    public VehicleRoute getVehicleRoute(Long academicSessionVehicleId, String routeType) {
        Criteria crt = getSession().createCriteria(VehicleRoute.class);
        crt.createAlias("academicYearVehicle", "academicYearVehicle");
        crt.createAlias("route", "route");
        crt.add(Restrictions.eq("route.type", routeType));
        crt.add(Restrictions.eq("academicYearVehicle.id", academicSessionVehicleId));
        return (VehicleRoute) crt.uniqueResult();
    }

    public List<Route> getStudentRoutes(Long studentId, Long academicSessionId) {
        Criteria crt = getSession().createCriteria(Route.class);
        crt.createAlias("busStops", "busStop");
        crt.createAlias("busStop.busStopStudents", "busStopStudent");
        crt.createAlias("busStopStudent.student", "student");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        return crt.list();
    }

    public List<Route> getBusRoutes(Long academicSessionBusId, String routeType) {
        Criteria crt = getSession().createCriteria(Route.class);
        crt.createAlias("academicYearVehicle", "academicYearVehicle");
        crt.add(Restrictions.eq("type", routeType));
        crt.add(Restrictions.eq("academicYearVehicle.id", academicSessionBusId));
        return crt.list();
    }
}
