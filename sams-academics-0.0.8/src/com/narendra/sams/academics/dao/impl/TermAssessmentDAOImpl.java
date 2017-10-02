package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.TermAssessmentDAO;
import com.narendra.sams.academics.exam.domain.TermAssessment;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TermAssessmentDAOImpl extends HibernateDaoSupport implements TermAssessmentDAO {
    public List<TermAssessment> getTermAssessments(Long termId) {
        Criteria crt = getSession().createCriteria(TermAssessment.class);
        crt.add(Restrictions.eq("evaluationTerm.id", termId));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public void updateTermAssessment(TermAssessment termAssessment, Long userId) {
        TermAssessment persistAseessment = (TermAssessment) getHibernateTemplate().load(TermAssessment.class, termAssessment.getId());
        persistAseessment.setName(termAssessment.getName());
        persistAseessment.setDisplayName(termAssessment.getDisplayName());
        persistAseessment.setDisplayOrder(termAssessment.getDisplayOrder());
        persistAseessment.setWeightageInAcademicSession(termAssessment.getWeightageInAcademicSession());
        persistAseessment.setMaxMarks(termAssessment.getMaxMarks());
        getHibernateTemplate().update(persistAseessment);
    }

    public void addTermAssessment(TermAssessment termAssessment, Long userId) {
        getHibernateTemplate().save(termAssessment);
    }

    public void deleteTermAssessment(Long termAssessmentId) {
        getHibernateTemplate().delete(getHibernateTemplate().get(TermAssessment.class, termAssessmentId));
    }

    public TermAssessment getTermAssessment(Long id) {
        return (TermAssessment) getHibernateTemplate().get(TermAssessment.class, id);
    }
}
