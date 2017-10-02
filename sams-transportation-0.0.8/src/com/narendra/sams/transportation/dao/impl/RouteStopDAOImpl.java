package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.dao.RouteStopDAO;
import com.narendra.sams.transportation.domain.BusStopPoint;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RouteStopDAOImpl extends HibernateDaoSupport implements RouteStopDAO {
    public List<BusFee> getActiveBusStopsInAcademicYear(Long academicYearId) {
        Criteria crt = getSession().createCriteria(BusFee.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public Long getBusStopCount(Long academicYearId) {
        Criteria crt = getSession().createCriteria(BusStop.class);
        crt.createAlias("busFees", "busFee");
        crt.createAlias("busFee.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("id"), "busStopCount"));
        return (Long) crt.uniqueResult();
    }

    public List<BusStopPoint> getBusStopPickUpDropPoints(Long busFeeId) {
        Criteria crt = getSession().createCriteria(BusStopPoint.class);
        crt.add(Restrictions.eq("busFee.id", busFeeId));
        return crt.list();
    }

    public void addBusStopPickUpDropPoint(BusStopPoint busStopPoint, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        busStopPoint.setCreatedBy(user);
        busStopPoint.setLastUpdatedBy(user);
        Date currentDateTime = DateUtil.getSystemDateTime();
        busStopPoint.setCreatedDateTime(currentDateTime);
        busStopPoint.setLastUpdatedDateTime(currentDateTime);
        getHibernateTemplate().save(busStopPoint);
    }

    public BusStop getBusStop(Long academicYearBusStopId) {
        Criteria crt = getSession().createCriteria(BusStop.class);
        crt.createAlias("busFees", "busFee");
        crt.add(Restrictions.eq("busFee.id", academicYearBusStopId));
        return (BusStop) crt.uniqueResult();
    }

    public List<BusStopPoint> getBusStopPickUpDropPoints(Long academicYearId, Long busStopId) {
        Criteria crt = getSession().createCriteria(BusStopPoint.class);
        crt.createAlias("busFee", "busFee");
        crt.createAlias("busFee.academicYear", "academicYear");
        crt.createAlias("busFee.busStop", "busStop");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("busStop.id", busStopId));
        return crt.list();
    }

    public BusFee getAcademicYearBusStop(Long academicYearId, Long busStopId) {
        Criteria crt = getSession().createCriteria(BusFee.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("busStop.id", busStopId));
        return (BusFee) crt.uniqueResult();
    }
}
