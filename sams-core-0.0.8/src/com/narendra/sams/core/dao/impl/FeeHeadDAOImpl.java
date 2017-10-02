package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.dao.FeeHeadDAO;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FeeHeadDAOImpl extends HibernateDaoSupport implements FeeHeadDAO {
    private UserViewDAO userViewDAO;

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public Long addFeeHead(FeeHead feeHead, Long userId) {
        UserView user = this.userViewDAO.loadUser(userId);
        Date date = DateUtil.getSystemDateTime();
        feeHead.setCreatedBy(user);
        feeHead.setModifiedBy(user);
        feeHead.setCreatedDate(date);
        feeHead.setModifiedDate(date);
        return (Long) getHibernateTemplate().save(feeHead);
    }

    public void updateFeeHead(FeeHead feeHead, Long userId) {
        FeeHead persisitFeeHead = loadById(feeHead.getId());
        persisitFeeHead.setName(feeHead.getName());
        persisitFeeHead.setActive(feeHead.getActive());
        persisitFeeHead.setModifiedBy(this.userViewDAO.loadUser(userId));
        persisitFeeHead.setModifiedDate(DateUtil.getSystemDateTime());
        getHibernateTemplate().update(persisitFeeHead);
    }

    public FeeHead loadById(Long feeHeadId) {
        return (FeeHead) getHibernateTemplate().load(FeeHead.class, feeHeadId);
    }

    public List<FeeHead> getAllActiveFeeHeads(Long instituteId) {
        Criteria feeHeadCrt = getSession().createCriteria(FeeHead.class);
        feeHeadCrt.add(Restrictions.eq("institute.id", instituteId)).add(Restrictions.eq("active", Boolean.TRUE)).add(Restrictions.eq("systemFeeHead", Boolean.FALSE)).addOrder(Order.asc("displayName"));
        return feeHeadCrt.list();
    }

    public FeeHead getFeeHead(Long feeHeadId) {
        return (FeeHead) getHibernateTemplate().get(FeeHead.class, feeHeadId);
    }

    public Boolean isFeeHeadNameExist(Long instituteId, String name) {
        Criteria feeHeadCrt = getSession().createCriteria(FeeHead.class);
        feeHeadCrt.setProjection(Projections.property("id"));
        feeHeadCrt.add(Restrictions.eq("institute.id", instituteId)).add(Restrictions.eq("name", name));
        List<Object> list = feeHeadCrt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public FeeHead loadByName(Long instituteId, String name) {
        return (FeeHead) getSession().createCriteria(FeeHead.class).add(Restrictions.eq("institute.id", instituteId)).add(Restrictions.eq("name", name)).uniqueResult();
    }

    public FeeHead getBusFeeHead(Long instituteId) {
        Criteria crt = getSession().createCriteria(FeeHead.class);
        crt.add(Restrictions.eq("name", "Bus Fee").ignoreCase());
        crt.add(Restrictions.isNull("active")).add(Restrictions.eq("institute.id", instituteId));
        return (FeeHead) crt.uniqueResult();
    }

    public FeeHead getLateFeeHead(Long instituteId) {
        Criteria crt = getSession().createCriteria(FeeHead.class);
        crt.add(Restrictions.eq("name", "Late Fee").ignoreCase());
        crt.add(Restrictions.isNull("active")).add(Restrictions.eq("institute.id", instituteId));
        return (FeeHead) crt.uniqueResult();
    }

    public List<FeeHead> getAllFeeHeads(Long instituteId) {
        Criteria feeHeadCrt = getSession().createCriteria(FeeHead.class);
        feeHeadCrt.add(Restrictions.eq("institute.id", instituteId)).add(Restrictions.eq("systemFeeHead", Boolean.FALSE)).addOrder(Order.asc("displayName"));
        return feeHeadCrt.list();
    }
}
