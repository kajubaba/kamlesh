package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.enquiry.dao.FollowupNextActionDAO;
import com.narendra.sams.enquiry.domain.FollowupNextAction;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FollowupNextActionDAOImpl extends HibernateDaoSupport implements FollowupNextActionDAO {
    public List<FollowupNextAction> getFollowupNextActions(Long instituteId) {
        Criteria crt = getSession().createCriteria(FollowupNextAction.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }
}
