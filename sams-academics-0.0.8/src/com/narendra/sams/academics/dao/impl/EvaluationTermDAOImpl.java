package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.EvaluationTermDAO;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EvaluationTermDAOImpl extends HibernateDaoSupport implements EvaluationTermDAO {
    public List<EvaluationTerm> getEvaluationTerms(Long evaluationTypeId) {
        Criteria crt = getSession().createCriteria(EvaluationTerm.class);
        crt.add(Restrictions.eq("evaluationType.id", evaluationTypeId));
        crt.addOrder(Order.asc("termName"));
        return crt.list();
    }

    public void addEvaluationTerm(EvaluationTerm evaluationTerm, Long userId) {
        getHibernateTemplate().save(evaluationTerm);
    }

    public void updateEvaluationTerm(EvaluationTerm evaluationTerm, Long userId) {
        EvaluationTerm persistEvalTerm = (EvaluationTerm) getHibernateTemplate().load(EvaluationTerm.class, evaluationTerm.getId());
        persistEvalTerm.setTermName(evaluationTerm.getTermName());
        persistEvalTerm.setTermDisplayName(evaluationTerm.getTermDisplayName());
        persistEvalTerm.setDisplayOrder(evaluationTerm.getDisplayOrder());
        persistEvalTerm.setWeightageInFinalAssessment(evaluationTerm.getWeightageInFinalAssessment());
        persistEvalTerm.setWorkingDays(evaluationTerm.getWorkingDays());
        getHibernateTemplate().update(persistEvalTerm);
    }

    public void deleteEvaluationTerm(Long termId) {
        getHibernateTemplate().delete(getHibernateTemplate().get(EvaluationTerm.class, termId));
    }

    public EvaluationTerm getEvaluationTerm(Long id) {
        return (EvaluationTerm) getHibernateTemplate().get(EvaluationTerm.class, id);
    }
}
