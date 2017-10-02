package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.InstituteDAO;
import com.narendra.sams.core.domain.Institute;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class InstituteDAOImpl extends HibernateDaoSupport implements InstituteDAO {
    public Institute getDefaultInstitute(Long companyId) {
        Criteria crt = getSession().createCriteria(Institute.class);
        crt.add(Restrictions.eq("company.id", companyId));
        crt.add(Restrictions.eq("defaultInstitute", Boolean.TRUE));
        return (Institute) crt.uniqueResult();
    }

    public List<Institute> getInstitutes(Long companyId) {
        Criteria crt = getSession().createCriteria(Institute.class);
        crt.add(Restrictions.eq("company.id", companyId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public Institute getInstitute(Long instituteId) {
        return (Institute) getHibernateTemplate().get(Institute.class, instituteId);
    }

    public void updateInstituteDetails(Institute institute) {
        Institute persistInstitute = (Institute) getHibernateTemplate().load(Institute.class, institute.getId());
        persistInstitute.setName(institute.getName());
        persistInstitute.setAddress(institute.getAddress());
        persistInstitute.setLine2(institute.getLine2());
        persistInstitute.setAffiliationNo(institute.getAffiliationNo());
        getHibernateTemplate().update(persistInstitute);
    }
}
