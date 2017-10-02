package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.enquiry.dao.FollowupSuggestionDAO;
import com.narendra.sams.enquiry.domain.FollowupSuggestion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FollowupSuggestionDAOImpl extends HibernateDaoSupport implements FollowupSuggestionDAO {
    public List<FollowupSuggestion> getFollowupSuggestions(Long instituteId) {
        Criteria crt = getSession().createCriteria(FollowupSuggestion.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }
}
