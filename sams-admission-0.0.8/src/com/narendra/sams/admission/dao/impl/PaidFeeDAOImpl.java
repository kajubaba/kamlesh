package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.PaidFeeDAO;
import com.narendra.sams.admission.domain.DateWisePaidFee;
import com.narendra.sams.admission.domain.FeeHeadPaid;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.PaidFee;
import com.narendra.sams.admission.domain.StudentPaidFee;
import com.narendra.sams.core.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PaidFeeDAOImpl extends HibernateDaoSupport implements PaidFeeDAO {
    public List<PaidFee> getClassWisePaidFee(Long instituteId, Long academicYearId) {
        List<PaidFee> paidFees = null;
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class).createAlias("feeTransaction", "feeTransaction").createAlias("feeTransaction.institute", "institute").createAlias("feeTransaction.academicYear", "academicYear").createAlias("feeTransaction.studentClass", "studentClass").createAlias("studentClass.courseYear", "courseYear").createAlias("courseYear.course", "course");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("courseYear.id")).add(Projections.property("academicYear.name")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.name")).add(Projections.sum("amount"), "amount")).addOrder(Order.asc("amount"));
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.add(Restrictions.eq("institute.id", instituteId));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            paidFees = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                paidFees.add(new PaidFee((Long) col[0], (String) col[1], (String) col[2], (String) col[3], (Long) col[4], academicYearId));
            }
        }
        return paidFees;
    }

    public List<PaidFee> getClassWisePaidFee(Long instituteId, Date fromDate, Date toDate, Long academicYearId) {
        List<PaidFee> paidFees = null;
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class).createAlias("feeTransaction", "feeTransaction").createAlias("feeTransaction.institute", "institute").createAlias("feeTransaction.academicYear", "academicYear").createAlias("feeTransaction.studentClass", "studentClass").createAlias("studentClass.courseYear", "courseYear").createAlias("courseYear.course", "course");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("courseYear.id")).add(Projections.property("academicYear.name")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.name")).add(Projections.property("course.duration")).add(Projections.property("studentClass.displayName")).add(Projections.sum("amount"), "amount")).addOrder(Order.asc("amount"));
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.add(Restrictions.eq("institute.id", instituteId));
        if (fromDate != null) {
            crt.add(Restrictions.ge("feeTransaction.paymentDate", fromDate));
        }
        if (toDate != null) {
            crt.add(Restrictions.le("feeTransaction.paymentDate", toDate));
        }
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            paidFees = new ArrayList();
            while (it.hasNext()) {
                String className;
                Object[] col = (Object[]) it.next();
                if (((Short) col[4]).shortValue() == (short) 1) {
                    className = (String)col[5];
                } else {
                    className = new StringBuilder(String.valueOf((String) col[2])).append(" , ").append((Short) col[3]).append(" Yr.").toString();
                }
                paidFees.add(new PaidFee((Long) col[0], (String) col[1], className, "", (Long) col[6], academicYearId));
            }
        }
        return paidFees;
    }

    public List<FeeTransaction> getFeeTransactions(Long instituteId, Date fromDate, Date toDate, Long academicYearId) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.add(Restrictions.eq("institute.id", instituteId));
        if (fromDate != null) {
            crt.add(Restrictions.ge("paymentDate", fromDate));
        }
        if (toDate != null) {
            crt.add(Restrictions.le("paymentDate", toDate));
        }
        crt.addOrder(Order.desc("paymentDate"));
        return crt.list();
    }

    public List<FeeHeadPaid> getHeadwisePaidFee(Long instituteId, Date fromDate, Date toDate, Long academicYearId) {
        List<FeeHeadPaid> paidFees = null;
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class).createAlias("feeHead", "feeHead").createAlias("feeTransaction", "feeTransaction").createAlias("feeTransaction.academicYear", "academicYear").createAlias("feeTransaction.institute", "institute");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("feeHead.id")).add(Projections.property("feeHead.name")).add(Projections.sum("amount")));
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.add(Restrictions.eq("institute.id", instituteId));
        if (fromDate != null) {
            crt.add(Restrictions.ge("feeTransaction.paymentDate", fromDate));
        }
        if (toDate != null) {
            crt.add(Restrictions.le("feeTransaction.paymentDate", toDate));
        }
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            paidFees = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                paidFees.add(new FeeHeadPaid((Long) col[0], (String) col[1], (Long) col[2]));
            }
        }
        return paidFees;
    }

    public List<StudentPaidFee> getHeadwisePaidFee(Long instituteId, Long feeHeadId, Date fromDate, Date toDate, Long academicYearId) {
        List<StudentPaidFee> paidFees = null;
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class).createAlias("feeHead", "feeHead").createAlias("feeTransaction", "feeTransaction").createAlias("feeTransaction.academicYear", "academicYear").createAlias("feeTransaction.institute", "institute").createAlias("feeTransaction.student", "student").createAlias("student.academicYearClass", "academicYearClass").createAlias("academicYearClass.courseYear", "courseYear").createAlias("courseYear.course", "course");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("student.id")).add(Projections.property("student.studentId")).add(Projections.property("student.firstName")).add(Projections.property("student.lastName")).add(Projections.property("academicYearClass.displayName")).add(Projections.sum("amount")).add(Projections.property("student.fatherName")));
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.add(Restrictions.eq("feeHead.id", feeHeadId));
        crt.add(Restrictions.eq("institute.id", instituteId));
        if (fromDate != null) {
            crt.add(Restrictions.ge("feeTransaction.paymentDate", fromDate));
        }
        if (toDate != null) {
            crt.add(Restrictions.le("feeTransaction.paymentDate", toDate));
        }
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            paidFees = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                paidFees.add(new StudentPaidFee((Long) col[0], (String) col[1], new StringBuilder(String.valueOf((String) col[2])).append(" ").append((String) col[3]).toString(), (String) col[4], (Long) col[5], (String) col[6]));
            }
        }
        return paidFees;
    }

    public List<FeeTransaction> getRecentFeeTransactions(Long instituteId) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.desc("paymentDate"));
        crt.setFirstResult(0);
        crt.setMaxResults(9);
        return crt.list();
    }

    public List<FeeTransaction> getAllFeeTransactions(Long instituteId, Long academicYearId, Date fromDate, Date toDate) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.createAlias("student", "student", 0);
        crt.setFetchMode("feeTransactionDetails", FetchMode.JOIN);
        crt.setFetchMode("student", FetchMode.JOIN);
        crt.setFetchMode("studentClass", FetchMode.JOIN);
        crt.setFetchMode("studentClass.courseYear", FetchMode.JOIN);
        crt.setFetchMode("studentClass.courseYear.course", FetchMode.JOIN);
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        if (fromDate != null) {
            crt.add(Restrictions.ge("paymentDate", fromDate));
        }
        if (toDate != null) {
            crt.add(Restrictions.le("paymentDate", toDate));
        }
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.desc("paymentDate"));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public Long getTotalPaidFee(Long academicYearId) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class).createAlias("feeTransaction", "feeTransaction").createAlias("feeTransaction.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.sum("amount"), "paidFee"));
        return (Long) crt.uniqueResult();
    }

    public Long getPaidFee(Long instituteId, Date fromDate, Date toDate) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class).createAlias("feeTransaction", "feeTransaction");
        crt.add(Restrictions.ge("feeTransaction.paymentDate", fromDate));
        crt.add(Restrictions.le("feeTransaction.paymentDate", toDate));
        crt.add(Restrictions.eq("feeTransaction.institute.id", instituteId));
        crt.setProjection(Projections.projectionList().add(Projections.sum("amount"), "paidFee"));
        return (Long) crt.uniqueResult();
    }

    public FeeTransaction getFeeTransaction(Long feeTransactionId) {
        return (FeeTransaction) getHibernateTemplate().get(FeeTransaction.class, feeTransactionId);
    }

    public List<DateWisePaidFee> getDateWisePaidFee(Long instituteId, Long feeHeadId, Long academicYearId, Date fromDate, Date toDate) {
        List<DateWisePaidFee> paidFees = null;
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class).createAlias("feeHead", "feeHead").createAlias("feeTransaction", "feeTransaction").createAlias("feeTransaction.academicYear", "academicYear").createAlias("feeTransaction.institute", "institute");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("feeTransaction.paymentDate")).add(Projections.sum("amount")));
        crt.addOrder(Order.desc("feeTransaction.paymentDate"));
        if (academicYearId != null) {
            crt.add(Restrictions.eq("academicYear.id", academicYearId));
        }
        crt.add(Restrictions.eq("feeHead.id", feeHeadId));
        crt.add(Restrictions.eq("institute.id", instituteId));
        if (fromDate != null) {
            crt.add(Restrictions.ge("feeTransaction.paymentDate", fromDate));
        }
        if (toDate != null) {
            crt.add(Restrictions.le("feeTransaction.paymentDate", toDate));
        }
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            paidFees = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                DateWisePaidFee dateWisePaidFee = new DateWisePaidFee();
                dateWisePaidFee.setDate((Date) col[0]);
                dateWisePaidFee.setPaidFee((Long) col[1]);
                if (dateWisePaidFee.getDate() != null) {
                    dateWisePaidFee.setDateStr(DateUtil.formatDate(dateWisePaidFee.getDate(), "dd-MMM-yyyy"));
                }
                paidFees.add(dateWisePaidFee);
            }
        }
        return paidFees;
    }

    public void deleteFeeTransaction(Long feeTransactionId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(FeeTransaction.class, feeTransactionId));
    }

    public List<FeeTransaction> getFeeTransactionInCourseYearSetting(Long courseYearSetting, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.createAlias("academicYearFeeInstallment", "academicYearFeeInstallment").createAlias("academicYearFeeInstallment.academicYearFee", "academicYearFee").createAlias("academicYearFee.admissionType", "admissionType").createAlias("academicYearFee.courseYearSetting", "courseYearSetting");
        crt.add(Restrictions.eq("courseYearSetting.id", courseYearSetting));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        return crt.list();
    }
}
