package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.enquiry.dao.FollowupCommConclusionDAO;
import com.narendra.sams.enquiry.domain.FollowupCommConclusion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FollowupCommConclusionDAOImpl extends HibernateDaoSupport implements FollowupCommConclusionDAO {
    public List<FollowupCommConclusion> getFollowupConclusions(Long instituteId) {
        Criteria crt = getSession().createCriteria(FollowupCommConclusion.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }
}
