package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.enquiry.dao.EnquiryFollowupListDAO;
import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EnquiryFollowupListDAOImpl extends HibernateDaoSupport implements EnquiryFollowupListDAO {
    public List<EnquiryFolloup> getUpcomingEnquiryFollowups(Long academicSessionId, Date from, Date to) {
        Criteria crt = getSession().createCriteria(EnquiryFolloup.class);
        crt.createAlias("enquiry", "enquiry");
        crt.createAlias("enquiry.academicYear", "academicYear");
        if (from != null) {
            crt.add(Restrictions.ge("nextFollowupDate", from));
        }
        if (to != null) {
            crt.add(Restrictions.le("nextFollowupDate", to));
        }
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.addOrder(Order.asc("nextFollowupDate"));
        return crt.list();
    }

    public Long getUpcomingEnquiryFollowupsCount(Long academicSessionId, Date from, Date to) {
        Criteria crt = getSession().createCriteria(EnquiryFolloup.class);
        crt.createAlias("enquiry", "enquiry");
        crt.createAlias("enquiry.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        if (from != null) {
            crt.add(Restrictions.ge("nextFollowupDate", from));
        }
        if (to != null) {
            crt.add(Restrictions.le("nextFollowupDate", to));
        }
        crt.add(Restrictions.isNotNull("nextFollowupDate"));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("nextFollowupDate"), "followupCount"));
        return (Long) crt.uniqueResult();
    }
}
