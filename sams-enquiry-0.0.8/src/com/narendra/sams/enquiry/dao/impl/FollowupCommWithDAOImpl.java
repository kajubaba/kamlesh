package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.enquiry.dao.FollowupCommWithDAO;
import com.narendra.sams.enquiry.domain.FollowupCommWith;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FollowupCommWithDAOImpl extends HibernateDaoSupport implements FollowupCommWithDAO {
    public List<FollowupCommWith> getCommunicationWith(Long instituteId) {
        Criteria crt = getSession().createCriteria(FollowupCommWith.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }
}
