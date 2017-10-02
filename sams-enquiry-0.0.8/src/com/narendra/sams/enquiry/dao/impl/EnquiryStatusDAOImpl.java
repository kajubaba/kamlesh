package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.enquiry.dao.EnquiryStatusDAO;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EnquiryStatusDAOImpl extends HibernateDaoSupport implements EnquiryStatusDAO {
    public List<EnquiryStatus> getAllActiveEnquiryStatus(Long instituteId) {
        Criteria crt = getSession().createCriteria(EnquiryStatus.class);
        crt.add(Restrictions.eq("institute.id", instituteId)).add(Restrictions.eq("active", Boolean.TRUE)).addOrder(Order.asc("name"));
        return crt.list();
    }

    public EnquiryStatus getStatusByName(String name, Long instituteId) {
        Criteria crt = getSession().createCriteria(EnquiryStatus.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("name", name));
        return (EnquiryStatus) crt.uniqueResult();
    }

    public List<EnquiryStatus> getAllStatusList(Long instituteId) {
        Criteria crt = getSession().createCriteria(EnquiryStatus.class);
        crt.add(Restrictions.eq("institute.id", instituteId)).addOrder(Order.asc("name"));
        return crt.list();
    }

    public EnquiryStatus getEnquiryStatus(Long statusId) {
        return (EnquiryStatus) getHibernateTemplate().get(EnquiryStatus.class, statusId);
    }

    public Long addStatus(EnquiryStatus enquiryStatus, Long userId) {
        return (Long) getHibernateTemplate().save(enquiryStatus);
    }

    public void updateStatus(EnquiryStatus enquiryStatus, Long userId) {
        EnquiryStatus persistStatus = (EnquiryStatus) getHibernateTemplate().load(EnquiryStatus.class, enquiryStatus.getId());
        persistStatus.setName(enquiryStatus.getName());
        persistStatus.setActive(enquiryStatus.getActive());
        getHibernateTemplate().update(persistStatus);
    }

    public void deleteStatus(Long statusId) {
        getHibernateTemplate().delete((EnquiryStatus) getHibernateTemplate().load(EnquiryStatus.class, statusId));
    }
}
