package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.AssessmentTypeDAO;
import com.narendra.sams.academics.domain.GradeScale;
import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AssessmentTypeDAOImpl extends HibernateDaoSupport implements AssessmentTypeDAO {
    public List<EvaluationType> getEvaluationTypes(Long academicYearClassId) {
        Criteria crt1 = getSession().createCriteria(EvaluationScheme.class);
        crt1.createAlias("appliedOnClasses", "appliedOnClasses");
        crt1.createAlias("appliedOnClasses.academicYearClass", "academicYearClass");
        crt1.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        EvaluationScheme classEvaluationScheme = (EvaluationScheme) crt1.uniqueResult();
        if (classEvaluationScheme == null) {
            return null;
        }
        Criteria crt2 = getSession().createCriteria(EvaluationType.class);
        crt2.add(Restrictions.eq("evaluationScheme.id", classEvaluationScheme.getId()));
        return crt2.list();
    }

    public EvaluationType getScholasticEvaluationType(Long academicYearClassId) {
        Criteria crt1 = getSession().createCriteria(EvaluationScheme.class);
        crt1.createAlias("appliedOnClasses", "appliedOnClasses");
        crt1.createAlias("appliedOnClasses.academicYearClass", "academicYearClass");
        crt1.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        EvaluationScheme classEvaluationScheme = (EvaluationScheme) crt1.uniqueResult();
        if (classEvaluationScheme == null) {
            return null;
        }
        Criteria crt2 = getSession().createCriteria(EvaluationType.class);
        crt2.add(Restrictions.eq("evaluationScheme.id", classEvaluationScheme.getId()));
        crt2.add(Restrictions.eq("isScholastic", Boolean.TRUE));
        return (EvaluationType) crt2.uniqueResult();
    }

    public EvaluationType getCoScholasticEvaluationType(Long academicYearClassId) {
        Criteria crt1 = getSession().createCriteria(EvaluationScheme.class);
        crt1.createAlias("appliedOnClasses", "appliedOnClasses");
        crt1.createAlias("appliedOnClasses.academicYearClass", "academicYearClass");
        crt1.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        EvaluationScheme classEvaluationScheme = (EvaluationScheme) crt1.uniqueResult();
        if (classEvaluationScheme == null) {
            return null;
        }
        Criteria crt2 = getSession().createCriteria(EvaluationType.class);
        crt2.add(Restrictions.eq("evaluationScheme.id", classEvaluationScheme.getId()));
        crt2.add(Restrictions.eq("isScholastic", Boolean.FALSE));
        return (EvaluationType) crt2.uniqueResult();
    }

    public EvaluationType getScholasticEvaluationTypeOfExamPattern(Long examPatternId) {
        Criteria crt = getSession().createCriteria(EvaluationType.class);
        crt.add(Restrictions.eq("isScholastic", Boolean.TRUE));
        crt.add(Restrictions.eq("evaluationScheme.id", examPatternId));
        return (EvaluationType) crt.uniqueResult();
    }

    public EvaluationType getCoScholasticEvaluationTypeOfExamPattern(Long examPatternId) {
        Criteria crt = getSession().createCriteria(EvaluationType.class);
        crt.add(Restrictions.eq("isScholastic", Boolean.FALSE));
        crt.add(Restrictions.eq("evaluationScheme.id", examPatternId));
        return (EvaluationType) crt.uniqueResult();
    }

    public void updateAssessmentGradeAndScoringMethod(Long assessmentTypeId, Long gradeScaleId, String scoringMethod, String gradeCalculationMethod, Long maxMarks, Boolean isFASABasedExam, Boolean isTermBasedAssessment, Boolean useAssessmentWeightageAsMaxMarks) {
        EvaluationType evaluationType = (EvaluationType) getHibernateTemplate().load(EvaluationType.class, assessmentTypeId);
        if (gradeScaleId != null) {
            evaluationType.setGradeScale((GradeScale) getHibernateTemplate().load(GradeScale.class, gradeScaleId));
        } else {
            evaluationType.setGradeScale(null);
        }
        evaluationType.setScoringMethod(scoringMethod);
        evaluationType.setGradeCalculationMethod(gradeCalculationMethod);
        evaluationType.setIsFASABasedExam(isFASABasedExam);
        evaluationType.setIsTermBasedAssessment(isTermBasedAssessment);
        evaluationType.setUseAssessmentWeightageAsMaxMarks(useAssessmentWeightageAsMaxMarks);
        getHibernateTemplate().update(evaluationType);
    }

    public EvaluationType getEvaluationType(Long id) {
        return (EvaluationType) getHibernateTemplate().get(EvaluationType.class, id);
    }
}
