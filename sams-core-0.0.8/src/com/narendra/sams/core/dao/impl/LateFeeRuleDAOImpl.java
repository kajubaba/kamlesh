package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.LateFeeRuleDAO;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LateFeeRuleDAOImpl extends HibernateDaoSupport implements LateFeeRuleDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public LateFeeRule loadLateFeeRuleById(Long lateFeeRuleId) {
        return (LateFeeRule) getHibernateTemplate().load(LateFeeRule.class, lateFeeRuleId);
    }

    public Long addLateFeeRule(LateFeeRule lateFeeRule, Long userId) throws DuplicateNameFoundException {
        UserView user = loadUser(userId);
        lateFeeRule.setCreatedBy(user);
        lateFeeRule.setModifiedBy(user);
        lateFeeRule.setCreatedDate(DateUtil.getSystemDateTime());
        lateFeeRule.setModifiedDate(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(lateFeeRule);
    }

    public void updateLateFeeRule(LateFeeRule lateFeeRule, Long userId) throws DuplicateNameFoundException {
        LateFeeRule loadedLateFeeRule = loadLateFeeRuleById(lateFeeRule.getId());
        loadedLateFeeRule.setName(lateFeeRule.getName());
        loadedLateFeeRule.setRule(lateFeeRule.getRule());
        loadedLateFeeRule.setDesc(lateFeeRule.getDesc());
        loadedLateFeeRule.setActive(lateFeeRule.getActive());
        loadedLateFeeRule.setModifiedDate(DateUtil.getSystemDateTime());
        loadedLateFeeRule.setModifiedBy(loadUser(userId));
        getHibernateTemplate().update(loadedLateFeeRule);
    }

    public LateFeeRule getLateFeeRule(Long lateFeeRuleId) {
        return (LateFeeRule) getHibernateTemplate().get(LateFeeRule.class, lateFeeRuleId);
    }

    public List<LateFeeRule> getAllLateFeeRules(Long instituteId) {
        Criteria criteria = getSession().createCriteria(LateFeeRule.class);
        criteria.add(Restrictions.eq("institute.id", instituteId));
        criteria.addOrder(Order.desc("createdDate"));
        return criteria.list();
    }

    public Boolean isLateFeeRuleExist(Long instituteId, String lateFeeRuleName) {
        Criteria crt = getSession().createCriteria(LateFeeRule.class);
        crt.setProjection(Projections.property("id"));
        crt.add(Restrictions.eq("name", lateFeeRuleName).ignoreCase()).add(Restrictions.eq("institute.id", instituteId));
        List<Object> list = crt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public LateFeeRule loadLateFeeRuleByName(Long instituteId, String name) {
        return (LateFeeRule) getSession().createCriteria(LateFeeRule.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("institute.id", instituteId)).uniqueResult();
    }
}
