package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.BusStopDAO;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.BusStopTranslation;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusStopDAOImpl extends HibernateDaoSupport implements BusStopDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public Long addBusStop(BusStop busStop, Long userId) {
        UserView user = loadUser(userId);
        Date date = DateUtil.getSystemDateTime();
        busStop.setCreatedBy(user);
        busStop.setModifiedBy(user);
        busStop.setCreatedDate(date);
        busStop.setModifiedDate(date);
        return (Long) getHibernateTemplate().save(busStop);
    }

    public void updateBusStop(BusStop busStop, Long userId) {
        BusStop persistBusStop = loadBusStopById(busStop.getId());
        persistBusStop.setName(busStop.getName());
        persistBusStop.setNameInOtherLang(busStop.getNameInOtherLang());
        persistBusStop.setDistance(busStop.getDistance());
        persistBusStop.setActive(busStop.getActive());
        persistBusStop.setModifiedDate(DateUtil.getSystemDateTime());
        persistBusStop.setModifiedBy(loadUser(userId));
        getHibernateTemplate().update(persistBusStop);
    }

    public BusStop getBusStop(Long busStopId) {
        return (BusStop) getHibernateTemplate().get(BusStop.class, busStopId);
    }

    public List<BusStop> getAllBusStops(Long instituteId) {
        Criteria crt = getSession().createCriteria(BusStop.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public List<BusStop> getActiveBusStops(Long instituteId) {
        Criteria crt = getSession().createCriteria(BusStop.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public Boolean isBusStopExist(Long instituteId, String name) {
        Criteria crt = getSession().createCriteria(BusStop.class);
        crt.setProjection(Projections.property("id"));
        crt.add(Restrictions.eq("name", name).ignoreCase()).add(Restrictions.eq("institute.id", instituteId));
        List<Object> list = crt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public BusStop loadBusStopByName(Long instituteId, String name) {
        return (BusStop) getSession().createCriteria(BusStop.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("institute.id", instituteId)).uniqueResult();
    }

    private BusStop loadBusStopById(Long busStopId) {
        return (BusStop) getHibernateTemplate().load(BusStop.class, busStopId);
    }

    public List<BusStop> getAllBusStopsOfAcademicYear(Long academicYearId) {
        Criteria crt = getSession().createCriteria(BusStop.class);
        crt.createAlias("busFees", "busFee");
        crt.createAlias("busFee.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public void updateBusStoptranslations(Collection<BusStopTranslation> busStopTranslations) {
        for (BusStopTranslation busStopTranslation : busStopTranslations) {
            BusStop busStop = (BusStop) getHibernateTemplate().load(BusStop.class, busStopTranslation.getBusStopId());
            busStop.setNameInOtherLang(busStopTranslation.getBusStopNameInOtherLanguage());
            getHibernateTemplate().update(busStop);
        }
    }
}
