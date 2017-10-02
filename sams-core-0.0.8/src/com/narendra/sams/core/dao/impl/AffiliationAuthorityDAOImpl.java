package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.dao.AffiliationAuthorityDAO;
import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AffiliationAuthorityDAOImpl extends HibernateDaoSupport implements AffiliationAuthorityDAO {
    private UserViewDAO userViewDAO;

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public List<AffiliationAuthority> getAllAffiliationAuthorities(Long institueId) {
        return getSession().createCriteria(AffiliationAuthority.class).add(Restrictions.eq("institute.id", institueId)).addOrder(Order.asc("displayName")).list();
    }

    public List<AffiliationAuthority> getAllActive(Long institueId) {
        return getSession().createCriteria(AffiliationAuthority.class).add(Restrictions.eq("institute.id", institueId)).add(Restrictions.eq("active", Boolean.TRUE)).addOrder(Order.asc("displayName")).list();
    }

    public AffiliationAuthority getAffiliationAuthority(Long id) {
        return (AffiliationAuthority) getHibernateTemplate().get(AffiliationAuthority.class, id);
    }

    public AffiliationAuthority loadAffiliationAuthority(Long id) {
        return (AffiliationAuthority) getHibernateTemplate().load(AffiliationAuthority.class, id);
    }

    public Long addAffiliationAuthority(AffiliationAuthority affiliationAuthority, Long userId) throws DuplicateNameFoundException {
        UserView user = this.userViewDAO.loadUser(userId);
        Date date = DateUtil.getSystemDateTime();
        affiliationAuthority.setCreatedBy(user);
        affiliationAuthority.setModifiedBy(user);
        affiliationAuthority.setCreatedDate(date);
        affiliationAuthority.setModifiedDate(date);
        return (Long) getHibernateTemplate().save(affiliationAuthority);
    }

    public Boolean isAffiliationAuthorityDisplayNameExists(Long instituteId, String displayName) {
        Criteria aaCrt = getSession().createCriteria(AffiliationAuthority.class);
        aaCrt.setProjection(Projections.property("id"));
        aaCrt.add(Restrictions.eq("displayName", displayName)).add(Restrictions.eq("institute.id", instituteId));
        List<Object> list = aaCrt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void updateAffiliationAuthority(AffiliationAuthority affiliationAuthority, Long userId) throws DuplicateNameFoundException {
        AffiliationAuthority persistAffiliationAuthority = loadAffiliationAuthority(affiliationAuthority.getId());
        persistAffiliationAuthority.setName(affiliationAuthority.getName());
        persistAffiliationAuthority.setDisplayName(affiliationAuthority.getDisplayName());
        persistAffiliationAuthority.setActive(affiliationAuthority.getActive());
        persistAffiliationAuthority.setModifiedDate(DateUtil.getSystemDateTime());
        persistAffiliationAuthority.setModifiedBy(this.userViewDAO.loadUser(userId));
        getHibernateTemplate().update(persistAffiliationAuthority);
    }

    public AffiliationAuthority getAffiliationAuthorityByDisplayName(Long instituteId, String displayName) {
        return (AffiliationAuthority) getSession().createCriteria(AffiliationAuthority.class).add(Restrictions.eq("displayName", displayName)).add(Restrictions.eq("institute.id", instituteId)).uniqueResult();
    }
}
