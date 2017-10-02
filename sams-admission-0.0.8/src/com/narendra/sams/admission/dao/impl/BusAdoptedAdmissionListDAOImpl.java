package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.BusAdoptedAdmissionListDAO;
import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.BusStopAdmissionCount;
import com.narendra.sams.admission.domain.ClassHistory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusAdoptedAdmissionListDAOImpl extends HibernateDaoSupport implements BusAdoptedAdmissionListDAO {
    public Long getBusFacilityAdoptedAdmissionCount(Long academicYearId, Long studentStatus, Short admissionType) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId));
        if (studentStatus != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatus));
        }
        if (admissionType != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionType));
        }
        crt.add(Restrictions.isNotNull("busStop.id"));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("student.id"), "admnCount"));
        return (Long) crt.uniqueResult();
    }

    public List<ClassHistory> getBusFacilityAdoptedAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student", 0);
        crt.createAlias("busStop", "busStop", 0);
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
        crt.add(Restrictions.isNotNull("busStop"));
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<BusStopAdmissionCount> getBusStopWiseBusFacilityAdoptedAdmissionCount(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        String studStatus = studentStatusId.toString();
        List<BusStopAdmissionCount> admissions = null;
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("busStop", "busStop", 0);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.isNotNull("busStop"));
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        if (admissionTypeId != null) {
            studStatus = "new";
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        }
        crt.setProjection(Projections.projectionList().add(Projections.property("busStop.name")).add(Projections.property("busStop.distance")).add(Projections.groupProperty("busStop.id")).add(Projections.countDistinct("student.id"), "admissionCount"));
        crt.addOrder(Order.asc("busStop.name"));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            admissions = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                BusStopAdmissionCount busStopWiseAdmission = new BusStopAdmissionCount();
                busStopWiseAdmission.setBusStopId((Long) col[2]);
                busStopWiseAdmission.setBusStop((String) col[0]);
                busStopWiseAdmission.setAdmissions((Long) col[3]);
                busStopWiseAdmission.setStudentStatusId(studStatus);
                admissions.add(busStopWiseAdmission);
            }
        }
        return admissions;
    }

    public List<AcademicYearClassAdmissionCount> getClasswiseBusFacilityAdoptedAdmissionCount(Long academicYearId, Long studentStatus, Short admissionTypeId) {
        String studStatus = studentStatus.toString();
        List<AcademicYearClassAdmissionCount> academicYearClassAdmissionCounts = null;
        Criteria crt = getSession().createCriteria(ClassHistory.class).createAlias("academicYearClass", "academicYearClass");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("academicYearClass.id")).add(Projections.property("academicYearClass.displayName")).add(Projections.countDistinct("student.id"), "admnCount")).addOrder(Order.asc("admnCount"));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("studentStatus.id", studentStatus));
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        crt.add(Restrictions.isNotNull("busStop"));
        if (admissionTypeId != null) {
            studStatus = "new";
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
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

    public List<ClassHistory> getBusFacilityAdoptedAdmissionsByBusStop(Long academicYearId, Long studentStatusId, Long busStopId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student");
        crt.add(Restrictions.eq("academicYear.id", academicYearId)).add(Restrictions.eq("activeClass", Boolean.TRUE));
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        if (busStopId != null) {
            crt.add(Restrictions.eq("busStop.id", busStopId));
        }
        if (admissionTypeId != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        }
        crt.addOrder(Order.asc("student.firstName")).addOrder(Order.asc("student.lastName"));
        return crt.list();
    }

    public List<ClassHistory> getBusFacilityAdoptedAdmissionsByClass(Long academicYearClassId, Long studentStatusId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student", 0);
        crt.createAlias("academicYearClass", "academicYearClass", 0);
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        crt.add(Restrictions.isNotNull("busStop"));
        crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        if (studentStatusId != null) {
            crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        }
        if (admissionTypeId != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        }
        crt.addOrder(Order.asc("student.firstName"));
        crt.addOrder(Order.asc("student.lastName"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }
}
