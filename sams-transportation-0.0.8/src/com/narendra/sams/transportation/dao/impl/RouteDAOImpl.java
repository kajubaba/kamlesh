package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.dao.RouteDAO;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RouteDAOImpl extends HibernateDaoSupport implements RouteDAO {
    public List<Route> getAllRoutes(Long academicYearId) {
        Criteria crt = getSession().createCriteria(Route.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public Long getRouteCount(Long academicYearId) {
        Criteria crt = getSession().createCriteria(Route.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("id"), "routeCount"));
        return (Long) crt.uniqueResult();
    }

    public Long addRoute(Route route, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        route.setCreatedBy(user);
        route.setLastUpdatedBy(user);
        route.setCreatedDateTime(DateUtil.getSystemDateTime());
        route.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(route);
    }

    public void updateRoute(Route route, Long userId) {
        Route loadedRoute = (Route) getHibernateTemplate().load(Route.class, route.getId());
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        loadedRoute.setFrom(route.getFrom());
        loadedRoute.setTo(route.getTo());
        loadedRoute.setName(route.getName());
        loadedRoute.setType(route.getType());
        loadedRoute.setPlannedStudents(route.getPlannedStudents());
        loadedRoute.setLastUpdatedBy(user);
        loadedRoute.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
        getHibernateTemplate().update(loadedRoute);
    }

    public Route getRoute(Long routeId) {
        return (Route) getHibernateTemplate().get(Route.class, routeId);
    }

    public RouteBusStop getRouteBusStop(Long routeBusStopId) {
        return (RouteBusStop) getHibernateTemplate().get(RouteBusStop.class, routeBusStopId);
    }
}
