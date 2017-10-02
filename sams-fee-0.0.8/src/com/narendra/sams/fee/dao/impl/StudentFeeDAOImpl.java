package com.narendra.sams.fee.dao.impl;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.domain.FeeCustomizeComments;
import com.narendra.sams.admission.domain.FeeDiscount;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.dao.FeeHeadDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.dao.StudentFeeDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentFeeDAOImpl extends HibernateDaoSupport implements StudentFeeDAO {
    private FeeHeadDAO feeHeadDAO;

    public FeeHeadDAO getFeeHeadDAO() {
        return this.feeHeadDAO;
    }

    public void setFeeHeadDAO(FeeHeadDAO feeHeadDAO) {
        this.feeHeadDAO = feeHeadDAO;
    }

    public long getAcademicYearBusFeeDeposited(Long studentId, Long academicYearId, Long courseYearId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.student", "student");
        crt.createAlias("feeTransaction.academicYearFeeInstallment", "academicYearFeeInstallment");
        crt.createAlias("academicYearFeeInstallment.academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("academicYearFee.admissionType", "admissionType");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        crt.add(Restrictions.eq("feeHead.id", Long.valueOf(3)));
        crt.add(Restrictions.isNotNull("amount"));
        List<FeeTransactionDetail> feeTransactionDetails = crt.list();
        if (feeTransactionDetails == null) {
            return 0;
        }
        long sum = 0;
        for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
            sum += feeTransactionDetail.getAmount().longValue();
        }
        return sum;
    }

    public long getCustomizedBusFeeDeposited(Long studentId, Long academicYearId, Long courseYearId, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.student", "student");
        crt.createAlias("feeTransaction.customizeInstallment", "customizeInstallment");
        crt.createAlias("customizeInstallment.academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("academicYearFee.admissionType", "admissionType");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        crt.add(Restrictions.eq("feeHead.id", Long.valueOf(3)));
        crt.add(Restrictions.isNotNull("amount"));
        List<FeeTransactionDetail> feeTransactionDetails = crt.list();
        if (feeTransactionDetails == null) {
            return 0;
        }
        long sum = 0;
        for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
            sum += feeTransactionDetail.getAmount().longValue();
        }
        return sum;
    }

    public List<ClassHistory> getAllClassHistories(Long academicYearId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public Date getFirstTransactionDatetime(Long studentId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        ProjectionList projLit = Projections.projectionList();
        projLit.add(Projections.min("transactionTime"));
        crt.setProjection(projLit);
        return (Date) crt.uniqueResult();
    }

    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public List<CustomizeInstallment> getCustomizeInstallments(Long studentId, Long academicYearFeeId) {
        Criteria crt = getSession().createCriteria(CustomizeInstallment.class);
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        crt.add(Restrictions.eq("student.id", studentId));
        crt.addOrder(Order.asc("installment.id"));
        return crt.list();
    }

    public List<FeeTransactionDetail> getAllTransactionDetails(Long studentId, Long academicYearFeeId, Boolean isCustomized) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.student", "student");
        if (isCustomized.booleanValue()) {
            crt.createAlias("feeTransaction.customizeInstallment", "customizeInstallment");
            crt.createAlias("customizeInstallment.academicYearFee", "academicYearFee");
        } else {
            crt.createAlias("feeTransaction.academicYearFeeInstallment", "academicYearFeeInstallment");
            crt.createAlias("academicYearFeeInstallment.academicYearFee", "academicYearFee");
        }
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        return crt.list();
    }

    public List<DaysOverdue> getDayOverdue(Long studentId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(DaysOverdue.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("feeAcademicYear.id", academicYearId));
        return crt.list();
    }

    public void saveOrUpdateDaysOverdue(List<DaysOverdue> daysOverdues) {
        for (DaysOverdue daysOverdue : daysOverdues) {
            DaysOverdue loaded = getDaysOverdue(daysOverdue.getStudent().getId(), daysOverdue.getFeeAcademicYear().getId(), daysOverdue.getInstallment().getId());
            if (loaded != null) {
                loaded.setDaysOverdue(daysOverdue.getDaysOverdue());
                loaded.setFeeAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, daysOverdue.getFeeAcademicYear().getId()));
                if (daysOverdue.getAcademicYearFeeInstallment() != null) {
                    loaded.setAcademicYearFeeInstallment((AcademicYearFeeInstallment) getHibernateTemplate().load(AcademicYearFeeInstallment.class, daysOverdue.getAcademicYearFeeInstallment().getId()));
                } else if (daysOverdue.getCustomizeInstallment() != null) {
                    loaded.setCustomizeInstallment((CustomizeInstallment) getHibernateTemplate().load(CustomizeInstallment.class, daysOverdue.getCustomizeInstallment().getId()));
                }
                if (loaded.getCalculate() == null) {
                    loaded.setCalculate(Boolean.FALSE);
                }
                if (loaded.getDiscount() == null) {
                    loaded.setDiscount(Integer.valueOf(0));
                }
            } else if (daysOverdue.getDaysOverdue() > 0) {
                if (daysOverdue.getCalculate() == null) {
                    daysOverdue.setCalculate(Boolean.TRUE);
                }
                if (daysOverdue.getDiscount() == null) {
                    daysOverdue.setDiscount(Integer.valueOf(0));
                }
                getSession().save(daysOverdue);
            }
        }
    }

    public DaysOverdue getDaysOverdue(Long studentId, Long academicYearId, Long installmentId) {
        Criteria crt = getSession().createCriteria(DaysOverdue.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("feeAcademicYear.id", academicYearId));
        crt.add(Restrictions.eq("installment.id", installmentId));
        return (DaysOverdue) crt.uniqueResult();
    }

    public List<FeeTransaction> getFeeTransactionsOnAcademicYearFee(Long studentId, Long academicYearFeeId, Long installmentId) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.createAlias("academicYearFeeInstallment", "academicYearFeeInstallment");
        crt.createAlias("academicYearFeeInstallment.academicYearFee", "academicYearFee");
        crt.createAlias("feeTransactionDetails", "feeTransactionDetail");
        crt.createAlias("feeTransactionDetails.installment", "installment");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFee.id", academicYearFeeId));
        crt.add(Restrictions.eq("installment.id", installmentId));
        return crt.list();
    }

    public List<FeeTransactionDetail> getPaidFeeDetails(Long studentId, Long academicYearId, Long courseYearId, Short[] installmentIds) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.student", "student");
        crt.createAlias("feeTransaction.studentClass", "studentClass");
        crt.createAlias("studentClass.academicYear", "ay");
        crt.createAlias("studentClass.courseYear", "courseYear");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("ay.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        if (installmentIds != null) {
            crt.add(Restrictions.in("installment.id", installmentIds));
        }
        return crt.list();
    }

    public List<FeeTransactionDetail> getPaidFeeDetailsByAcdemicYearFeeInstallmentId(Long studentId, Long academicYearFeeInstallmentId) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.academicYearFeeInstallment", "academicYearFeeInstallment");
        crt.createAlias("feeTransaction.student", "student");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYearFeeInstallment.id", academicYearFeeInstallmentId));
        return crt.list();
    }

    public List<FeeTransactionDetail> getTransactionDetailsOfCustomizedInstallment(Long studentId, Long custmizeInstallmentId) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.customizeInstallment", "customizeInstallment");
        crt.createAlias("feeTransaction.student", "student");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("customizeInstallment.id", custmizeInstallmentId));
        return crt.list();
    }

    public List<FeeTransaction> getFeeTransactions(Long studentid) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.add(Restrictions.eq("student.id", studentid));
        crt.addOrder(Order.desc("paymentDate"));
        return crt.list();
    }

    public List<Student> getStudentsWhoseFeeIsCustomized(Long academicYearId, String studentName) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crt.createAlias("customizeInstallments", "customizeInstallment");
        crt.createAlias("customizeInstallment.academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        if (!(studentName == null || studentName.trim().isEmpty())) {
            String firstName;
            String[] names = studentName.split(" ");
            String lastName = null;
            if (names.length == 1) {
                firstName = names[0].trim();
            } else if (names.length == 2) {
                firstName = names[0].trim();
                lastName = names[1].trim();
            } else {
                firstName = names[0].trim();
                for (int i = 1; i < names.length; i++) {
                    lastName = new StringBuilder(String.valueOf(lastName)).append(" ").append(names[i].trim()).toString();
                }
            }
            if (firstName != null) {
                crt.add(Restrictions.ilike("firstName", firstName, MatchMode.START));
            }
            if (lastName != null) {
                crt.add(Restrictions.ilike("lastName", lastName, MatchMode.START));
            }
        }
        crt.addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName"));
        return crt.list();
    }

    public List<DaysOverdue> getLateFeeDetails(Long studentId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(DaysOverdue.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("feeAcademicYear.id", academicYearId));
        crt.addOrder(Order.asc("installment.id"));
        return crt.list();
    }

    public void customizeLateFee(List<DaysOverdue> daysOverdues, Long userId) {
        if (daysOverdues != null) {
            UserView user = loadUser(userId);
            Date date = DateUtil.getSystemDateTime();
            for (DaysOverdue daysOverdue : daysOverdues) {
                DaysOverdue loaded = (DaysOverdue) getHibernateTemplate().load(DaysOverdue.class, daysOverdue.getId());
                if (daysOverdue.getCalculate() != null) {
                    loaded.setCalculate(daysOverdue.getCalculate());
                } else {
                    loaded.setCalculate(Boolean.FALSE);
                }
                loaded.setDiscount(daysOverdue.getDiscount());
                loaded.setModifiedBy(user);
                loaded.setModifiedDateTime(date);
            }
        }
    }

    public List<FeeTransaction> getFeeTransactions(Long studentId, Long academicYearId, Long courseYearId) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.createAlias("student", "student");
        crt.createAlias("studentClass", "studentClass");
        crt.createAlias("studentClass.academicYear", "ay");
        crt.createAlias("studentClass.courseYear", "courseYear");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("ay.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        return crt.list();
    }

    public List<FeeTransaction> getStudentFeeTransactions(Long studentId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(FeeTransaction.class);
        crt.createAlias("student", "student");
        crt.createAlias("academicYear", "academicYear");
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public void updateFeeTransactionTable() {
        List<FeeTransaction> feeTransactions = getSession().createCriteria(FeeTransaction.class).list();
        if (feeTransactions != null && !feeTransactions.isEmpty()) {
            for (FeeTransaction feeTransaction : feeTransactions) {
                Long installment = null;
                Long studentId = feeTransaction.getStudent().getId();
                Long courseYear = feeTransaction.getStudentClass().getCourseYear().getId();
                Long academicYear = feeTransaction.getStudentClass().getAcademicYear().getId();
                Short admissionType = feeTransaction.getStudent().getAdmissionType().getId();
                Criteria custInstCrt = getSession().createCriteria(CustomizeInstallment.class);
                custInstCrt.createAlias("academicYearClass", "academicYearClass");
                custInstCrt.add(Restrictions.eq("student.id", studentId));
                custInstCrt.add(Restrictions.eq("academicYearClass.academicYear.id", academicYear));
                Iterator it = feeTransaction.getFeeTransactionDetails().iterator();
                if (it.hasNext()) {
                    installment = ((FeeTransactionDetail) it.next()).getInstallment().getId();
                }
                List<CustomizeInstallment> customizeInstallments = custInstCrt.list();
                Criteria crt;
                if (customizeInstallments == null || customizeInstallments.isEmpty()) {
                    crt = getSession().createCriteria(AcademicYearFeeInstallment.class);
                    crt.createAlias("academicYearFee", "academicYearFee");
                    crt.add(Restrictions.eq("installment.id", installment));
                    crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYear));
                    crt.add(Restrictions.eq("academicYearFee.courseYear.id", courseYear));
                    crt.add(Restrictions.eq("academicYearFee.admissionType.id", admissionType));
                    feeTransaction.setAcademicYearFeeInstallment((AcademicYearFeeInstallment) crt.uniqueResult());
                } else {
                    crt = getSession().createCriteria(CustomizeInstallment.class);
                    crt.createAlias("academicYearClass", "academicYearClass");
                    crt.add(Restrictions.eq("student.id", studentId));
                    crt.add(Restrictions.eq("installment.id", installment));
                    crt.add(Restrictions.eq("academicYearClass.academicYear.id", academicYear));
                    feeTransaction.setCustomizeInstallment((CustomizeInstallment) crt.uniqueResult());
                }
            }
        }
    }

    public void updateCustomizeInstallmenetTable() {
        List<CustomizeInstallment> customizeInstallments = getSession().createCriteria(CustomizeInstallment.class).list();
        if (customizeInstallments != null && !customizeInstallments.isEmpty()) {
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                Criteria ayFeeCrt = getSession().createCriteria(AcademicYearFee.class);
                ayFeeCrt.add(Restrictions.eq("academicYear.id", customizeInstallment.getAcademicYearClass().getAcademicYear().getId()));
                ayFeeCrt.add(Restrictions.eq("courseYear.id", customizeInstallment.getStudent().getAcademicYearClass().getCourseYear().getId()));
                ayFeeCrt.add(Restrictions.eq("admissionType.id", customizeInstallment.getStudent().getAdmissionType().getId()));
                customizeInstallment.setAcademicYearFee((AcademicYearFee) ayFeeCrt.uniqueResult());
            }
        }
    }

    public void updateCustomizeInstallmenetCommentsTable() {
        List<FeeCustomizeComments> feeCustomizeCommentsList = getSession().createCriteria(FeeCustomizeComments.class).list();
        if (feeCustomizeCommentsList != null && !feeCustomizeCommentsList.isEmpty()) {
            for (FeeCustomizeComments feeCustomizeComments : feeCustomizeCommentsList) {
                Criteria ayFeeCrt = getSession().createCriteria(AcademicYearFee.class);
                ayFeeCrt.add(Restrictions.eq("academicYear.id", feeCustomizeComments.getStudent().getAcademicYearClass().getAcademicYear().getId()));
                ayFeeCrt.add(Restrictions.eq("courseYear.id", feeCustomizeComments.getStudent().getAcademicYearClass().getCourseYear().getId()));
                ayFeeCrt.add(Restrictions.eq("admissionType.id", feeCustomizeComments.getStudent().getAdmissionType().getId()));
                feeCustomizeComments.setAcademicYearFee((AcademicYearFee) ayFeeCrt.uniqueResult());
            }
        }
    }

    public void updateFeeDiscountTable() {
        List<FeeDiscount> feeDiscounts = getSession().createCriteria(FeeDiscount.class).list();
        if (feeDiscounts != null && !feeDiscounts.isEmpty()) {
            for (FeeDiscount feeDiscount : feeDiscounts) {
                Criteria ayFeeCrt = getSession().createCriteria(AcademicYearFee.class);
                ayFeeCrt.add(Restrictions.eq("academicYear.id", feeDiscount.getStudent().getAcademicYearClass().getAcademicYear().getId()));
                ayFeeCrt.add(Restrictions.eq("courseYear.id", feeDiscount.getStudent().getAcademicYearClass().getCourseYear().getId()));
                ayFeeCrt.add(Restrictions.eq("admissionType.id", feeDiscount.getStudent().getAdmissionType().getId()));
                feeDiscount.setAcademicYearFee((AcademicYearFee) ayFeeCrt.uniqueResult());
            }
        }
    }

    public void updateDaysOverdueTable() {
        List<DaysOverdue> daysOverdues = getSession().createCriteria(DaysOverdue.class).list();
        if (daysOverdues != null && !daysOverdues.isEmpty()) {
            for (DaysOverdue daysOverdue : daysOverdues) {
                Long installment = daysOverdue.getInstallment().getId();
                Long studentId = daysOverdue.getStudent().getId();
                Long courseYear = daysOverdue.getStudent().getAcademicYearClass().getCourseYear().getId();
                Long academicYear = daysOverdue.getStudent().getAcademicYearClass().getAcademicYear().getId();
                Short admissionType = daysOverdue.getStudent().getAdmissionType().getId();
                Criteria custInstCrt = getSession().createCriteria(CustomizeInstallment.class);
                custInstCrt.createAlias("academicYearClass", "academicYearClass");
                custInstCrt.add(Restrictions.eq("student.id", studentId));
                custInstCrt.add(Restrictions.eq("academicYearClass.academicYear.id", academicYear));
                List<CustomizeInstallment> customizeInstallments = custInstCrt.list();
                Criteria crt;
                if (customizeInstallments == null || customizeInstallments.isEmpty()) {
                    crt = getSession().createCriteria(AcademicYearFeeInstallment.class);
                    crt.createAlias("academicYearFee", "academicYearFee");
                    crt.add(Restrictions.eq("installment.id", installment));
                    crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYear));
                    crt.add(Restrictions.eq("academicYearFee.courseYear.id", courseYear));
                    crt.add(Restrictions.eq("academicYearFee.admissionType.id", admissionType));
                    daysOverdue.setAcademicYearFeeInstallment((AcademicYearFeeInstallment) crt.uniqueResult());
                } else {
                    crt = getSession().createCriteria(CustomizeInstallment.class);
                    crt.createAlias("academicYearClass", "academicYearClass");
                    crt.add(Restrictions.eq("student.id", studentId));
                    crt.add(Restrictions.eq("installment.id", installment));
                    crt.add(Restrictions.eq("academicYearClass.academicYear.id", academicYear));
                    daysOverdue.setCustomizeInstallment((CustomizeInstallment) crt.uniqueResult());
                }
            }
        }
    }

    public FeeTransaction getFeeTransaction(Long transactionid) {
        return (FeeTransaction) getHibernateTemplate().get(FeeTransaction.class, transactionid);
    }

    public List<FeeTransactionDetail> getPaidFee(Long studentId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.add(Restrictions.eq("feeTransaction.student.id", studentId));
        crt.add(Restrictions.eq("feeTransaction.academicYear.id", academicYearId));
        return crt.list();
    }

    public void updateLateFeeCalculationFlag(Long daysOverdueId, Boolean flag, Long userId) {
        DaysOverdue daysOverdue = (DaysOverdue) getHibernateTemplate().load(DaysOverdue.class, daysOverdueId);
        daysOverdue.setCalculate(flag);
        daysOverdue.setModifiedDateTime(DateUtil.getSystemDateTime());
        daysOverdue.setModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        getHibernateTemplate().update(daysOverdue);
    }

    public void removeBusStopFromAdjustedFee(Long studentId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(CustomizeInstallment.class);
        crt.createAlias("student", "student");
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("student.id", studentId));
        List<CustomizeInstallment> customizeInstallments = crt.list();
        if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            FeeHead busFeeHead = this.feeHeadDAO.getBusFeeHead(((Student) getHibernateTemplate().load(Student.class, studentId)).getInstitute().getId());
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                List<CustomizeInstallmentDetail> toBeDeleted = new ArrayList();
                if (!(customizeInstallment.getCustomizeInstallmentDetails() == null || customizeInstallment.getCustomizeInstallmentDetails().isEmpty())) {
                    for (CustomizeInstallmentDetail customizeInstallmentDetail : customizeInstallment.getCustomizeInstallmentDetails()) {
                        if (busFeeHead.getId().equals(customizeInstallmentDetail.getFeeHead().getId())) {
                            toBeDeleted.add(customizeInstallmentDetail);
                        }
                    }
                }
                if (!toBeDeleted.isEmpty()) {
                    customizeInstallment.getCustomizeInstallmentDetails().removeAll(toBeDeleted);
                }
            }
        }
        List<CustomizeInstallment> installmentsToBeDeleted = new ArrayList();
        if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            for (CustomizeInstallment customizeInstallment2 : customizeInstallments) {
                if (customizeInstallment2.getTotalFee() == 0) {
                    installmentsToBeDeleted.add(customizeInstallment2);
                }
            }
        }
        if (!installmentsToBeDeleted.isEmpty()) {
            getHibernateTemplate().deleteAll(installmentsToBeDeleted);
        }
    }
}
