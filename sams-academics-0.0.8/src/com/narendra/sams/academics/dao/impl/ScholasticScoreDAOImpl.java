package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.ScholasticScoreDAO;
import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.academics.exam.domain.ScholasticScore;
import com.narendra.sams.academics.exam.domain.TermAssessment;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ScholasticScoreDAOImpl extends HibernateDaoSupport implements ScholasticScoreDAO {
    public List<ScholasticScore> getScholasticScore(Long classId, Long sectionId, Long subjectId, Long termAssessmentId) {
        Criteria crt = getSession().createCriteria(ScholasticScore.class);
        crt.createAlias("student", "student");
        crt.add(Restrictions.eq("studentClass.id", classId));
        crt.add(Restrictions.eq("classSubject.id", subjectId));
        crt.add(Restrictions.eq("termAssessment.id", termAssessmentId));
        if (sectionId != null) {
            crt.createAlias("student.classHistories", "classHistory");
            crt.add(Restrictions.eq("classHistory.classSection.id", sectionId));
            crt.add(Restrictions.eq("classHistory.academicYearClass.id", classId));
            crt.add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
            crt.add(Restrictions.eq("classHistory.studentStatus.id", StudentStatus.CONFIRMED));
        }
        crt.addOrder(Order.asc("student.firstName")).addOrder(Order.asc("student.lastName"));
        return crt.list();
    }

    public void updateStudentScore(Long scoreId, Float marks) {
        ScholasticScore scholasticScore = (ScholasticScore) getHibernateTemplate().get(ScholasticScore.class, scoreId);
        scholasticScore.setMarksObtained(marks);
        getHibernateTemplate().saveOrUpdate(scholasticScore);
    }

    public void addStudentScore(Long studentId, Long classId, Long subjectId, Long termAssessmentId, Float marks) {
        ScholasticScore scholasticScore = new ScholasticScore();
        scholasticScore.setStudent((Student) getHibernateTemplate().load(Student.class, studentId));
        scholasticScore.setStudentClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, classId));
        scholasticScore.setTermAssessment((TermAssessment) getHibernateTemplate().load(TermAssessment.class, termAssessmentId));
        scholasticScore.setClassSubject((ClassSubject) getHibernateTemplate().load(ClassSubject.class, subjectId));
        scholasticScore.setMarksObtained(marks);
        getHibernateTemplate().save(scholasticScore);
    }

    public List<ScholasticScore> getStudentScore(Long studentId, Long studentClassId) {
        Criteria crt = getSession().createCriteria(ScholasticScore.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("studentClass.id", studentClassId));
        return crt.list();
    }

    public ScholasticScore getStudnetScholasticScore(Long studentId, Long studentClassId, Long assessmentId, Long subjectId) {
        Criteria crt = getSession().createCriteria(ScholasticScore.class);
        crt.add(Restrictions.eq("studentClass.id", studentClassId));
        crt.add(Restrictions.eq("classSubject.id", subjectId));
        crt.add(Restrictions.eq("termAssessment.id", assessmentId));
        crt.add(Restrictions.eq("student.id", studentId));
        return (ScholasticScore) crt.uniqueResult();
    }
}
