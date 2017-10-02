package com.narendra.sams.fee.dao.impl;

import com.narendra.sams.admission.domain.BusStopWiseFee;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CourseDiscount;
import com.narendra.sams.admission.domain.CourseYearwiseAdmissionCount;
import com.narendra.sams.admission.domain.CourseYearwiseBusFee;
import com.narendra.sams.admission.domain.CourseYearwiseDepositFee;
import com.narendra.sams.admission.domain.CourseYearwiseDiscount;
import com.narendra.sams.admission.domain.CourseYearwiseFee;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.FeeDiscount;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentFee;
import com.narendra.sams.admission.literals.AdmissionLiteral;
import com.narendra.sams.core.dao.FeeHeadDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.fee.dao.FeeReportDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FeeReportDAOImpl extends HibernateDaoSupport implements FeeReportDAO {
    private FeeHeadDAO feeHeadDAO;

    public FeeHeadDAO getFeeHeadDAO() {
        return this.feeHeadDAO;
    }

    public void setFeeHeadDAO(FeeHeadDAO feeHeadDAO) {
        this.feeHeadDAO = feeHeadDAO;
    }

    public List<StudentFee> getCustomizedStudentFees(Long academicYearId, Long courseYearId, Date dueDate, Long studentStatusId) {
        List<StudentFee> studentDueDateFees = null;
        Criteria crt = getSession().createCriteria(CustomizeInstallmentDetail.class);
        crt.createAlias("customizeInstallment", "customizeInstallment").createAlias("customizeInstallment.academicYearFee", "academicYearFee").createAlias("customizeInstallment.student", "student").createAlias("student.classHistories", "classHistory");
        if (dueDate != null) {
            crt.add(Restrictions.disjunction().add(Restrictions.isNull("customizeInstallment.dueDate")).add(Restrictions.le("customizeInstallment.dueDate", dueDate)));
        }
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId)).add(Restrictions.eq("academicYearFee.courseYear.id", courseYearId)).add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId)).add(Restrictions.eq("classHistory.academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        crt.setProjection(Projections.projectionList().add(Projections.property("student.studentId")).add(Projections.sum("amount")).add(Projections.max("customizeInstallment.dueDate")).add(Projections.groupProperty("student.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            studentDueDateFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                StudentFee studentDueDateFee = new StudentFee();
                studentDueDateFee.setStudentId((String) objArr[0]);
                studentDueDateFee.setCustomizedFee((Long) objArr[1]);
                studentDueDateFee.setLatestDateDate((Date) objArr[2]);
                studentDueDateFees.add(studentDueDateFee);
            }
        }
        return studentDueDateFees;
    }

    public List<StudentFee> getCustomizedStudentFees(Long academicYearId, Date dueDate, Long studentStatusId) {
        List<StudentFee> studentDueDateFees = null;
        Criteria crt = getSession().createCriteria(CustomizeInstallmentDetail.class);
        crt.createAlias("customizeInstallment", "customizeInstallment").createAlias("customizeInstallment.academicYearFee", "academicYearFee").createAlias("customizeInstallment.student", "student").createAlias("student.classHistories", "classHistory");
        if (dueDate != null) {
            crt.add(Restrictions.disjunction().add(Restrictions.isNull("customizeInstallment.dueDate")).add(Restrictions.le("customizeInstallment.dueDate", dueDate)));
        }
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId)).add(Restrictions.eq("classHistory.academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        crt.setProjection(Projections.projectionList().add(Projections.property("student.studentId")).add(Projections.sum("amount")).add(Projections.max("customizeInstallment.dueDate")).add(Projections.groupProperty("student.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            studentDueDateFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                StudentFee studentDueDateFee = new StudentFee();
                studentDueDateFee.setStudentId((String) objArr[0]);
                studentDueDateFee.setCustomizedFee((Long) objArr[1]);
                studentDueDateFee.setLatestDateDate((Date) objArr[2]);
                studentDueDateFees.add(studentDueDateFee);
                if ("SPITM20130023".equals((String) objArr[0])) {
                    System.out.println("Check fee..");
                }
            }
        }
        return studentDueDateFees;
    }

    public List<Student> getCustomizedStudent(Long academicYearId, Long courseYearId, Long studentStatusId) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.createAlias("customizeInstallments", "customizeInstallment").createAlias("customizeInstallment.academicYearFee", "academicYearFee").createAlias("classHistories", "classHistory");
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId)).add(Restrictions.eq("academicYearFee.courseYear.id", courseYearId)).add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId)).add(Restrictions.eq("classHistory.academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        return crt.list();
    }

    public List<Student> getFeeAdjustedStudents(Long academicYearId, Long studentStatusId) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.createAlias("customizeInstallments", "customizeInstallment").createAlias("customizeInstallment.academicYearFee", "academicYearFee").createAlias("classHistories", "classHistory");
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId)).add(Restrictions.eq("classHistory.academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<CourseYearwiseAdmissionCount> getAdmissionCount(Long academicYearId, List<Long> courseYears, Long studentStatusId, Date feeDueDate) {
        Map<Long, CourseYearwiseAdmissionCount> admissionTypeCountMap = null;
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.createAlias("academicYearClass.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        if (courseYears != null) {
            crt.add(Restrictions.in("courseYear.id", courseYears));
        }
        if (feeDueDate != null) {
            crt.createAlias("academicYearClass.courseYearSetting", "courseYearSetting");
            crt.createAlias("courseYearSetting.academicYearFees", "academicYearFee");
            crt.createAlias("academicYearFee.academicYearFeeInstallments", "academicYearFeeInstallment");
            crt.add(Restrictions.isNotNull("academicYearFeeInstallment.dueDate")).add(Restrictions.le("academicYearFeeInstallment.dueDate", feeDueDate));
        }
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.countDistinct("student.id"), "admissionCount").add(Projections.groupProperty("courseYear.id")).add(Projections.groupProperty("admissionType.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            admissionTypeCountMap = new LinkedHashMap();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                CourseYearwiseAdmissionCount admissionTypeCount = (CourseYearwiseAdmissionCount) admissionTypeCountMap.get((Long) objArr[2]);
                if (admissionTypeCount == null) {
                    admissionTypeCount = new CourseYearwiseAdmissionCount((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3]);
                }
                if (AdmissionLiteral.ADMISSION_TYPE_NEW_DB_ID.equals((Short) objArr[6])) {
                    admissionTypeCount.setNewAdmissionCount(((Long) objArr[4]).longValue());
                } else if (AdmissionLiteral.ADMISSION_TYPE_REGULAR_DB_ID.equals((Short) objArr[6])) {
                    admissionTypeCount.setRegularAdmissionCount(((Long) objArr[4]).longValue());
                }
                admissionTypeCountMap.put((Long) objArr[2], admissionTypeCount);
            }
        }
        if (admissionTypeCountMap != null) {
            return new ArrayList(admissionTypeCountMap.values());
        }
        return null;
    }

    public List<CourseYearwiseFee> getCourseYearwiseFee(Long academicYearId) {
        Map<Long, CourseYearwiseFee> courseYearwiseFeeMap = null;
        Criteria crt = getSession().createCriteria(AcademicYearFeeDetail.class);
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.admissionType", "admissionType");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.sum("amount"), "totalFee").add(Projections.groupProperty("courseYear.id")).add(Projections.groupProperty("admissionType.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            courseYearwiseFeeMap = new LinkedHashMap();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                CourseYearwiseFee courseYearwiseFee = (CourseYearwiseFee) courseYearwiseFeeMap.get((Long) objArr[2]);
                if (courseYearwiseFee == null) {
                    courseYearwiseFee = new CourseYearwiseFee((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3]);
                }
                if (AdmissionLiteral.ADMISSION_TYPE_NEW_DB_ID.equals((Short) objArr[6])) {
                    courseYearwiseFee.setNewAdmissionFee(((Long) objArr[4]).longValue());
                } else if (AdmissionLiteral.ADMISSION_TYPE_REGULAR_DB_ID.equals((Short) objArr[6])) {
                    courseYearwiseFee.setRegularAdmissionFee(((Long) objArr[4]).longValue());
                }
                courseYearwiseFeeMap.put((Long) objArr[2], courseYearwiseFee);
            }
        }
        if (courseYearwiseFeeMap != null) {
            return new ArrayList(courseYearwiseFeeMap.values());
        }
        return null;
    }

    public List<CourseYearwiseDiscount> getCourseYearwiseDiscount(Long academicYearId, List<Long> courseYearIds, Long studentStatus) {
        List<CourseYearwiseDiscount> courseYearwiseDiscounts = null;
        Criteria crt = getSession().createCriteria(FeeDiscount.class);
        crt.createAlias("feeHead", "feeHead");
        crt.createAlias("student", "student");
        crt.createAlias("student.classHistories", "classHistory");
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        if (!(courseYearIds == null || courseYearIds.isEmpty())) {
            crt.add(Restrictions.in("courseYear.id", courseYearIds));
        }
        crt.add(Restrictions.ne("feeHead.name", "Late Fee").ignoreCase());
        crt.add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        crt.add(Restrictions.eq("classHistory.studentStatus.id", studentStatus));
        crt.add(Restrictions.eq("classHistory.academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.sum("amount"), "totalDiscount").add(Projections.groupProperty("courseYear.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            courseYearwiseDiscounts = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                courseYearwiseDiscounts.add(new CourseYearwiseDiscount((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3], (Long) objArr[4]));
            }
        }
        return courseYearwiseDiscounts;
    }

    public List<CourseYearwiseDepositFee> getCourseYearwiseDepositFee(Long academicYearId, List<Long> courseYearIds, Long studentStatusId, Boolean isCustomized, Boolean isLateFee) {
        FeeHead lateFeeHead = this.feeHeadDAO.getLateFeeHead(((AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId)).getInstitute().getId());
        List<CourseYearwiseDepositFee> courseYearwiseDepositFees = null;
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeHead", "feeHead");
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.student", "student");
        crt.createAlias("student.classHistories", "classHistory");
        if (isCustomized.booleanValue()) {
            crt.createAlias("feeTransaction.customizeInstallment", "feeInstallment");
        } else {
            crt.createAlias("feeTransaction.academicYearFeeInstallment", "feeInstallment");
        }
        crt.createAlias("feeInstallment.academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.add(Restrictions.eq("classHistory.academicYear.id", academicYearId));
        crt.add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        crt.add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId));
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId)).add(Restrictions.in("academicYearFee.courseYear.id", courseYearIds));
        if (isLateFee != null) {
            if (Boolean.TRUE.equals(isLateFee)) {
                crt.add(Restrictions.eq("feeHead.id", lateFeeHead.getId()));
            } else {
                crt.add(Restrictions.ne("feeHead.id", lateFeeHead.getId()));
            }
        }
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.sum("amount"), "totalDeposited").add(Projections.groupProperty("courseYear.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            courseYearwiseDepositFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                courseYearwiseDepositFees.add(new CourseYearwiseDepositFee((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3], (Long) objArr[4]));
            }
        }
        return courseYearwiseDepositFees;
    }

    public List<CourseYearwiseDepositFee> getCourseYearwiseDepositFee(Long academicYearId, List<Long> courseYearIds, Date dueDate, Boolean isCustomized, Boolean isAdvance, Boolean isLateFee, Long studentStatusId) {
        FeeHead lateFeeHead = this.feeHeadDAO.getLateFeeHead(((AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId)).getInstitute().getId());
        List<CourseYearwiseDepositFee> courseYearwiseDepositFees = null;
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeHead", "feeHead");
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.student", "student");
        crt.createAlias("student.classHistories", "classHistory");
        if (isCustomized.booleanValue()) {
            crt.createAlias("feeTransaction.customizeInstallment", "feeInstallment");
        } else {
            crt.createAlias("feeTransaction.academicYearFeeInstallment", "feeInstallment");
        }
        crt.createAlias("feeInstallment.academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.add(Restrictions.eq("classHistory.academicYear.id", academicYearId));
        crt.add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        crt.add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId));
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId)).add(Restrictions.in("academicYearFee.courseYear.id", courseYearIds));
        if (isAdvance != null) {
            crt.add(Restrictions.ne("feeHead.id", lateFeeHead.getId()));
            if (isAdvance.booleanValue()) {
                crt.add(Restrictions.gt("feeInstallment.dueDate", dueDate));
            } else {
                crt.add(Restrictions.isNotNull("feeInstallment.dueDate")).add(Restrictions.le("feeInstallment.dueDate", dueDate));
            }
        } else if (isLateFee != null && isLateFee.booleanValue()) {
            crt.add(Restrictions.eq("feeHead.id", lateFeeHead.getId()));
        }
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.sum("amount"), "totalDeposited").add(Projections.groupProperty("courseYear.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            courseYearwiseDepositFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                courseYearwiseDepositFees.add(new CourseYearwiseDepositFee((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3], (Long) objArr[4]));
            }
        }
        return courseYearwiseDepositFees;
    }

    public List<CourseYearwiseBusFee> getCourseYearwiseBusFee(Long academicYearId, List<Long> courseYearIds, Boolean includeCustomizeStudents, Date dueDate) {
        List<CourseYearwiseBusFee> courseYearwiseBusFees = null;
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student");
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.createAlias("busStop", "busStop");
        crt.createAlias("busStop.busFees", "busFee");
        crt.createAlias("busFee.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        if (!(courseYearIds == null || courseYearIds.isEmpty())) {
            crt.add(Restrictions.in("courseYear.id", courseYearIds));
        }
        if (includeCustomizeStudents != null && Boolean.FALSE.equals(includeCustomizeStudents)) {
            crt.add(Restrictions.isEmpty("student.customizeInstallments"));
        }
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.sum("busFee.rs"), "busFeeSum").add(Projections.groupProperty("courseYear.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            courseYearwiseBusFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                courseYearwiseBusFees.add(new CourseYearwiseBusFee((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3], (Long) objArr[4]));
            }
        }
        return courseYearwiseBusFees;
    }

    public List<BusStopWiseFee> getBusStopWiseAllStudents(Long academicYearId, Long studentStatusId, Short admissionType) {
        List<BusStopWiseFee> busStopWiseFees = null;
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.createAlias("academicYearClass.courseYearSetting", "courseYearSetting");
        crt.createAlias("courseYearSetting.academicYearFees", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        crt.add(Restrictions.isNotNull("busStop.id"));
        if (admissionType != null) {
            crt.add(Restrictions.eq("admissionType.id", admissionType));
        }
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("student.id"), "students").add(Projections.groupProperty("courseYear.id")).add(Projections.groupProperty("busStop.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            busStopWiseFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                BusStopWiseFee busStopWiseFee = new BusStopWiseFee();
                busStopWiseFee.setStudentCount((Long) objArr[0]);
                busStopWiseFee.setCourseYearId((Long) objArr[1]);
                busStopWiseFee.setBusStopId((Long) objArr[2]);
                busStopWiseFees.add(busStopWiseFee);
            }
        }
        return busStopWiseFees;
    }

    public List<BusStopWiseFee> getBusStopWiseCustomizedStudents(Long academicYearId, Long studentStatusId, Short admissionType) {
        List<BusStopWiseFee> busStopWiseFees = null;
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.academicYear", "classAy");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.createAlias("student", "student");
        crt.createAlias("student.customizeInstallments", "customizeInstallment");
        crt.createAlias("customizeInstallment.academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.add(Restrictions.eq("studentStatus.id", studentStatusId));
        crt.add(Restrictions.eq("admissionType.id", admissionType));
        crt.add(Restrictions.eq("activeClass", Boolean.TRUE));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("classAy.id", academicYearId));
        crt.add(Restrictions.isNotNull("busStop.id"));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("student.id"), "students").add(Projections.groupProperty("courseYear.id")).add(Projections.groupProperty("busStop.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            busStopWiseFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                BusStopWiseFee busStopWiseFee = new BusStopWiseFee();
                busStopWiseFee.setStudentCount((Long) objArr[0]);
                busStopWiseFee.setCourseYearId((Long) objArr[1]);
                busStopWiseFee.setBusStopId((Long) objArr[2]);
                busStopWiseFees.add(busStopWiseFee);
            }
        }
        return busStopWiseFees;
    }

    public long getBusFeePercentage(Long academicYearId, Long courseYearId, Short admissionType, Date dueDate) {
        long feePercent = 0;
        Criteria criteria = getSession().createCriteria(AcademicYearFeeInstallment.class);
        criteria.createAlias("academicYearFee", "academicYearFee");
        criteria.createAlias("academicYearFee.admissionType", "admissionType");
        criteria.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId));
        if (courseYearId != null) {
            criteria.add(Restrictions.eq("academicYearFee.courseYear.id", courseYearId));
        }
        if (admissionType != null) {
            criteria.add(Restrictions.eq("admissionType.id", admissionType));
        }
        criteria.add(Restrictions.isNotNull("dueDate"));
        criteria.add(Restrictions.le("dueDate", dueDate));
        List<AcademicYearFeeInstallment> academicYearFeeInstallments = criteria.list();
        if (!(academicYearFeeInstallments == null || academicYearFeeInstallments.isEmpty())) {
            List<Long> installmentIds = new ArrayList();
            for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                installmentIds.add(academicYearFeeInstallment.getInstallment().getId());
            }
            Criteria crt = getSession().createCriteria(BusFeeInstallmentDetail.class);
            crt.createAlias("busFeeInstallment", "busFeeInstallment");
            crt.add(Restrictions.eq("busFeeInstallment.academicYear.id", academicYearId));
            crt.add(Restrictions.in("installment.id", installmentIds));
            List<BusFeeInstallmentDetail> busFeeInstallmentDetails = crt.list();
            if (!(busFeeInstallmentDetails == null || busFeeInstallmentDetails.isEmpty())) {
                for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallmentDetails) {
                    if (busFeeInstallmentDetail.getFeePercent() != null) {
                        feePercent += busFeeInstallmentDetail.getFeePercent().longValue();
                    }
                }
            }
        }
        return feePercent;
    }

    public List<CourseYearwiseFee> getCourseYearwiseFee(Long academicYearId, Collection<Long> courseYears, Date dueDate) {
        Map<Long, CourseYearwiseFee> courseYearwiseFeeMap = null;
        Criteria crt = getSession().createCriteria(AcademicYearFeeInstallmentDetail.class);
        crt.createAlias("academicYearFeeInstallment", "installment");
        crt.createAlias("installment.academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.admissionType", "admissionType");
        crt.createAlias("courseYear.course", "course");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        if (dueDate != null) {
            crt.add(Restrictions.isNotNull("installment.dueDate"));
            crt.add(Restrictions.le("installment.dueDate", dueDate));
        }
        if (courseYears != null) {
            crt.add(Restrictions.in("courseYear.id", courseYears));
        }
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.sum("amount"), "totalFee").add(Projections.property("academicYear.id")).add(Projections.groupProperty("courseYear.id")).add(Projections.groupProperty("admissionType.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            courseYearwiseFeeMap = new LinkedHashMap();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                CourseYearwiseFee courseYearwiseFee = (CourseYearwiseFee) courseYearwiseFeeMap.get((Long) objArr[2]);
                if (courseYearwiseFee == null) {
                    courseYearwiseFee = new CourseYearwiseFee((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3]);
                }
                if (AdmissionLiteral.ADMISSION_TYPE_NEW_DB_ID.equals((Short) objArr[7])) {
                    courseYearwiseFee.setNewAdmissionFee(((Long) objArr[4]).longValue());
                } else if (AdmissionLiteral.ADMISSION_TYPE_REGULAR_DB_ID.equals((Short) objArr[7])) {
                    courseYearwiseFee.setRegularAdmissionFee(((Long) objArr[4]).longValue());
                }
                courseYearwiseFee.setAcademicYearId((Long) objArr[5]);
                courseYearwiseFeeMap.put((Long) objArr[2], courseYearwiseFee);
            }
        }
        if (courseYearwiseFeeMap != null) {
            return new ArrayList(courseYearwiseFeeMap.values());
        }
        return null;
    }

    public List<CourseYearwiseAdmissionCount> getCustomizeAdmissionCount(Long academicYearId, List<Long> courseYears, Date dueDate, Long studentStatusId) {
        Map<Long, CourseYearwiseAdmissionCount> admissionTypeCountMap = null;
        Criteria crt = getSession().createCriteria(CustomizeInstallmentDetail.class);
        crt.createAlias("customizeInstallment", "customizeInstallment");
        crt.createAlias("customizeInstallment.student", "student");
        crt.createAlias("student.classHistories", "classHistory");
        crt.createAlias("classHistory.admissionType", "admissionType");
        crt.createAlias("classHistory.academicYearClass", "academicYearClass");
        crt.createAlias("customizeInstallment.academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.createAlias("academicYearClass.academicYear", "classAcademicYear");
        crt.add(Restrictions.eq("classAcademicYear.id", academicYearId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId));
        crt.add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        if (courseYears != null) {
            crt.add(Restrictions.in("courseYear.id", courseYears));
        }
        if (dueDate != null) {
            crt.add(Restrictions.isNotNull("customizeInstallment.dueDate")).add(Restrictions.le("customizeInstallment.dueDate", dueDate));
        }
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.countDistinct("classHistory.student.id"), "admissionCount").add(Projections.sum("amount"), "sumOfAmount").add(Projections.groupProperty("courseYear.id")).add(Projections.groupProperty("admissionType.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            admissionTypeCountMap = new LinkedHashMap();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                CourseYearwiseAdmissionCount admissionTypeCount = (CourseYearwiseAdmissionCount) admissionTypeCountMap.get((Long) objArr[2]);
                if (admissionTypeCount == null) {
                    admissionTypeCount = new CourseYearwiseAdmissionCount((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3]);
                }
                if (AdmissionLiteral.ADMISSION_TYPE_NEW_DB_ID.equals((Short) objArr[7])) {
                    admissionTypeCount.setNewAdmissionCount(((Long) objArr[4]).longValue());
                    if (objArr[4] != null) {
                        admissionTypeCount.setNewAdmissionFee(((Long) objArr[5]).longValue());
                    } else {
                        admissionTypeCount.setNewAdmissionFee(0);
                    }
                } else if (AdmissionLiteral.ADMISSION_TYPE_REGULAR_DB_ID.equals((Short) objArr[7])) {
                    admissionTypeCount.setRegularAdmissionCount(((Long) objArr[4]).longValue());
                    if (objArr[5] != null) {
                        admissionTypeCount.setRegularAdmissionFee(((Long) objArr[5]).longValue());
                    } else {
                        admissionTypeCount.setRegularAdmissionFee(0);
                    }
                }
                admissionTypeCountMap.put((Long) objArr[2], admissionTypeCount);
            }
        }
        if (admissionTypeCountMap != null) {
            return new ArrayList(admissionTypeCountMap.values());
        }
        return null;
    }

    public List<CourseYearwiseBusFee> getCourseYearwiseBusFeeDiscount(Long academicYearId, List<Long> courseYearIds) {
        FeeHead busFeeHead = this.feeHeadDAO.getBusFeeHead(((AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId)).getInstitute().getId());
        List<CourseYearwiseBusFee> courseYearwiseBusFees = null;
        Criteria crt = getSession().createCriteria(FeeDiscount.class);
        crt.createAlias("feeHead", "feeHead");
        crt.createAlias("academicYearClass", "academicYearClass");
        crt.createAlias("academicYearClass.academicYear", "academicYear");
        crt.createAlias("academicYearClass.courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        if (!(courseYearIds == null || courseYearIds.isEmpty())) {
            crt.add(Restrictions.in("courseYear.id", courseYearIds));
        }
        crt.add(Restrictions.eq("feeHead.id", busFeeHead.getId()));
        crt.setProjection(Projections.projectionList().add(Projections.property("course.id")).add(Projections.property("course.displayName")).add(Projections.property("courseYear.id")).add(Projections.property("courseYear.name")).add(Projections.sum("amount"), "amountSum").add(Projections.groupProperty("courseYear.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            courseYearwiseBusFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                if (objArr[4] != null) {
                    courseYearwiseBusFees.add(new CourseYearwiseBusFee((Long) objArr[0], (String) objArr[1], (Long) objArr[2], (Short) objArr[3], (Long) objArr[4]));
                }
            }
        }
        return courseYearwiseBusFees;
    }

    public List<StudentFee> getPaidFeeByStudents(Collection<String> students, Long academicYearId, Long courseYearId, Date dueDate, Boolean isCustomized, Boolean isLateFee, Boolean isAdvance) {
        FeeHead lateFeeHead = this.feeHeadDAO.getLateFeeHead(((AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId)).getInstitute().getId());
        Criteria crt = getSession().createCriteria(FeeTransactionDetail.class);
        crt.createAlias("feeTransaction", "feeTransaction");
        crt.createAlias("feeTransaction.student", "student");
        crt.createAlias("feeHead", "feeHead");
        crt.add(Restrictions.in("student.studentId", students));
        if (isCustomized.booleanValue()) {
            crt.createAlias("feeTransaction.customizeInstallment", "feeInstallment");
        } else {
            crt.createAlias("feeTransaction.academicYearFeeInstallment", "feeInstallment");
        }
        crt.createAlias("feeInstallment.academicYearFee", "academicYearFee");
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId));
        if (courseYearId != null) {
            crt.add(Restrictions.eq("academicYearFee.courseYear.id", courseYearId));
        }
        if (isAdvance != null) {
            crt.add(Restrictions.ne("feeHead.id", lateFeeHead.getId()));
            if (isAdvance.booleanValue()) {
                crt.add(Restrictions.disjunction().add(Restrictions.isNull("feeInstallment.dueDate")).add(Restrictions.gt("feeInstallment.dueDate", dueDate)));
            } else {
                crt.add(Restrictions.isNotNull("feeInstallment.dueDate")).add(Restrictions.le("feeInstallment.dueDate", dueDate));
            }
        } else if (isLateFee != null && isLateFee.booleanValue()) {
            crt.add(Restrictions.eq("feeHead.id", lateFeeHead.getId()));
        }
        crt.setProjection(Projections.projectionList().add(Projections.property("student.studentId")).add(Projections.sum("amount"), "amountSum").add(Projections.groupProperty("student.id")));
        List<StudentFee> studentFees = null;
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            studentFees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                StudentFee studentFee = new StudentFee();
                studentFee.setStudentId((String) objArr[0]);
                studentFee.setFeePlaceHolder(((Long) objArr[1]).longValue());
                studentFees.add(studentFee);
            }
        }
        return studentFees;
    }

    public List<CourseDiscount> getCustomizedStudentFees(Long academicYearId, Long courseYearId, Long studentStatusId) {
        List<CourseDiscount> fees = null;
        Criteria crt = getSession().createCriteria(CustomizeInstallmentDetail.class);
        crt.createAlias("customizeInstallment", "customizeInstallment").createAlias("customizeInstallment.academicYearFee", "academicYearFee").createAlias("customizeInstallment.student", "student").createAlias("student.classHistories", "classHistory");
        crt.add(Restrictions.eq("academicYearFee.academicYear.id", academicYearId)).add(Restrictions.eq("academicYearFee.courseYear.id", courseYearId)).add(Restrictions.eq("classHistory.studentStatus.id", studentStatusId)).add(Restrictions.eq("classHistory.academicYear.id", academicYearId)).add(Restrictions.eq("classHistory.activeClass", Boolean.TRUE));
        crt.setProjection(Projections.projectionList().add(Projections.property("student.studentId")).add(Projections.property("student.firstName")).add(Projections.property("student.lastName")).add(Projections.sum("amount")).add(Projections.groupProperty("student.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            fees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                CourseDiscount courseDiscount = new CourseDiscount();
                courseDiscount.setStudentId((String) objArr[0]);
                courseDiscount.setStudentFirstName((String) objArr[1]);
                courseDiscount.setStudentLastName((String) objArr[2]);
                courseDiscount.setTotalFee((Long) objArr[3]);
                fees.add(courseDiscount);
            }
        }
        return fees;
    }

    public List<CourseDiscount> getDiscountGiven(Long academicYearId, Long courseYearId) {
        List<CourseDiscount> fees = null;
        Criteria crt = getSession().createCriteria(FeeDiscount.class);
        crt.createAlias("academicYearFee", "academicYearFee");
        crt.createAlias("academicYearFee.academicYear", "academicYear");
        crt.createAlias("academicYearFee.courseYear", "courseYear");
        crt.createAlias("student", "student");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("courseYear.id", courseYearId));
        crt.setProjection(Projections.projectionList().add(Projections.property("student.studentId")).add(Projections.sum("amount")).add(Projections.groupProperty("student.id")));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            fees = new ArrayList();
            while (it.hasNext()) {
                Object[] objArr = (Object[]) it.next();
                CourseDiscount courseDiscount = new CourseDiscount();
                courseDiscount.setStudentId((String) objArr[0]);
                courseDiscount.setDiscount((Long) objArr[1]);
                fees.add(courseDiscount);
            }
        }
        return fees;
    }
}
