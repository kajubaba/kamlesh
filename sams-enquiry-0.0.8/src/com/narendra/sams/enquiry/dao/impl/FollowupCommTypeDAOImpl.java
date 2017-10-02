package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.enquiry.dao.FollowupCommTypeDAO;
import com.narendra.sams.enquiry.domain.FollowupCommType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FollowupCommTypeDAOImpl extends HibernateDaoSupport implements FollowupCommTypeDAO {
    public List<FollowupCommType> getFollowupTypes(Long instituteId) {
        Criteria crt = getSession().createCriteria(FollowupCommType.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }
}
