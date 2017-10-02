package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.CustomizeStudentFeeDAO;
import com.narendra.sams.admission.domain.CustomizeFee;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.FeeCustomizeComments;
import com.narendra.sams.admission.domain.FeeDiscount;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CustomizeStudentFeeDAOImpl extends HibernateDaoSupport implements CustomizeStudentFeeDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public void saveOrUpdateStudentDiscount(List<FeeDiscount> feeDiscounts) {
        getHibernateTemplate().saveOrUpdateAll(feeDiscounts);
    }

    public List<FeeDiscount> getFeeDiscounts(Long studentId, Long academicYearFeeId) {
        Criteria crt = getSession().createCriteria(FeeDiscount.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        return crt.list();
    }

    public void saveCustomizeInstallments(List<CustomizeInstallment> customizeInstallments) {
        getHibernateTemplate().saveOrUpdateAll(customizeInstallments);
    }

    public void updateCustomizeFee(List<CustomizeInstallmentDetail> customizeInstallmentDetails, Long userId) {
        UserView user = loadUser(userId);
        Date dateTime = DateUtil.getSystemDateTime();
        if (customizeInstallmentDetails != null) {
            for (CustomizeInstallmentDetail customizeInstallmentDetail : customizeInstallmentDetails) {
                CustomizeInstallmentDetail loadedCustomizeInstallmentDetail = (CustomizeInstallmentDetail) getHibernateTemplate().load(CustomizeInstallmentDetail.class, customizeInstallmentDetail.getId());
                loadedCustomizeInstallmentDetail.setAmount(customizeInstallmentDetail.getAmount());
                loadedCustomizeInstallmentDetail.getCustomizeInstallment().setModifiedBy(user);
                loadedCustomizeInstallmentDetail.getCustomizeInstallment().setModifiedDate(dateTime);
                Student student = loadedCustomizeInstallmentDetail.getCustomizeInstallment().getStudent();
                if (student.getIsLocked() != null && student.getIsLocked().booleanValue()) {
                    AcademicYear academicYear = loadedCustomizeInstallmentDetail.getCustomizeInstallment().getAcademicYearFee().getAcademicYear();
                    CourseYear courseYear = loadedCustomizeInstallmentDetail.getCustomizeInstallment().getAcademicYearFee().getCourseYear();
                    if (academicYear.getId().equals(student.getAcademicYear().getId()) && courseYear.getId().equals(student.getAcademicYearClass().getCourseYear().getId())) {
                        student.setIsLocked(Boolean.FALSE);
                    }
                }
            }
        }
    }

    public List<CustomizeInstallment> getCustomizeInstallments(Long studentId, Long academicYearId, Long courseYearId, Short installmentId) {
        Criteria crt = getSession().createCriteria(CustomizeInstallment.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.academicYear", "academicYear");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        crt.add(Restrictions.eq("installment.id", installmentId));
        crt.addOrder(Order.asc("installment.id"));
        return crt.list();
    }

    public void updateCustomizeInstallmentDueDate(List<CustomizeInstallment> customizeInstallments) {
        if (customizeInstallments != null) {
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                CustomizeInstallment loadedInstallment = (CustomizeInstallment) getHibernateTemplate().load(CustomizeInstallment.class, customizeInstallment.getId());
                loadedInstallment.setDueDate(customizeInstallment.getDueDate());
                if (customizeInstallment.getLateFeeRule() == null || customizeInstallment.getLateFeeRule().getId() == null) {
                    loadedInstallment.setLateFeeRule(null);
                } else {
                    loadedInstallment.setLateFeeRule((LateFeeRule) getHibernateTemplate().load(LateFeeRule.class, customizeInstallment.getLateFeeRule().getId()));
                }
            }
        }
    }

    public CustomizeFee getCustomizeFee(Long studentId, Long academicYearId, Long courseYearId) {
        Criteria crt = getSession().createCriteria(CustomizeFee.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.academicYear", "academicYear");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        return (CustomizeFee) crt.uniqueResult();
    }

    public void saveCustomizeFee(CustomizeFee customizeFee) {
        getHibernateTemplate().save(customizeFee);
    }

    public FeeCustomizeComments getComments(Long studentId, Long academicYearFeeId) {
        Criteria crt = getSession().createCriteria(FeeCustomizeComments.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        return (FeeCustomizeComments) crt.uniqueResult();
    }

    public void saveOrUpdateComments(FeeCustomizeComments feeCustomizeComments) {
        if (feeCustomizeComments.getId() != null) {
            ((FeeCustomizeComments) getSession().load(FeeCustomizeComments.class, feeCustomizeComments.getId())).setComments(feeCustomizeComments.getComments());
            return;
        }
        AcademicYearFee academicYearFee = (AcademicYearFee) getHibernateTemplate().load(AcademicYearFee.class, feeCustomizeComments.getAcademicYearFee().getId());
        feeCustomizeComments.setAcademicYear(academicYearFee.getAcademicYear());
        feeCustomizeComments.setAcademicYearFee(academicYearFee);
        getSession().save(feeCustomizeComments);
    }

    public CustomizeInstallment getCustomizeInstallment(Long id) {
        return (CustomizeInstallment) getHibernateTemplate().get(CustomizeInstallment.class, id);
    }

    public List<FeeCustomizeComments> getComments(Long studentId, Collection<Long> academicYearIds) {
        if (academicYearIds == null) {
            return null;
        }
        Criteria crt = getSession().createCriteria(FeeCustomizeComments.class);
        crt.createAlias("academicYear", "academicYear");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.in("academicYear.id", academicYearIds));
        crt.addOrder(Order.asc("academicYear.orderNo"));
        return crt.list();
    }

    public List<Date> getAdjustedInstallmentDueDates(Long academicYearId) {
        List<Date> dueDates = new ArrayList();
        Criteria crt = getSession().createCriteria(CustomizeInstallment.class);
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

    public void deleteFeeAdjustment(Long studentId, Long academicYearFeeId) {
        Criteria crt = getSession().createCriteria(CustomizeInstallment.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        getHibernateTemplate().deleteAll(crt.list());
    }

    public void deleteFeeAdjustmentComments(Long studentId, Long academicYearFeeId) {
        Criteria crt = getSession().createCriteria(FeeCustomizeComments.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        getHibernateTemplate().deleteAll(crt.list());
    }

    public void deleteFeeAdjustmentDiscount(Long studentId, Long academicYearFeeId) {
        Criteria crt = getSession().createCriteria(FeeDiscount.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        getHibernateTemplate().deleteAll(crt.list());
    }

    public List<CustomizeInstallment> getAdjustedInstallmentOfCourseYearSetting(Long courseYearSettingId, Short admissionTypeId) {
        CourseYearSetting courseYearSetting = (CourseYearSetting) getHibernateTemplate().get(CourseYearSetting.class, courseYearSettingId);
        Criteria crt = getSession().createCriteria(CustomizeInstallment.class);
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.admissionType", "admissionType");
        crt.createAlias("academicYearFee.courseYearSetting", "courseYearSetting");
        crt.add(Restrictions.eq("courseYearSetting.id", courseYearSettingId));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        return crt.list();
    }
}
