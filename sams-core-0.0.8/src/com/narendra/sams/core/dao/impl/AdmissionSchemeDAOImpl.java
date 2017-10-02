package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.AdmissionSchemeDAO;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdmissionSchemeDAOImpl extends HibernateDaoSupport implements AdmissionSchemeDAO {
    public AdmissionScheme getAdmissionScheme(Long admissionSchemeId) {
        return (AdmissionScheme) getHibernateTemplate().get(AdmissionScheme.class, admissionSchemeId);
    }

    public Long addAdmissionScheme(AdmissionScheme admissionScheme, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        admissionScheme.setCreatedBy(user);
        admissionScheme.setModifiedBy(user);
        admissionScheme.setCreatedDateTime(DateUtil.getSystemDateTime());
        admissionScheme.setModifiedDateTime(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(admissionScheme);
    }

    public void updateAdmissionScheme(AdmissionScheme admissionScheme, Long userId) {
        AdmissionScheme persistAdmissionScheme = (AdmissionScheme) getHibernateTemplate().load(AdmissionScheme.class, admissionScheme.getId());
        persistAdmissionScheme.setModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        persistAdmissionScheme.setModifiedDateTime(DateUtil.getSystemDateTime());
        persistAdmissionScheme.setActive(admissionScheme.getActive());
        persistAdmissionScheme.setName(admissionScheme.getName());
        getHibernateTemplate().save(persistAdmissionScheme);
    }

    public List<AdmissionScheme> getAdmissionSchemesOfAcademicSession(Long academicYearId) {
        Criteria crt = getSession().createCriteria(AdmissionScheme.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public List<AdmissionScheme> getAdmissionSchemes(Long instituteId) {
        Criteria crt = getSession().createCriteria(AdmissionScheme.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public List<AdmissionScheme> getActiveAdmissionSchemes(Long instituteId) {
        Criteria crt = getSession().createCriteria(AdmissionScheme.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public AdmissionScheme getAdmissionSchemeByName(String schemeName, Long instituteId) {
        Criteria crt = getSession().createCriteria(AdmissionScheme.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("name", schemeName));
        return (AdmissionScheme) crt.uniqueResult();
    }
}
