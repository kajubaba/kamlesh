package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.AdmissionCountDAO;
import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdmissionCountDAOImpl extends HibernateDaoSupport implements AdmissionCountDAO {
    public long getAdmissionCountUnderScheme(Long academicYearId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("studentStatus.id", StudentStatus.CONFIRMED));
        crt.add(Restrictions.isNotNull("admissionScheme"));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("student.id"), "admnCount"));
        return ((Long) crt.uniqueResult()).longValue();
    }

    public Long getAdmissionCount(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId));
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        if (admissionTypeId != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        }
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("student.id"), "admnCount"));
        return (Long) crt.uniqueResult();
    }

    public List<AcademicYearClassAdmissionCount> getClasswiseAdmissionCount(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        List<AcademicYearClassAdmissionCount> academicYearClassAdmissionCounts = null;
        String studStatus = studentStatusId.toString();
        Criteria crt = getSession().createCriteria(ClassHistory.class).createAlias("academicYearClass", "academicYearClass");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("academicYearClass.id")).add(Projections.property("academicYearClass.displayName")).add(Projections.countDistinct("student.id"), "admnCount")).addOrder(Order.asc("admnCount"));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        if (admissionTypeId != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
            studStatus = "new";
        }
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            academicYearClassAdmissionCounts = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                academicYearClassAdmissionCounts.add(new AcademicYearClassAdmissionCount((Long) col[0], (String) col[1], (Long) col[2], academicYearId, studStatus));
            }
        }
        return academicYearClassAdmissionCounts;
    }
}
