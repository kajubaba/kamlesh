package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.AcademicYearFeeDAO;
import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AcademicYearFeeDAOImpl extends HibernateDaoSupport implements AcademicYearFeeDAO {
    public void saveCourseYearFee(List<AcademicYearFee> academicYearFees) {
        getHibernateTemplate().saveOrUpdateAll(academicYearFees);
    }

    public List<AcademicYearFee> getAcademicYearFee(Long courseYearId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(AcademicYearFee.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        return crt.list();
    }

    public List<AcademicYearFee> getAcademicYearFeeForAllAdmissionType(Long courseYearSettingId) {
        Criteria crt = getSession().createCriteria(AcademicYearFee.class);
        crt.add(Restrictions.eq("courseYearSetting.id", courseYearSettingId));
        return crt.list();
    }

    public AcademicYearFee getAcademicYearFee(Long courseYearSetting, Short admissionType) {
        Criteria crt = getSession().createCriteria(AcademicYearFee.class);
        crt.add(Restrictions.eq("courseYearSetting.id", courseYearSetting));
        crt.add(Restrictions.eq("admissionType.id", admissionType));
        return (AcademicYearFee) crt.uniqueResult();
    }

    public void updateCourseYearFeeDetail(List<AcademicYearFeeDetail> academicYearFeeDetails) {
        if (academicYearFeeDetails != null) {
            for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFeeDetails) {
                ((AcademicYearFeeDetail) getHibernateTemplate().load(AcademicYearFeeDetail.class, academicYearFeeDetail.getId())).setAmount(academicYearFeeDetail.getAmount());
            }
        }
    }

    public AcademicYearFee getAcademicYearFeeById(Long academicYearFeeId) {
        return (AcademicYearFee) getHibernateTemplate().get(AcademicYearFee.class, academicYearFeeId);
    }

    public void saveAcademicYearFeeInstallments(List<AcademicYearFeeInstallment> academicYearFeeInstallments) {
        if (academicYearFeeInstallments != null) {
            Criteria crt = getSession().createCriteria(AcademicYearFeeInstallment.class);
            crt.add(Restrictions.eq("academicYearFee.id", ((AcademicYearFeeInstallment) academicYearFeeInstallments.get(0)).getAcademicYearFee().getId()));
            List<AcademicYearFeeInstallment> persistInstallments = crt.list();
            if (persistInstallments != null) {
                ListIterator<AcademicYearFeeInstallment> itr = persistInstallments.listIterator();
                while (itr.hasNext()) {
                    AcademicYearFeeInstallment item = (AcademicYearFeeInstallment) itr.next();
                    Boolean found = Boolean.FALSE;
                    for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                        if (academicYearFeeInstallment.getId().equals(item.getId())) {
                            found = Boolean.TRUE;
                            break;
                        }
                    }
                    if (!found.booleanValue()) {
                        itr.remove();
                        getHibernateTemplate().delete(item);
                    }
                }
                for (AcademicYearFeeInstallment transientAcademicYearFeeInstallment : academicYearFeeInstallments) {
                    if (transientAcademicYearFeeInstallment.getId() != null) {
                        for (AcademicYearFeeInstallment persistInstallment : persistInstallments) {
                            if (transientAcademicYearFeeInstallment.getId().equals(persistInstallment.getId())) {
                                persistInstallment.setLateFeeRule(transientAcademicYearFeeInstallment.getLateFeeRule());
                                persistInstallment.setDueDate(transientAcademicYearFeeInstallment.getDueDate());
                                for (AcademicYearFeeInstallmentDetail persistInstallmentDetail : persistInstallment.getAcademicYearFeeInstallmentDetails()) {
                                    for (AcademicYearFeeInstallmentDetail transientInstallmentDetail : transientAcademicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                                        if (persistInstallmentDetail.getId().equals(transientInstallmentDetail.getId())) {
                                            persistInstallmentDetail.setAmount(transientInstallmentDetail.getAmount());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    persistInstallments.add(transientAcademicYearFeeInstallment);
                }
                getHibernateTemplate().saveOrUpdateAll(persistInstallments);
                return;
            }
            getHibernateTemplate().saveOrUpdateAll(academicYearFeeInstallments);
        }
    }

    public AcademicYearFee getAcademicYearFee(Long academicYearId, Long courseYearId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(AcademicYearFee.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        return (AcademicYearFee) crt.uniqueResult();
    }

    public List<AcademicYearFeeInstallment> getFeeInstallments(Long academicYearId, Long courseYearId, Short admissionTypeId, Short[] installmentIds) {
        Criteria crt = getSession().createCriteria(AcademicYearFeeInstallment.class);
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("academicYearFee.admissionType", "admissionType");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        crt.add(Restrictions.in("installment.id", installmentIds));
        return crt.list();
    }

    public AcademicYearFeeInstallment getAcademicYearFeeInstallment(Long id) {
        return (AcademicYearFeeInstallment) getHibernateTemplate().get(AcademicYearFeeInstallment.class, id);
    }

    public void updateInstallmentDueDateAndLateFeeRule(List<AcademicYearFeeInstallment> academicYearFeeInstallments) {
        if (academicYearFeeInstallments != null) {
            for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                if (academicYearFeeInstallment.getId() != null) {
                    AcademicYearFeeInstallment loadInstallment = (AcademicYearFeeInstallment) getHibernateTemplate().load(AcademicYearFeeInstallment.class, academicYearFeeInstallment.getId());
                    loadInstallment.setDueDate(academicYearFeeInstallment.getDueDate());
                    loadInstallment.setLateFeeRule(academicYearFeeInstallment.getLateFeeRule());
                }
            }
        }
    }

    public List<AcademicYearFeeInstallment> getAcademicYearFeeInstallments(Long academicYearFeeId) {
        Criteria crt = getSession().createCriteria(AcademicYearFeeInstallment.class);
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        return crt.list();
    }

    public void deleteAcademicYearFeeInstallment(AcademicYearFeeInstallment academicYearFeeInstallment) {
        getHibernateTemplate().delete(academicYearFeeInstallment);
    }

    public List<Date> getAcademicInstallmentDueDates(Long academicYearId) {
        List<Date> dueDates = new ArrayList();
        Criteria crt = getSession().createCriteria(AcademicYearFeeInstallment.class);
        crt.setProjection(Projections.distinct(Projections.property("dueDate")));
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                dueDates.add((Date) it.next());
            }
        }
        return dueDates;
    }

    public void saveAcademicYearCourses(List<AcademicYearCourse> academicYearCourses) {
        getHibernateTemplate().saveOrUpdateAll(academicYearCourses);
    }

    public List<AcademicYearFeeInstallment> getDueFeeInstallments(Long academicYearId, Long courseYearId, Short admissionTypeId, Date dueDate) {
        Criteria crt = getSession().createCriteria(AcademicYearFeeInstallment.class);
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("academicYearFee.admissionType", "admissionType");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        crt.add(Restrictions.le("dueDate", dueDate));
        return crt.list();
    }
}
