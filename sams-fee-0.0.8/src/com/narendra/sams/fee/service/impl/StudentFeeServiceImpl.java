package com.narendra.sams.fee.service.impl;

import com.narendra.sams.admission.dao.StudentDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.HeadWiseDueFee;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentDueFee;
import com.narendra.sams.admission.domain.StudentDueFeeHeadWise;
import com.narendra.sams.admission.domain.StudentDueInstallment;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.fee.dao.StudentFeeDAO;
import com.narendra.sams.fee.service.StudentFeeService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class StudentFeeServiceImpl implements StudentFeeService {
    private AcademicYearBusFeeService academicYearBusFeeService;
    private AcademicYearDAO academicYearDAO;
    private AcademicYearFeeService academicYearFeeService;
    private FeeHeadService feeHeadService;
    private StudentDAO studentDAO;
    private StudentFeeDAO studentFeeDAO;
    private StudentService studentService;

    public StudentFeeDAO getStudentFeeDAO() {
        return this.studentFeeDAO;
    }

    public void setStudentFeeDAO(StudentFeeDAO studentFeeDAO) {
        this.studentFeeDAO = studentFeeDAO;
    }

    public StudentDAO getStudentDAO() {
        return this.studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public AcademicYearFeeService getAcademicYearFeeService() {
        return this.academicYearFeeService;
    }

    public void setAcademicYearFeeService(AcademicYearFeeService academicYearFeeService) {
        this.academicYearFeeService = academicYearFeeService;
    }

    public StudentService getStudentService() {
        return this.studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public AcademicYearBusFeeService getAcademicYearBusFeeService() {
        return this.academicYearBusFeeService;
    }

    public void setAcademicYearBusFeeService(AcademicYearBusFeeService academicYearBusFeeService) {
        this.academicYearBusFeeService = academicYearBusFeeService;
    }

    public FeeHeadService getFeeHeadService() {
        return this.feeHeadService;
    }

    public void setFeeHeadService(FeeHeadService feeHeadService) {
        this.feeHeadService = feeHeadService;
    }

    public AcademicYearDAO getAcademicYearDAO() {
        return this.academicYearDAO;
    }

    public void setAcademicYearDAO(AcademicYearDAO academicYearDAO) {
        this.academicYearDAO = academicYearDAO;
    }

    public List<CustomizeInstallment> getCustomizeInstallments(Long studentId, Long academicYearFeeId) {
        return this.studentFeeDAO.getCustomizeInstallments(studentId, academicYearFeeId);
    }

    public List<FeeTransactionDetail> getAllTransactionDetails(Long studentId, Long academicYearFeeId, Boolean isCustomized) {
        return this.studentFeeDAO.getAllTransactionDetails(studentId, academicYearFeeId, isCustomized);
    }

    public List<DaysOverdue> getDayOverdue(Long studentId, Long academicYearId) {
        return this.studentFeeDAO.getDayOverdue(studentId, academicYearId);
    }

    public void saveOrUpdateDaysOverdue(List<DaysOverdue> daysOverdues) {
        if (daysOverdues != null) {
            this.studentFeeDAO.saveOrUpdateDaysOverdue(daysOverdues);
        }
    }

    public DaysOverdue getDaysOverdue(Long studentId, Long academicYearId, Long installmentId) {
        return this.studentFeeDAO.getDaysOverdue(studentId, academicYearId, installmentId);
    }

    public List<FeeTransactionDetail> getPaidFeeDetails(Long studentId, Long academicYearId, Long courseYearId, Short[] installmentIds) {
        return this.studentFeeDAO.getPaidFeeDetails(studentId, academicYearId, courseYearId, installmentIds);
    }

    public List<FeeTransactionDetail> getPaidFeeDetailsByAcdemicYearFeeInstallmentId(Long studentId, Long academicYearFeeInstallmentId) {
        return this.studentFeeDAO.getPaidFeeDetailsByAcdemicYearFeeInstallmentId(studentId, academicYearFeeInstallmentId);
    }

    public List<FeeTransactionDetail> getTransactionDetailsOfCustomizedInstallment(Long studentId, Long custmizeInstallmentId) {
        return this.studentFeeDAO.getTransactionDetailsOfCustomizedInstallment(studentId, custmizeInstallmentId);
    }

    public List<FeeTransaction> getFeeTransactions(Long studentid) {
        return this.studentFeeDAO.getFeeTransactions(studentid);
    }

    public List<Student> getStudentsWhoseFeeIsCustomized(Long academicYearId, String studentName) {
        return this.studentFeeDAO.getStudentsWhoseFeeIsCustomized(academicYearId, studentName);
    }

    public List<DaysOverdue> getLateFeeDetails(Long studentId, Long academicYearId) {
        return this.studentFeeDAO.getLateFeeDetails(studentId, academicYearId);
    }

    public void customizeLateFee(List<DaysOverdue> daysOverdues, Long userId) {
        this.studentFeeDAO.customizeLateFee(daysOverdues, userId);
    }

    public List<FeeTransaction> getFeeTransactions(Long studentId, Long academicYearId, Long courseYearId) {
        return this.studentFeeDAO.getFeeTransactions(studentId, academicYearId, courseYearId);
    }

    public List<FeeTransaction> getStudentFeeTransactions(Long studentId, Long academicYearId) {
        return this.studentFeeDAO.getStudentFeeTransactions(studentId, academicYearId);
    }

    public List<FeeTransaction> getFeeTransactionsOnAcademicYearFee(Long studentId, Long academicYearFeeId, Long installmentId) {
        return this.studentFeeDAO.getFeeTransactionsOnAcademicYearFee(studentId, academicYearFeeId, installmentId);
    }

    public void updateFeeTransactionTable() {
        this.studentFeeDAO.updateFeeTransactionTable();
    }

    public void updateCustomizeInstallmenetTable() {
        this.studentFeeDAO.updateCustomizeInstallmenetTable();
    }

    public void updateCustomizeInstallmenetCommentsTable() {
        this.studentFeeDAO.updateCustomizeInstallmenetCommentsTable();
    }

    public void updateFeeDiscountTable() {
        this.studentFeeDAO.updateFeeDiscountTable();
    }

    public void updateDaysOverdueTable() {
        this.studentFeeDAO.updateDaysOverdueTable();
    }

    public void saveAdmissiondate(Long academicYearId) {
        for (ClassHistory classHistory : this.studentFeeDAO.getAllClassHistories(academicYearId)) {
            Date date = this.studentFeeDAO.getFirstTransactionDatetime(classHistory.getStudent().getId(), academicYearId);
            if (date != null) {
                classHistory.setAdmissionConfirmDateTime(date);
                classHistory.setCreatedDate(date);
                classHistory.setModifiedDate(date);
            } else {
                classHistory.setCreatedDate(classHistory.getModifiedDate());
            }
        }
    }

    public FeeTransaction getFeeTransaction(Long transactionid) {
        return this.studentFeeDAO.getFeeTransaction(transactionid);
    }

    public void updateLateFeeCalculationFlag(Long daysOverdueId, Boolean flag, Long userId) {
        this.studentFeeDAO.updateLateFeeCalculationFlag(daysOverdueId, flag, userId);
    }

    public void removeBusStopFromAdjustedFee(Long studentId, Long academicYearId) {
        this.studentFeeDAO.removeBusStopFromAdjustedFee(studentId, academicYearId);
    }

    public Boolean isCurrentClassFeeCustomized(Long studentId) {
        Student student = this.studentDAO.getStudentById(studentId);
        List<CustomizeInstallment> customizeInstallments = getCustomizeInstallments(student.getId(), this.academicYearFeeService.getAcademicYearFee(student.getAcademicYearClass().getAcademicYear().getId(), student.getAcademicYearClass().getCourseYear().getId(), student.getAdmissionType().getId()).getId());
        if (customizeInstallments == null || customizeInstallments.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<StudentDueFee> getDueStudentsForNotice(Collection<Long> studentIds, Long academicYearId, Date dueDate) {
        List<StudentDueFee> students = new ArrayList();
        for (Long studentId : studentIds) {
            students.add(getStudentDueFee(studentId, academicYearId, dueDate));
        }
        return students;
    }

    public StudentDueFee getStudentDueFee(Long studentId, Long academicYearId, Date dueDate) {
        StudentDueFee studentDueFee = new StudentDueFee();
        Student student = this.studentService.getStudentById(studentId);
        ClassHistory studentActiveClass = student.getActiveClassHistory(academicYearId);
        studentDueFee.setStudentId(student.getStudentId());
        if (student.getFatherName() == null || student.getFatherName().trim().isEmpty()) {
            studentDueFee.setStudentName(student.getFullName());
        } else if ("male".equals(student.getGender())) {
            studentDueFee.setStudentName(student.getFullName() + " S/O " + student.getFatherName());
        } else {
            studentDueFee.setStudentName(student.getFullName() + " D/O " + student.getFatherName());
        }
        studentDueFee.setAcademicYear(studentActiveClass.getAcademicYear().getName());
        studentDueFee.setStudentClass(studentActiveClass.getAcademicYearClass().prepareClassName());
        AcademicYearFee academicYearFee = this.academicYearFeeService.getAcademicYearFee(studentActiveClass.getAcademicYearClass().getAcademicYear().getId(), studentActiveClass.getAcademicYearClass().getCourseYear().getId(), studentActiveClass.getAdmissionType().getId());
        if (academicYearFee == null) {
            return null;
        }
        StudentDueInstallment studentDueInstallment;
        BusStop feeBusStop = studentActiveClass.getBusStop();
        BusFee busFee = null;
        BusFeeInstallment busFeeInstallment = null;
        if (feeBusStop != null) {
            busFee = this.academicYearBusFeeService.getBusFee(academicYearId, feeBusStop.getId());
            busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(academicYearId);
        }
        Boolean isCustomized = Boolean.FALSE;
        List<CustomizeInstallment> customizeInstallments = getCustomizeInstallments(studentActiveClass.getStudent().getId(), academicYearFee.getId());
        if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            isCustomized = Boolean.TRUE;
        }
        List<FeeTransactionDetail> feeTransactionDetails = getAllTransactionDetails(studentActiveClass.getStudent().getId(), academicYearFee.getId(), isCustomized);
        Map<String, StudentDueInstallment> dueInstallmentsMap = new HashMap();
        if (isCustomized.booleanValue()) {
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                if (customizeInstallment.getDueDate() != null && (customizeInstallment.getDueDate().before(dueDate) || customizeInstallment.getDueDate().equals(dueDate))) {
                    studentDueInstallment = new StudentDueInstallment();
                    studentDueInstallment.setDueDate(customizeInstallment.getDueDate());
                    studentDueInstallment.setInstallment(customizeInstallment.getInstallment().getName());
                    studentDueInstallment.setInstallmentId(customizeInstallment.getInstallment().getId());
                    studentDueInstallment.setFee(Long.valueOf(customizeInstallment.getTotalFee() + customizeInstallment.getTotalBusFee()));
                    dueInstallmentsMap.put(studentDueInstallment.getInstallment(), studentDueInstallment);
                }
            }
        } else {
            if (!(academicYearFee == null || academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty())) {
                for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFee.getAcademicYearFeeInstallments()) {
                    if (academicYearFeeInstallment.getDueDate() != null && (academicYearFeeInstallment.getDueDate().before(dueDate) || academicYearFeeInstallment.getDueDate().equals(dueDate))) {
                        studentDueInstallment = new StudentDueInstallment();
                        studentDueInstallment.setDueDate(academicYearFeeInstallment.getDueDate());
                        studentDueInstallment.setInstallment(academicYearFeeInstallment.getInstallment().getName());
                        studentDueInstallment.setInstallmentId(academicYearFeeInstallment.getInstallment().getId());
                        studentDueInstallment.setFee(Long.valueOf(academicYearFeeInstallment.getTotal()));
                        dueInstallmentsMap.put(studentDueInstallment.getInstallment(), studentDueInstallment);
                    }
                }
            }
            if (!(dueInstallmentsMap.isEmpty() || busFee == null || busFeeInstallment == null || busFeeInstallment.getBusFeeInstallmentDetails() == null || busFeeInstallment.getBusFeeInstallmentDetails().isEmpty())) {
                for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                    studentDueInstallment = (StudentDueInstallment) dueInstallmentsMap.get(busFeeInstallmentDetail.getInstallment().getName());
                    if (!(studentDueInstallment == null || busFee.getRs() == null || busFeeInstallmentDetail.getFeePercent() == null)) {
                        studentDueInstallment.setFee(Long.valueOf(studentDueInstallment.getFee().longValue() + ((busFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100)));
                    }
                }
            }
        }
        if (!(dueInstallmentsMap.isEmpty() || feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
                if (!"Late Fee".equalsIgnoreCase(feeTransactionDetail.getFeeHead().getName())) {
                    studentDueInstallment = (StudentDueInstallment) dueInstallmentsMap.get(feeTransactionDetail.getInstallment().getName());
                    if (studentDueInstallment != null) {
                        studentDueInstallment.setPaidFee(Long.valueOf(studentDueInstallment.getPaidFee().longValue() + feeTransactionDetail.getAmount().longValue()));
                    }
                }
            }
        }
        List<StudentDueInstallment> dueInstallmentList = new ArrayList();
        for (String key : dueInstallmentsMap.keySet()) {
            if (((StudentDueInstallment) dueInstallmentsMap.get(key)).getUnpaidFee().longValue() != 0) {
                dueInstallmentList.add((StudentDueInstallment) dueInstallmentsMap.get(key));
            }
        }
        Collections.sort(dueInstallmentList, new BeanComparator("installmentId", new NullComparator()));
        studentDueFee.setDueInstallments(dueInstallmentList);
        return studentDueFee;
    }

    public List<StudentDueFeeHeadWise> getDueStudentsHeadWiseForNotice(Collection<Long> studentIds, Long academicYearId, Date dueDate) {
        List<StudentDueFeeHeadWise> students = new ArrayList();
        for (Long studentId : studentIds) {
            students.add(getStudentDueFeeHeadWise(studentId, academicYearId, dueDate));
        }
        return students;
    }

    private StudentDueFeeHeadWise getStudentDueFeeHeadWise(Long studentId, Long academicYearId, Date dueDate) {
        StudentDueFeeHeadWise studentDueFee = new StudentDueFeeHeadWise();
        Student student = this.studentService.getStudentById(studentId);
        ClassHistory studentActiveClass = student.getActiveClassHistory(academicYearId);
        studentDueFee.setStudentId(student.getStudentId());
        if (student.getFatherName() == null || student.getFatherName().trim().isEmpty()) {
            studentDueFee.setStudentName(student.getFullName());
        } else if ("male".equals(student.getGender())) {
            studentDueFee.setStudentName(student.getFullName() + " S/O " + student.getFatherName());
        } else {
            studentDueFee.setStudentName(student.getFullName() + " D/O " + student.getFatherName());
        }
        studentDueFee.setAcademicYear(studentActiveClass.getAcademicYear().getName());
        studentDueFee.setStudentClass(studentActiveClass.getAcademicYearClass().prepareClassName());
        AcademicYearFee academicYearFee = this.academicYearFeeService.getAcademicYearFee(studentActiveClass.getAcademicYearClass().getAcademicYear().getId(), studentActiveClass.getAcademicYearClass().getCourseYear().getId(), studentActiveClass.getAdmissionType().getId());
        if (academicYearFee == null) {
            return null;
        }
        HeadWiseDueFee headWiseDueFee;
        AcademicYear academicYear = this.academicYearDAO.getAcademicYearById(academicYearId.longValue());
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(academicYear.getInstitute().getId());
        FeeHead lateFeeHead = this.feeHeadService.getLateFeeHead(academicYear.getInstitute().getId());
        BusStop feeBusStop = studentActiveClass.getBusStop();
        BusFee busFee = null;
        BusFeeInstallment busFeeInstallment = null;
        if (feeBusStop != null) {
            busFee = this.academicYearBusFeeService.getBusFee(academicYearId, feeBusStop.getId());
            busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(academicYearId);
        }
        Boolean isCustomized = Boolean.FALSE;
        List<CustomizeInstallment> customizeInstallments = getCustomizeInstallments(studentActiveClass.getStudent().getId(), academicYearFee.getId());
        if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            isCustomized = Boolean.TRUE;
        }
        List<FeeTransactionDetail> feeTransactionDetails = getAllTransactionDetails(studentActiveClass.getStudent().getId(), academicYearFee.getId(), isCustomized);
        Map<Long, HeadWiseDueFee> dueFeeMap = new HashMap();
        if (isCustomized.booleanValue()) {
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                if (!(customizeInstallment.getDueDate() == null || ((!customizeInstallment.getDueDate().before(dueDate) && !customizeInstallment.getDueDate().equals(dueDate)) || customizeInstallment.getCustomizeInstallmentDetails() == null || customizeInstallment.getCustomizeInstallmentDetails().isEmpty()))) {
                    for (CustomizeInstallmentDetail installmentDetail : customizeInstallment.getCustomizeInstallmentDetails()) {
                        if (installmentDetail.getAmount().longValue() != 0) {
                            headWiseDueFee = (HeadWiseDueFee) dueFeeMap.get(installmentDetail.getFeeHead().getId());
                            if (headWiseDueFee == null) {
                                headWiseDueFee = new HeadWiseDueFee();
                                headWiseDueFee.setHeadId(installmentDetail.getFeeHead().getId());
                                headWiseDueFee.setHeadName(installmentDetail.getFeeHead().getName());
                                headWiseDueFee.setTotal(installmentDetail.getAmount());
                                dueFeeMap.put(installmentDetail.getFeeHead().getId(), headWiseDueFee);
                            } else {
                                headWiseDueFee.setTotal(Long.valueOf(headWiseDueFee.getTotal().longValue() + installmentDetail.getAmount().longValue()));
                            }
                        }
                    }
                }
            }
        } else if (!(academicYearFee == null || academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty())) {
            for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFee.getAcademicYearFeeInstallments()) {
                if (academicYearFeeInstallment.getDueDate() != null && (academicYearFeeInstallment.getDueDate().before(dueDate) || academicYearFeeInstallment.getDueDate().equals(dueDate))) {
                    if (!(busFee == null || busFeeInstallment == null || busFeeInstallment.getBusFeeInstallmentDetails() == null || busFeeInstallment.getBusFeeInstallmentDetails().isEmpty())) {
                        for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                            if (busFeeInstallmentDetail.getInstallment().getId().equals(academicYearFeeInstallment.getInstallment().getId())) {
                                long fee = 0;
                                if (!(busFee.getRs() == null || busFeeInstallmentDetail.getFeePercent() == null)) {
                                    fee = (busFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100;
                                }
                                HeadWiseDueFee busDueFee = (HeadWiseDueFee) dueFeeMap.get(busFeeHead.getId());
                                if (busDueFee == null) {
                                    busDueFee = new HeadWiseDueFee();
                                    busDueFee.setHeadId(busFeeHead.getId());
                                    busDueFee.setHeadName(busFeeHead.getName());
                                    busDueFee.setTotal(Long.valueOf(fee));
                                    dueFeeMap.put(busDueFee.getHeadId(), busDueFee);
                                } else {
                                    busDueFee.setTotal(Long.valueOf(busDueFee.getTotal().longValue() + fee));
                                }
                            }
                        }
                    }
                    if (!(academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails() == null || academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails().isEmpty())) {
                        for (AcademicYearFeeInstallmentDetail installmentDetail2 : academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                            if (installmentDetail2.getAmount().longValue() != 0) {
                                headWiseDueFee = (HeadWiseDueFee) dueFeeMap.get(installmentDetail2.getFeeHead().getId());
                                if (headWiseDueFee == null) {
                                    headWiseDueFee = new HeadWiseDueFee();
                                    headWiseDueFee.setHeadId(installmentDetail2.getFeeHead().getId());
                                    headWiseDueFee.setHeadName(installmentDetail2.getFeeHead().getName());
                                    headWiseDueFee.setTotal(installmentDetail2.getAmount());
                                    dueFeeMap.put(installmentDetail2.getFeeHead().getId(), headWiseDueFee);
                                } else {
                                    headWiseDueFee.setTotal(Long.valueOf(headWiseDueFee.getTotal().longValue() + installmentDetail2.getAmount().longValue()));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (dueFeeMap.isEmpty()) {
            return studentDueFee;
        }
        if (!(feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
                if (!(feeTransactionDetail.getFeeHead() == null || lateFeeHead.getId().equals(feeTransactionDetail.getFeeHead().getId()) || feeTransactionDetail.getAmount() == null || feeTransactionDetail.getAmount().longValue() == 0)) {
                    System.out.println("Fee Head id ->" + feeTransactionDetail.getFeeHead().getId() + " Name ->" + feeTransactionDetail.getFeeHead().getName());
                    headWiseDueFee = (HeadWiseDueFee) dueFeeMap.get(feeTransactionDetail.getFeeHead().getId());
                    if (headWiseDueFee != null) {
                        headWiseDueFee.setPaid(Long.valueOf(headWiseDueFee.getPaid().longValue() + feeTransactionDetail.getAmount().longValue()));
                    } else {
                        System.out.println("Not Found ---");
                    }
                }
            }
        }
        List<HeadWiseDueFee> dueFeeList = new ArrayList();
        for (Long key : dueFeeMap.keySet()) {
            if (((HeadWiseDueFee) dueFeeMap.get(key)).getDue().longValue() != 0) {
                dueFeeList.add((HeadWiseDueFee) dueFeeMap.get(key));
            }
        }
        Collections.sort(dueFeeList, new BeanComparator("headName", new NullComparator()));
        studentDueFee.setHeadWiseDueFees(dueFeeList);
        return studentDueFee;
    }
}
