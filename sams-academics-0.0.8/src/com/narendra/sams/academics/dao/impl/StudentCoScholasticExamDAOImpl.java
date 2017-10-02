package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.StudentCoScholasticExamDAO;
import com.narendra.sams.academics.domain.GradeScalePoint;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.exam.domain.StudentCoScholasticScore;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentCoScholasticExamDAOImpl extends HibernateDaoSupport implements StudentCoScholasticExamDAO {
    public List<StudentCoScholasticScore> getStudentCoScholasticScore(Long studentId, Long studentClassId, Long termId) {
        Criteria crt = getSession().createCriteria(StudentCoScholasticScore.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("studentClass.id", studentClassId));
        crt.add(Restrictions.eq("evaluationTerm.id", termId));
        return crt.list();
    }

    public List<StudentCoScholasticScore> getStudentCoScholasticScore(Long studentId, Long studentClassId) {
        Criteria crt = getSession().createCriteria(StudentCoScholasticScore.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("studentClass.id", studentClassId));
        return crt.list();
    }

    public void saveStudentCoScholaticScore(List<StudentCoScholasticScore> coScholasticScores, Long studentId, Long studentClassId, Long termId) {
        Student student = (Student) getHibernateTemplate().load(Student.class, studentId);
        AcademicYearClass studentClass = (AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, studentClassId);
        EvaluationTerm evaluationTerm = (EvaluationTerm) getHibernateTemplate().load(EvaluationTerm.class, termId);
        for (StudentCoScholasticScore studentCoScholasticScore : coScholasticScores) {
            studentCoScholasticScore.setStudent(student);
            studentCoScholasticScore.setStudentClass(studentClass);
            studentCoScholasticScore.setEvaluationTerm(evaluationTerm);
            getHibernateTemplate().save(studentCoScholasticScore);
        }
    }

    public void updateStudentCoScholaticScore(List<StudentCoScholasticScore> coScholasticScores, Long studentId, Long studentClassId, Long termId) {
        for (StudentCoScholasticScore studentCoScholasticScore : coScholasticScores) {
            if (studentCoScholasticScore.getId() == null) {
                AcademicYearClass studentClass = (AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, studentClassId);
                EvaluationTerm evaluationTerm = (EvaluationTerm) getHibernateTemplate().load(EvaluationTerm.class, termId);
                studentCoScholasticScore.setStudent((Student) getHibernateTemplate().load(Student.class, studentId));
                studentCoScholasticScore.setEvaluationTerm(evaluationTerm);
                studentCoScholasticScore.setStudentClass(studentClass);
                getHibernateTemplate().save(studentCoScholasticScore);
            } else {
                StudentCoScholasticScore persistScore = (StudentCoScholasticScore) getHibernateTemplate().load(StudentCoScholasticScore.class, studentCoScholasticScore.getId());
                if (studentCoScholasticScore.getGradeScalePoint() != null) {
                    persistScore.setGradeScalePoint((GradeScalePoint) getHibernateTemplate().load(GradeScalePoint.class, studentCoScholasticScore.getGradeScalePoint().getId()));
                    persistScore.setMarksObtained(null);
                    persistScore.setFreeTextValue(null);
                } else {
                    persistScore.setMarksObtained(studentCoScholasticScore.getMarksObtained());
                    persistScore.setGradeScalePoint(null);
                    persistScore.setFreeTextValue(studentCoScholasticScore.getFreeTextValue());
                }
                getHibernateTemplate().update(persistScore);
            }
        }
    }

    public StudentCoScholasticScore getStudentCoScholasticScore(Long studentId, Long studentClassId, Long termId, Long criteriaId) {
        System.out.println("Getting ->" + studentId + " ->" + studentClassId + " ->" + termId + " ->" + criteriaId);
        Criteria crt = getSession().createCriteria(StudentCoScholasticScore.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("studentClass.id", studentClassId));
        crt.add(Restrictions.eq("evaluationTerm.id", termId));
        crt.add(Restrictions.eq("skillIndicator.id", criteriaId));
        return (StudentCoScholasticScore) crt.uniqueResult();
    }
}
