package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.AdmissionListDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentQuick;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdmissionListDAOImpl extends HibernateDaoSupport implements AdmissionListDAO {
    public List<ClassHistory> getAdmissionsByClass(Collection<Long> classes) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student", 0);
        crt.createAlias("academicYearClass", "academicYearClass", 0);
        crt.add(Restrictions.in("academicYearClass.id", classes));
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<ClassHistory> getAdmissionsByClass(Long academicYearClassId, Long studentStatusId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student", 0);
        crt.createAlias("academicYearClass", "academicYearClass", 0);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        if (admissionTypeId != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        }
        if (academicYearClassId != null) {
            crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        }
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<ClassHistory> getAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student", 0);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        if (admissionTypeId != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        }
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<ClassHistory> getUnderSchemeAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student", 0);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        crt.add(Restrictions.isNotNull("admissionScheme"));
        if (admissionTypeId != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        }
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<ClassHistory> getUnderSchemeAdmissions(Long academicYearId, Long admissionSchemeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student", 0);
        crt.add(Restrictions.eq("admissionScheme.id", admissionSchemeId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<Student> getStudents(Long academicYearId, Long studentStatusId) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.createAlias("classHistories", "classHistory").createAlias("classHistory.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE)).add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId));
        return crt.list();
    }

    public List<Student> getStudents(Long academicYearId, Long courseYearId, Long studentStatusId) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.createAlias("classHistories", "classHistory").createAlias("classHistory.academicYear", "academicYear").createAlias("classHistory.academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.add(Restrictions.eq("courseYear.id", courseYearId)).add(Restrictions.eq("academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE)).add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId));
        return crt.list();
    }

    public List<StudentQuick> searchStudents(String chars, Long institueId) {
        if (chars == null) {
            return null;
        }
        String lastName = null;
        String[] names = chars.split(" ");
        String firstName = names[0];
        if (names.length > 1) {
            lastName = names[1];
        }
        Criteria crt = getSession().createCriteria(Student.class);
        crt.setProjection(Projections.projectionList().add(Projections.property("id")).add(Projections.property("studentId")).add(Projections.property("firstName")).add(Projections.property("lastName")).add(Projections.property("gender")).add(Projections.property("fatherName")));
        crt.add(Restrictions.eq("institute.id", institueId));
        if (lastName != null) {
            crt.add(Restrictions.eq("firstName", firstName).ignoreCase());
            crt.add(Restrictions.like("lastName", lastName, MatchMode.START).ignoreCase());
        } else {
            crt.add(Restrictions.disjunction().add(Restrictions.like("firstName", firstName, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("lastName", firstName, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("studentId", firstName, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("fatherName", firstName, MatchMode.ANYWHERE).ignoreCase()));
        }
        crt.addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName"));
        Iterator it = crt.list().iterator();
        if (!it.hasNext()) {
            return null;
        }
        List<StudentQuick> students = new ArrayList();
        while (it.hasNext()) {
            Object[] col = (Object[]) it.next();
            students.add(new StudentQuick((Long) col[0], (String) col[1], (String) col[2], (String) col[3], (String) col[4], (String) col[5]));
        }
        return students;
    }

    public List<Student> getStudentsForTranslations(Long academicSessionId, Long classId, Long studentStatusId) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.createAlias("classHistories", "classHistory");
        crt.createAlias("classHistory.studentStatus", "studentStatus");
        crt.createAlias("classHistory.academicYearClass", "academicYearClass");
        crt.createAlias("classHistory.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", classId));
        }
        if (classId != null) {
            crt.add(Restrictions.eq("academicYearClass.id", classId));
        }
        crt.add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        return crt.list();
    }
}
