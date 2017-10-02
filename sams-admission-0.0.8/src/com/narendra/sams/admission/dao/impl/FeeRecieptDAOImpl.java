package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.FeeRecieptDAO;
import com.narendra.sams.admission.domain.FeeRecieptHeader;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FeeRecieptDAOImpl extends HibernateDaoSupport implements FeeRecieptDAO {
    public List<FeeRecieptHeader> getActiveFeeRecieptHeaders(Long instituteId) {
        Criteria crt = getSession().createCriteria(FeeRecieptHeader.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("isActive", Boolean.TRUE)).addOrder(Order.asc("displayName"));
        return crt.list();
    }

    public FeeRecieptHeader getFeeRecieptHeader(Long id) {
        return (FeeRecieptHeader) getHibernateTemplate().get(FeeRecieptHeader.class, id);
    }

    public FeeRecieptHeader getDefaultHeader(Long instituteId) {
        Criteria crt = getSession().createCriteria(FeeRecieptHeader.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("isActive", Boolean.TRUE));
        crt.add(Restrictions.eq("isDefault", Boolean.TRUE));
        return (FeeRecieptHeader) crt.uniqueResult();
    }
}
