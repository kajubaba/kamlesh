package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.FeeAdjustedStudentDAO;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.Student;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FeeAdjustedStudentDAOImpl extends HibernateDaoSupport implements FeeAdjustedStudentDAO {
    public Long getFeeAdjustedStudentCount(Long academicYearId) {
        Criteria crt = getSession().createCriteria(CustomizeInstallment.class).createAlias("student", "student").createAlias("academicYearClass", "academicYearClass").createAlias("academicYearClass.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("student.id")));
        return (Long) crt.uniqueResult();
    }

    public List<Student> getStudentsWhoseFeeIsCustomized(Long academicYearId, Long studentStatusId) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.createAlias("customizeInstallments", "customizeInstallment");
        crt.createAlias("customizeInstallment.academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        if (studentStatusId != null) {
            crt.createAlias("classHistories", "classHistory");
            crt.createAlias("classHistory.studentStatus", "studentStatus");
            crt.add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        crt.addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }
}
