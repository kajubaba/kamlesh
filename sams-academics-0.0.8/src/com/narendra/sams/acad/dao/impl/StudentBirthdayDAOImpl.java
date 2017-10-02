package com.narendra.sams.acad.dao.impl;

import com.narendra.sams.acad.dao.StudentBirthdayDAO;
import com.narendra.sams.admission.domain.Student;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentBirthdayDAOImpl extends HibernateDaoSupport implements StudentBirthdayDAO {
    public List<Student> getBirthdayStudents(Long instituteId, Long[] studentStatus, Date birthdate) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.createAlias("institute", "institute").createAlias("studentStatus", "studentStatus");
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.in("studentStatus.id", studentStatus));
        crt.add(Restrictions.isNotNull("dob"));
        crt.add(Restrictions.sqlRestriction("month(dob)=" + (birthdate.getMonth() + 1) + " and day(dob)=" + birthdate.getDate()));
        return crt.list();
    }
}
