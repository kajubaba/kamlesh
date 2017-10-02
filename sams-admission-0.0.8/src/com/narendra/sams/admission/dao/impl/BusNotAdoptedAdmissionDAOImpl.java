package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.BusNotAdoptedAdmissionDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusNotAdoptedAdmissionDAOImpl extends HibernateDaoSupport implements BusNotAdoptedAdmissionDAO {
    public Long getBusFacilityNotAdoptedAdmissionCount(Long academicYearId, Long studentStatus, Short admissionType) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId));
        if (studentStatus != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatus));
        }
        if (admissionType != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionType));
        }
        crt.add(Restrictions.isNull("busStop.id"));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("student.id"), "admnCount"));
        return (Long) crt.uniqueResult();
    }

    public List<ClassHistory> getBusFacilityNotAdoptedAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
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
        crt.add(Restrictions.isNull("busStop.id"));
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }
}
