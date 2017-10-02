package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.AdmissionRenewalDAO;
import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdmissionRenewalDAOImpl extends HibernateDaoSupport implements AdmissionRenewalDAO {
    private AcademicYearDAO academicYearDAO;

    public AcademicYearDAO getAcademicYearDAO() {
        return this.academicYearDAO;
    }

    public void setAcademicYearDAO(AcademicYearDAO academicYearDAO) {
        this.academicYearDAO = academicYearDAO;
    }

    public Long getAdmissionCountPendingForRenewal(Long instituteId) {
        AcademicYear admissionAcademicYear = this.academicYearDAO.getActiveAcademicYearForAdmission(instituteId);
        if (admissionAcademicYear == null) {
            return Long.valueOf(0);
        }
        AcademicYear prevAcademicYear = this.academicYearDAO.getAcademicYearByOrder(Short.valueOf((short) (admissionAcademicYear.getOrderNo().shortValue() - 1)), instituteId);
        if (prevAcademicYear == null) {
            return Long.valueOf(0);
        }
        Criteria crt = getSession().createCriteria(Student.class);
        crt.add(Restrictions.eq("academicYear.id", prevAcademicYear.getId()));
        crt.add(Restrictions.eq("studentStatus.id", StudentStatus.CONFIRMED));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("id"), "admnCount"));
        return (Long) crt.uniqueResult();
    }

    public List<Student> getAdmissionsPendingForRenewal(Long instituteId) {
        AcademicYear prevAcademicYear = this.academicYearDAO.getAcademicYearByOrder(Short.valueOf((short) (this.academicYearDAO.getActiveAcademicYearForAdmission(instituteId).getOrderNo().shortValue() - 1)), instituteId);
        Criteria crt = getSession().createCriteria(Student.class);
        crt.add(Restrictions.eq("academicYear.id", prevAcademicYear.getId()));
        crt.add(Restrictions.eq("studentStatus.id", StudentStatus.CONFIRMED));
        crt.addOrder(Order.asc("firstName"));
        crt.addOrder(Order.asc("lastName"));
        return crt.list();
    }

    public List<AcademicYearClassAdmissionCount> getClasswiseAdmissionsPendingForRenewal(Long instituteId) {
        List<AcademicYearClassAdmissionCount> academicYearClassAdmissionCounts = null;
        AcademicYear prevAcademicYear = this.academicYearDAO.getAcademicYearByOrder(Short.valueOf((short) (this.academicYearDAO.getActiveAcademicYearForAdmission(instituteId).getOrderNo().shortValue() - 1)), instituteId);
        Criteria crt = getSession().createCriteria(Student.class).createAlias("academicYearClass", "academicYearClass");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("academicYearClass.id")).add(Projections.property("academicYearClass.displayName")).add(Projections.countDistinct("id"), "admnCount")).addOrder(Order.asc("admnCount"));
        crt.add(Restrictions.eq("academicYear.id", prevAcademicYear.getId()));
        crt.add(Restrictions.eq("studentStatus.id", StudentStatus.CONFIRMED));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            academicYearClassAdmissionCounts = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                academicYearClassAdmissionCounts.add(new AcademicYearClassAdmissionCount((Long) col[0], (String) col[1], (Long) col[2], prevAcademicYear.getId(), StudentStatus.CONFIRMED.toString()));
            }
        }
        return academicYearClassAdmissionCounts;
    }

    public Boolean getRenewalEligibility(Long instituteId, Long studentId) {
        AcademicYear prevAcademicYear = this.academicYearDAO.getAcademicYearByOrder(Short.valueOf((short) (this.academicYearDAO.getActiveAcademicYearForAdmission(instituteId).getOrderNo().shortValue() - 1)), instituteId);
        Student student = (Student) getHibernateTemplate().get(Student.class, studentId);
        if (student != null && student.getAcademicYear().getId().equals(prevAcademicYear.getId()) && StudentStatus.CONFIRMED.equals(student.getStudentStatus().getId())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public List<Student> getClassAdmissionsPendingForRenewal(Long academicYearClassId) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        crt.add(Restrictions.eq("studentStatus.id", StudentStatus.CONFIRMED));
        return crt.list();
    }
}
