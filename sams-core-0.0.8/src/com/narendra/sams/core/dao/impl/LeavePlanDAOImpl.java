package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.LeavePlanDAO;
import com.narendra.sams.core.domain.LeavePlan;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LeavePlanDAOImpl extends HibernateDaoSupport implements LeavePlanDAO {
    public void addLeavePlan(LeavePlan leavePlan, Long userId) {
        leavePlan.setCreatedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        leavePlan.setCreatedDateTime(DateUtil.getSystemDateTime());
        getHibernateTemplate().save(leavePlan);
    }

    public LeavePlan getLeavePlan(Long leavePlanId) {
        return (LeavePlan) getHibernateTemplate().get(LeavePlan.class, leavePlanId);
    }

    public void updateLeavePlanReason(Long leavePlanId, String reason) {
        LeavePlan dbLeavePlan = (LeavePlan) getHibernateTemplate().load(LeavePlan.class, leavePlanId);
        dbLeavePlan.setReason(reason);
        getHibernateTemplate().update(dbLeavePlan);
    }

    public void deleteLeavePlan(Long leavePlanId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(LeavePlan.class, leavePlanId));
    }

    public List<LeavePlan> getAllLeavePlans(Long instituteId) {
        Criteria crt = getSession().createCriteria(LeavePlan.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.desc("leaveOn"));
        return crt.list();
    }
}
