package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.transportation.dao.RoutePlanDAO;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import com.narendra.sams.transportation.domain.RouteStudent;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RoutePlanDAOImpl extends HibernateDaoSupport implements RoutePlanDAO {
    public List<RouteBusStop> getRouteBusStops(Long routeId) {
        Criteria crt = getSession().createCriteria(RouteBusStop.class);
        crt.add(Restrictions.eq("route.id", routeId));
        return crt.list();
    }

    public List<BusFee> getAcademicYearBusStops(Long academicYearId) {
        Criteria crt = getSession().createCriteria(BusFee.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public void addBusStopsInRoute(Collection<RouteBusStop> routeBusStops) {
        getHibernateTemplate().saveOrUpdateAll(routeBusStops);
    }

    public List<RouteStudent> getStudentsOfRoute(Long routeBusStopId) {
        Criteria crt = getSession().createCriteria(RouteStudent.class);
        crt.add(Restrictions.eq("routeBusStop.id", routeBusStopId));
        return crt.list();
    }

    public Route getRouteOfBusStop(Long routeBusStopId) {
        Criteria crt = getSession().createCriteria(Route.class);
        crt.createAlias("busStops", "busStop");
        crt.add(Restrictions.eq("busStop.id", routeBusStopId));
        return (Route) crt.uniqueResult();
    }

    public List<RouteStudent> getStudentsOfBusStop(Long busStopId, Long academicYearId, String routeType) {
        Criteria crt1 = getSession().createCriteria(BusFee.class);
        crt1.add(Restrictions.eq("academicYear.id", academicYearId));
        crt1.add(Restrictions.eq("busStop.id", busStopId));
        BusFee academicYearBusStop = (BusFee) crt1.uniqueResult();
        Criteria crt = getSession().createCriteria(RouteStudent.class);
        crt.createAlias("routeBusStop", "routeBusStop");
        crt.createAlias("routeBusStop.route", "route");
        crt.createAlias("routeBusStop.busStop", "academicYearBusStop");
        crt.add(Restrictions.eq("academicYearBusStop.id", academicYearBusStop.getId()));
        crt.add(Restrictions.eq("route.type", routeType));
        return crt.list();
    }

    public List<Student> getBusFacilityAdoptedAllAdmissions(Long academicYearId, Long busStopId) {
        Set<Long> eligibleStatus = new HashSet();
        eligibleStatus.add(StudentStatus.TEMPORARY);
        eligibleStatus.add(StudentStatus.CONFIRMED);
        eligibleStatus.add(StudentStatus.TEMPORARY_RENEWAL);
        Criteria crt = getSession().createCriteria(Student.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.in("studentStatus.id", eligibleStatus));
        if (busStopId != null) {
            crt.add(Restrictions.eq("busStop.id", busStopId));
        }
        crt.addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName"));
        return crt.list();
    }

    public void addStdeuntsToRoute(Long routeBusStopId, Collection<RouteStudent> routeStudents) {
        RouteBusStop routeBusStop = (RouteBusStop) getHibernateTemplate().load(RouteBusStop.class, routeBusStopId);
        for (RouteStudent routeStudent : routeStudents) {
            routeStudent.setStudent((Student) getHibernateTemplate().load(Student.class, routeStudent.getStudent().getId()));
            routeStudent.setRouteBusStop(routeBusStop);
        }
        getHibernateTemplate().saveOrUpdateAll(routeStudents);
    }

    public void deleteRouteStudent(Long routeStudentId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(RouteStudent.class, routeStudentId));
    }

    public void deleteRouteBusStop(Long routeBusStopId) {
        Criteria crt = getSession().createCriteria(RouteStudent.class);
        crt.add(Restrictions.eq("routeBusStop.id", routeBusStopId));
        List<RouteStudent> routeStudents = crt.list();
        getHibernateTemplate().delete(getHibernateTemplate().load(RouteBusStop.class, routeBusStopId));
        if (routeStudents != null) {
            for (RouteStudent routeStudent : routeStudents) {
                getHibernateTemplate().delete(routeStudent);
            }
        }
    }

    public RouteStudent getStudentAssociatedWithRoute(Long studentId, String routeType) {
        Criteria crt = getSession().createCriteria(RouteStudent.class);
        crt.createAlias("routeBusStop", "routeBusStop").createAlias("routeBusStop.route", "route");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("route.type", routeType));
        return (RouteStudent) crt.uniqueResult();
    }

    public void deleteRouteStudent(RouteStudent routeStudent) {
        getHibernateTemplate().delete(routeStudent);
    }

    public RouteBusStop getRouteBusStop(Long routeBusStopId) {
        return (RouteBusStop) getHibernateTemplate().get(RouteBusStop.class, routeBusStopId);
    }
}
