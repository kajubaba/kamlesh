package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.StudentAttendanceDAO;
import com.narendra.sams.academics.domain.StudentAttendance;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentAttendanceDAOImpl extends HibernateDaoSupport implements StudentAttendanceDAO {
    public List<StudentAttendance> getTermAttendance(Long termId, Long classId, Long sectionId) {
        Criteria crt = getSession().createCriteria(StudentAttendance.class);
        crt.createAlias("student", "student");
        crt.add(Restrictions.eq("evaluationTerm.id", termId));
        crt.add(Restrictions.eq("academicYearClass.id", classId));
        if (sectionId != null) {
            crt.createAlias("student.classHistories", "classHistory");
            crt.add(Restrictions.eq("classHistory.classSection.id", sectionId));
        }
        crt.addOrder(Order.asc("student.firstName")).addOrder(Order.asc("student.lastName"));
        return crt.list();
    }

    public void saveStudentAttendance(StudentAttendance studentAttendance, Long userId) {
        if (studentAttendance.getId() == null) {
            EvaluationTerm persistTerm = (EvaluationTerm) getHibernateTemplate().load(EvaluationTerm.class, studentAttendance.getEvaluationTerm().getId());
            Student persistStudent = (Student) getHibernateTemplate().load(Student.class, studentAttendance.getStudent().getId());
            studentAttendance.setAcademicYearClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, studentAttendance.getAcademicYearClass().getId()));
            studentAttendance.setEvaluationTerm(persistTerm);
            studentAttendance.setStudent(persistStudent);
            getHibernateTemplate().save(studentAttendance);
            return;
        }
        StudentAttendance persistAttendance = (StudentAttendance) getHibernateTemplate().load(StudentAttendance.class, studentAttendance.getId());
        persistAttendance.setAttendance(studentAttendance.getAttendance());
        getHibernateTemplate().update(persistAttendance);
    }

    public StudentAttendance getStudentAttendance(Long studentId, Long classId, Long termId) {
        Criteria crt = getSession().createCriteria(StudentAttendance.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearClass.id", classId));
        crt.add(Restrictions.eq("evaluationTerm.id", termId));
        return (StudentAttendance) crt.uniqueResult();
    }
}
