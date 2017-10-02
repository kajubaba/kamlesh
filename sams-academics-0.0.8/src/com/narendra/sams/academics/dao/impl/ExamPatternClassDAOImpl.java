package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.ExamPatternClassDAO;
import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.exam.domain.EvaluationSchemeClass;
import com.narendra.sams.core.domain.AcademicYearClass;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ExamPatternClassDAOImpl extends HibernateDaoSupport implements ExamPatternClassDAO {
    public void addClassesInExamPattern(Collection<Integer> classes, Long examPatternId) {
        for (Integer classId : classes) {
            EvaluationSchemeClass evaluationSchemeClass = new EvaluationSchemeClass();
            EvaluationScheme evaluationScheme = (EvaluationScheme) getHibernateTemplate().load(EvaluationScheme.class, examPatternId);
            evaluationSchemeClass.setAcademicYearClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, Long.valueOf(classId.longValue())));
            evaluationSchemeClass.setEvaluationScheme(evaluationScheme);
            getHibernateTemplate().save(evaluationSchemeClass);
        }
    }

    public List<EvaluationSchemeClass> getExamPatternClasses(Long examPatternId) {
        Criteria crt = getSession().createCriteria(EvaluationSchemeClass.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.add(Restrictions.eq("evaluationScheme.id", examPatternId));
        crt.addOrder(Order.asc("academicYearClass.displayName"));
        return crt.list();
    }

    public List<EvaluationSchemeClass> getAllClassesAddedInExamPattern(Long academicYearId) {
        Criteria crt = getSession().createCriteria(EvaluationSchemeClass.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("evaluationScheme", "evaluationScheme");
        crt.createAlias("evaluationScheme.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.addOrder(Order.asc("academicYearClass.displayName"));
        return crt.list();
    }

    public void removeClassFromExamPattern(Long examPatternClassId) {
        getHibernateTemplate().delete(getHibernateTemplate().get(EvaluationSchemeClass.class, examPatternClassId));
    }

    public EvaluationSchemeClass getExamPatternClass(Long academicYearClassId) {
        Criteria crt = getSession().createCriteria(EvaluationSchemeClass.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        return (EvaluationSchemeClass) crt.uniqueResult();
    }
}
