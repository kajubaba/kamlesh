package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.AcademicYearAdmissionSchemeDAO;
import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AcademicYearAdmissionSchemeDAOImpl extends HibernateDaoSupport implements AcademicYearAdmissionSchemeDAO {
    public AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long id) {
        return (AcademicYearAdmissionScheme) getHibernateTemplate().get(AcademicYearAdmissionScheme.class, id);
    }

    public void addAdmissionSchemes(List<AcademicYearAdmissionScheme> academicYearAdmissionSchemes) {
        getHibernateTemplate().saveOrUpdateAll(academicYearAdmissionSchemes);
    }

    public void deleteAdmissionScheme(Long admissionSchemeId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(AcademicYearAdmissionScheme.class, admissionSchemeId));
    }

    public List<AcademicYearAdmissionScheme> getAdmissionSchemes(Long academicSessionId) {
        Criteria crt = getSession().createCriteria(AcademicYearAdmissionScheme.class);
        crt.createAlias("admissionScheme", "admissionScheme");
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.addOrder(Order.asc("admissionScheme.name"));
        return crt.list();
    }

    public AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long academicSessionId, Long admissionSchemeId) {
        Criteria crt = getSession().createCriteria(AcademicYearAdmissionScheme.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.add(Restrictions.eq("admissionScheme.id", admissionSchemeId));
        return (AcademicYearAdmissionScheme) crt.uniqueResult();
    }

    public void saveSchemeDetails(List<AcademicSessionAdmisionSchemeDetail> details, Long academicSessionSchemeId) {
        AcademicYearAdmissionScheme academicYearAdmissionScheme = (AcademicYearAdmissionScheme) getHibernateTemplate().load(AcademicYearAdmissionScheme.class, academicSessionSchemeId);
        for (AcademicSessionAdmisionSchemeDetail academicSessionAdmisionSchemeDetail : details) {
            if (academicSessionAdmisionSchemeDetail.getId() == null) {
                academicSessionAdmisionSchemeDetail.setAcademicYearAdmissionScheme(academicYearAdmissionScheme);
                getHibernateTemplate().save(academicSessionAdmisionSchemeDetail);
            } else {
                AcademicSessionAdmisionSchemeDetail persisitDetail = (AcademicSessionAdmisionSchemeDetail) getHibernateTemplate().load(AcademicSessionAdmisionSchemeDetail.class, academicSessionAdmisionSchemeDetail.getId());
                persisitDetail.setDiscount(academicSessionAdmisionSchemeDetail.getDiscount());
                getHibernateTemplate().saveOrUpdate(persisitDetail);
            }
        }
    }
}
