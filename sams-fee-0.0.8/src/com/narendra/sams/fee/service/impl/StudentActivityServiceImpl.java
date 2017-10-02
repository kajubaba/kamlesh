package com.narendra.sams.fee.service.impl;

import com.narendra.sams.admission.dao.StudentActivityDAO;
import com.narendra.sams.admission.domain.ChangeRequest;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentActivity;
import com.narendra.sams.admission.exception.ClassCanNotChangeException;
import com.narendra.sams.admission.service.StudentActivityLogService;
import com.narendra.sams.admission.service.StudentIdGeneratorService;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.dao.AcademicYearBusFeeDAO;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.exception.OperationCanNotSucceedException;
import com.narendra.sams.core.exception.OperationCanSucceedException;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.service.AcademicYearSettingService;
import com.narendra.sams.core.service.BusStopService;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.dao.StudentFeeDAO;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.fee.service.StudentActivityService;
import com.narendra.sams.fee.service.StudentFeeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentActivityServiceImpl implements StudentActivityService {
    private AcademicYearBusFeeDAO academicYearBusFeeDAO;
    private AcademicYearBusFeeService academicYearBusFeeService;
    private AcademicYearFeeService academicYearFeeService;
    private AcademicYearSettingService academicYearSettingService;
    private BusStopService busStopService;
    private CustomizeStudentFeeService customizeStudentFeeService;
    private FeeHeadService feeHeadService;
    private StudentActivityDAO studentActivityDAO;
    private StudentActivityLogService studentActivityLogService;
    private StudentFeeDAO studentFeeDAO;
    private StudentFeeService studentFeeService;
    private StudentIdGeneratorService studentIdGeneratorService;
    private StudentService studentService;

    public FeeHeadService getFeeHeadService() {
        return this.feeHeadService;
    }

    public void setFeeHeadService(FeeHeadService feeHeadService) {
        this.feeHeadService = feeHeadService;
    }

    public StudentIdGeneratorService getStudentIdGeneratorService() {
        return this.studentIdGeneratorService;
    }

    public void setStudentIdGeneratorService(StudentIdGeneratorService studentIdGeneratorService) {
        this.studentIdGeneratorService = studentIdGeneratorService;
    }

    public StudentActivityDAO getStudentActivityDAO() {
        return this.studentActivityDAO;
    }

    public void setStudentActivityDAO(StudentActivityDAO studentActivityDAO) {
        this.studentActivityDAO = studentActivityDAO;
    }

    public StudentService getStudentService() {
        return this.studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public BusStopService getBusStopService() {
        return this.busStopService;
    }

    public void setBusStopService(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    public AcademicYearFeeService getAcademicYearFeeService() {
        return this.academicYearFeeService;
    }

    public void setAcademicYearFeeService(AcademicYearFeeService academicYearFeeService) {
        this.academicYearFeeService = academicYearFeeService;
    }

    public StudentFeeService getStudentFeeService() {
        return this.studentFeeService;
    }

    public void setStudentFeeService(StudentFeeService studentFeeService) {
        this.studentFeeService = studentFeeService;
    }

    public AcademicYearBusFeeService getAcademicYearBusFeeService() {
        return this.academicYearBusFeeService;
    }

    public void setAcademicYearBusFeeService(AcademicYearBusFeeService academicYearBusFeeService) {
        this.academicYearBusFeeService = academicYearBusFeeService;
    }

    public StudentFeeDAO getStudentFeeDAO() {
        return this.studentFeeDAO;
    }

    public void setStudentFeeDAO(StudentFeeDAO studentFeeDAO) {
        this.studentFeeDAO = studentFeeDAO;
    }

    public AcademicYearBusFeeDAO getAcademicYearBusFeeDAO() {
        return this.academicYearBusFeeDAO;
    }

    public void setAcademicYearBusFeeDAO(AcademicYearBusFeeDAO academicYearBusFeeDAO) {
        this.academicYearBusFeeDAO = academicYearBusFeeDAO;
    }

    public AcademicYearSettingService getAcademicYearSettingService() {
        return this.academicYearSettingService;
    }

    public void setAcademicYearSettingService(AcademicYearSettingService academicYearSettingService) {
        this.academicYearSettingService = academicYearSettingService;
    }

    public StudentActivityLogService getStudentActivityLogService() {
        return this.studentActivityLogService;
    }

    public void setStudentActivityLogService(StudentActivityLogService studentActivityLogService) {
        this.studentActivityLogService = studentActivityLogService;
    }

    public CustomizeStudentFeeService getCustomizeStudentFeeService() {
        return this.customizeStudentFeeService;
    }

    public void setCustomizeStudentFeeService(CustomizeStudentFeeService customizeStudentFeeService) {
        this.customizeStudentFeeService = customizeStudentFeeService;
    }

    public void addStudentActivity(Long studentId, Long addmissionAction, String log, String comments, Long userId) {
        Student student = this.studentService.getStudentById(studentId);
        StudentActivity studentActivity = new StudentActivity();
        studentActivity.setStudent(student);
        studentActivity.setAcademicYear(student.getAcademicYearClass().getAcademicYear());
        studentActivity.setAcademicYearClass(student.getAcademicYearClass());
        studentActivity.setLog(log);
        studentActivity.setComments(comments);
        studentActivity.setCreatedDatetime(DateUtil.getSystemDateTime());
        studentActivity.setAddmissionAction(this.studentActivityDAO.loadAddmissionAction(addmissionAction));
        this.studentActivityDAO.addStudentActivity(studentActivity, userId);
    }

    public void updateStudentBusStop(Long studentId, Long newBusStopId, Long userId, String comments, Boolean forceRemoveAdjustments) throws Exception {
        Student student = this.studentService.getStudentById(studentId);
        Long oldBusStopId = null;
        if (student.getBusStop() != null) {
            oldBusStopId = student.getBusStop().getId();
        }
        String exceptionMessage;
        if (!StudentStatus.CONFIRMED.equals(student.getStudentStatus().getId()) && !StudentStatus.TEMPORARY.equals(student.getStudentStatus().getId()) && !StudentStatus.TEMPORARY_RENEWAL.equals(student.getStudentStatus().getId())) {
            exceptionMessage = "Bus stop can not be change because student status is other than Registered, Temporary Renewed and Confirmed. First change appropriate status and then try to change new bus stop";
            createChangeBusStopRequest(student, newBusStopId, userId, exceptionMessage);
            this.studentActivityLogService.addBusStopChangeRequestActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, oldBusStopId, newBusStopId);
            throw new OperationCanNotSucceedException(exceptionMessage);
        } else if (isNewAndOldBusStopFeeEquals(student, newBusStopId).booleanValue()) {
            changeBusStop(student, newBusStopId, userId, comments);
        } else if (isCurrentClassFeeAdjusted(student).booleanValue()) {
            String systemComments;
            if (isCurrentClassFeePaid(student).booleanValue()) {
                systemComments = "Bus Stop can not be changed because student fee is adjusted and student paid full or partial bus fee as well. Please contact system administrator.";
                createChangeBusStopRequest(student, newBusStopId, userId, systemComments);
                this.studentActivityLogService.addBusStopChangeRequestActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, oldBusStopId, newBusStopId);
                throw new OperationCanNotSucceedException(systemComments);
            } else if (forceRemoveAdjustments.booleanValue()) {
                Long l = studentId;
                this.customizeStudentFeeService.deleteFeeAdjustment(l, this.academicYearFeeService.getAcademicYearFee(student.getAcademicYearClass().getAcademicYear().getId(), student.getAcademicYearClass().getCourseYear().getId(), student.getAdmissionType().getId()).getId());
                changeBusStop(student, newBusStopId, userId, comments);
            } else {
                systemComments = "Student bus stop is not changed because academic session fee is adjusted. Student did not pay any bus fee, it is safe to change bus stop after removing fee adjustments.";
                createChangeBusStopRequest(student, newBusStopId, userId, systemComments);
                this.studentActivityLogService.addBusStopChangeRequestActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, oldBusStopId, newBusStopId);
                throw new OperationCanSucceedException(systemComments);
            }
        } else if (!isCurrentBusStopFeePaid(student).booleanValue()) {
            changeBusStop(student, newBusStopId, userId, comments);
        } else if (isPaidBusFeeIsCompatibleToChangeBusStop(student, newBusStopId).booleanValue()) {
            changeBusStop(student, newBusStopId, userId, comments);
        } else {
            exceptionMessage = "Bus stop can not be changed because student already paid full or partial bus fee and paid fee is greater than new bus stop fee. Please contact to system administrator.";
            createChangeBusStopRequest(student, newBusStopId, userId, exceptionMessage);
            this.studentActivityLogService.addBusStopChangeRequestActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, null, student.getBusStop().getId(), newBusStopId);
            throw new OperationCanNotSucceedException(exceptionMessage);
        }
    }

    private void createChangeBusStopRequest(Student student, Long newBusStopId, Long userId, String changeRequestSystemComments) {
        ChangeRequest changeRequest = new ChangeRequest();
        changeRequest.setRequestStatus(ChangeRequest.STATUS_REQUESTED);
        changeRequest.setRequestType(ChangeRequest.TYPE_BUS_STOP_CHANGE);
        changeRequest.setRequestedByUserId(userId);
        changeRequest.setRequestedOnDateTime(DateUtil.getSystemDateTime());
        changeRequest.setStudentId(student.getId());
        changeRequest.setAcademicYearId(student.getAcademicYear().getId());
        Long oldBusStopId = null;
        if (student.getBusStop() != null) {
            oldBusStopId = student.getBusStop().getId();
        }
        changeRequest.setFromId(oldBusStopId);
        changeRequest.setToId(newBusStopId);
        changeRequest.setRequestorComments(changeRequestSystemComments);
        this.studentActivityDAO.addChangeRequest(changeRequest);
    }

    private void createChangeClassRequest(Student student, Long newClassId, Long userId, String changeRequestSystemComments) {
        ChangeRequest changeRequest = new ChangeRequest();
        changeRequest.setRequestStatus(ChangeRequest.STATUS_REQUESTED);
        changeRequest.setRequestType(ChangeRequest.TYPE_CLASS_CHANGE);
        changeRequest.setRequestedByUserId(userId);
        changeRequest.setRequestedOnDateTime(DateUtil.getSystemDateTime());
        changeRequest.setStudentId(student.getId());
        changeRequest.setAcademicYearId(student.getAcademicYear().getId());
        changeRequest.setFromId(student.getAcademicYearClass().getId());
        changeRequest.setToId(newClassId);
        changeRequest.setRequestorComments(changeRequestSystemComments);
        this.studentActivityDAO.addChangeRequest(changeRequest);
    }

    public void updateStudentClass(Long studentId, Long newClassId, Long userId, String comments, Boolean forceRemoveFeeAdjustments) throws Exception {
        Student student = this.studentService.getStudentById(studentId);
        if (StudentStatus.CONFIRMED.equals(student.getStudentStatus().getId()) || StudentStatus.TEMPORARY.equals(student.getStudentStatus().getId()) || StudentStatus.TEMPORARY_RENEWAL.equals(student.getStudentStatus().getId())) {
            Boolean isClassFeePaid = isCurrentClassFeePaid(student);
            if (isCurrentClassFeeAdjusted(student).booleanValue()) {
                String systemComments;
                if (isClassFeePaid.booleanValue()) {
                    systemComments = "Student academic session class can not be changed because student fee is adjusted and student paid full or partial fee as well. Please contact system administrator.";
                    createChangeClassRequest(student, newClassId, userId, systemComments);
                    this.studentActivityLogService.addClassChangeRequestActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, student.getAcademicYearClass().getId(), newClassId);
                    throw new OperationCanNotSucceedException(systemComments);
                } else if (forceRemoveFeeAdjustments.booleanValue()) {
                    Long l = studentId;
                    this.customizeStudentFeeService.deleteFeeAdjustment(l, this.academicYearFeeService.getAcademicYearFee(student.getAcademicYearClass().getAcademicYear().getId(), student.getAcademicYearClass().getCourseYear().getId(), student.getAdmissionType().getId()).getId());
                    changeClass(student, newClassId, userId, comments);
                    return;
                } else {
                    systemComments = "Student fee is adjusted but student did not pay any fee. It is safe to change academic session class after removing fee adjustments.";
                    createChangeClassRequest(student, newClassId, userId, systemComments);
                    this.studentActivityLogService.addClassChangeRequestActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, student.getAcademicYearClass().getId(), newClassId);
                    throw new OperationCanSucceedException(systemComments);
                }
            } else if (!isClassFeePaid.booleanValue()) {
                changeClass(student, newClassId, userId, comments);
                return;
            } else if (isPaidClassFeeCompatibleToChangeClass(student, newClassId).booleanValue()) {
                changeClass(student, newClassId, userId, comments);
                updateAcademicYearFeeIdInFeeTransaction(student);
                return;
            } else {
                String exceptionMessage = "Class can not be changed because student paid full or partial fee and paid fee is greater than student new class fee. Please contact system administrator";
                createChangeClassRequest(student, newClassId, userId, exceptionMessage);
                this.studentActivityLogService.addClassChangeRequestActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, student.getAcademicYearClass().getId(), newClassId);
                throw new OperationCanNotSucceedException(exceptionMessage);
            }
        }
        throw new ClassCanNotChangeException("Academic class can not be change because student status is other than Registered, Temporary Renewed and Confirmed. First change appropriate status and then try to change new academic class");
    }

    public void updateStudentAdmissionScheme(Long studentId, Long newAdmissionSchemId, Long userId, String comments) {
        Student student = this.studentService.getStudentById(studentId);
        Long oldAdmissionSchemeId = null;
        if (student.getAdmissionScheme() != null) {
            oldAdmissionSchemeId = student.getAdmissionScheme().getId();
        }
        this.studentActivityDAO.updateStudentAdmissionScheme(studentId, newAdmissionSchemId);
        this.studentActivityLogService.addAdmissionSchemeChangedActivity(studentId, student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, oldAdmissionSchemeId, newAdmissionSchemId);
    }

    private void changeClass(Student student, Long newClassId, Long userId, String userComments) {
        Long oldClassId = student.getAcademicYearClass().getId();
        AcademicYearClass academicYearClass = this.academicYearSettingService.loadAcademicYearClass(newClassId);
        student.setAcademicYearClass(academicYearClass);
        if (student.getClassHistories() != null) {
            for (ClassHistory classHistory : student.getClassHistories()) {
                if (classHistory.getStudent().getId().equals(student.getId()) && classHistory.getAcademicYear().getId().equals(student.getAcademicYearClass().getAcademicYear().getId()) && Boolean.TRUE.equals(classHistory.getActiveClass())) {
                    classHistory.setAcademicYearClass(academicYearClass);
                    break;
                }
            }
        }
        this.studentActivityLogService.addClassChangedActivity(student.getId(), oldClassId, student.getAcademicYear().getId(), userId, userComments, oldClassId, newClassId);
    }

    private void updateAcademicYearFeeIdInFeeTransaction(Student student) {
        List<FeeTransaction> feeTransactions = this.studentFeeService.getStudentFeeTransactions(student.getId(), student.getAcademicYear().getId());
        if (feeTransactions != null && !feeTransactions.isEmpty()) {
            for (AcademicYearFeeInstallment academicYearFeeInstallment : this.academicYearFeeService.getAcademicYearFee(student.getAcademicYearClass().getAcademicYear().getId(), student.getAcademicYearClass().getCourseYear().getId(), student.getAdmissionType().getId()).getAcademicYearFeeInstallments()) {
                for (FeeTransaction feeTransaction : feeTransactions) {
                    for (FeeTransactionDetail feeTransactionDetail : feeTransaction.getFeeTransactionDetails()) {
                        if (academicYearFeeInstallment.getInstallment().getId().equals(feeTransactionDetail.getInstallment().getId())) {
                            feeTransaction.setAcademicYearFeeInstallment(academicYearFeeInstallment);
                            feeTransaction.setStudentClass(student.getAcademicYearClass());
                            break;
                        }
                    }
                }
            }
        }
    }

    private void changeBusStop(Student student, Long newBusStopId, Long userId, String userComments) {
        Long oldBusStopId = null;
        if (student.getBusStop() != null) {
            oldBusStopId = student.getBusStop().getId();
        }
        BusStop newbuBusStop = this.busStopService.getBusStop(newBusStopId);
        student.setBusStop(newbuBusStop);
        if (student.getClassHistories() != null) {
            for (ClassHistory classHistory : student.getClassHistories()) {
                if (classHistory.getStudent().getId().equals(student.getId()) && classHistory.getAcademicYear().getId().equals(student.getAcademicYearClass().getAcademicYear().getId()) && Boolean.TRUE.equals(classHistory.getActiveClass())) {
                    classHistory.setBusStop(newbuBusStop);
                    break;
                }
            }
        }
        this.studentActivityLogService.addBusStopChangedActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, userComments, oldBusStopId, newBusStopId);
    }

    private Boolean isPaidBusFeeIsCompatibleToChangeBusStop(Student student, Long newBusStopId) {
        if (newBusStopId == null) {
            return Boolean.FALSE;
        }
        BusFee newBusStopFee = this.academicYearBusFeeDAO.getBusFee(student.getAcademicYear().getId(), newBusStopId);
        if (newBusStopFee == null) {
            return Boolean.FALSE;
        }
        List<FeeTransactionDetail> paidFees = this.studentFeeDAO.getPaidFee(student.getId(), student.getAcademicYear().getId());
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeDAO.getBusFeeInstallment(student.getAcademicYear().getId());
        Map<Long, Long> newBusFeeInstallmentWise = new HashMap();
        if (!(busFeeInstallment == null || busFeeInstallment.getBusFeeInstallmentDetails() == null || busFeeInstallment.getBusFeeInstallmentDetails().isEmpty())) {
            for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                long installmentFee = (newBusStopFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100;
                newBusFeeInstallmentWise.put(busFeeInstallmentDetail.getInstallment().getId(), Long.valueOf(installmentFee));
            }
        }
        Map<Long, Long> paidBusFeeInstallmentWise = new HashMap();
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(student.getInstitute().getId());
        if (!(paidFees == null || paidFees.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : paidFees) {
                if (busFeeHead.getId().equals(feeTransactionDetail.getFeeHead().getId())) {
                    Long busFeePaidInInstallment = (Long) paidBusFeeInstallmentWise.get(feeTransactionDetail.getInstallment().getId());
                    if (busFeePaidInInstallment != null) {
                        paidBusFeeInstallmentWise.put(feeTransactionDetail.getInstallment().getId(), Long.valueOf(busFeePaidInInstallment.longValue() + feeTransactionDetail.getAmount().longValue()));
                    } else {
                        paidBusFeeInstallmentWise.put(feeTransactionDetail.getInstallment().getId(), feeTransactionDetail.getAmount());
                    }
                }
            }
        }
        Boolean isCompatible = Boolean.TRUE;
        for (Long installmentId : paidBusFeeInstallmentWise.keySet()) {
            Long paidFee = (Long) paidBusFeeInstallmentWise.get(installmentId);
            Long installmentFee2 = (Long) newBusFeeInstallmentWise.get(installmentId);
            if (paidFee == null) {
                paidFee = Long.valueOf(0);
            }
            if (installmentFee2 == null) {
                installmentFee2 = Long.valueOf(0);
            }
            if (paidFee.longValue() > installmentFee2.longValue()) {
                isCompatible = Boolean.FALSE;
            }
        }
        return isCompatible;
    }

    private Boolean isPaidClassFeeCompatibleToChangeClass(Student student, Long newClassId) {
        List<FeeTransactionDetail> paidFees = this.studentFeeDAO.getPaidFee(student.getId(), student.getAcademicYear().getId());
        if (paidFees == null || paidFees.isEmpty()) {
            return Boolean.TRUE;
        }
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(student.getInstitute().getId());
        Map<Long, Map<Long, Long>> newClassFeeInstallmentWise = new HashMap();
        AcademicYearClass newAcademicYearClass = this.academicYearSettingService.loadAcademicYearClass(newClassId);
        AcademicYearFee newClassFee = this.academicYearFeeService.getAcademicYearFee(newAcademicYearClass.getAcademicYear().getId(), newAcademicYearClass.getCourseYear().getId(), student.getAdmissionType().getId());
        if (!(newClassFee == null || newClassFee.getAcademicYearFeeInstallments() == null || newClassFee.getAcademicYearFeeInstallments().isEmpty())) {
            for (AcademicYearFeeInstallment feeInstallment : newClassFee.getAcademicYearFeeInstallments()) {
                Map<Long, Long> headFeeMap = new HashMap();
                for (AcademicYearFeeInstallmentDetail installmentDetail : feeInstallment.getAcademicYearFeeInstallmentDetails()) {
                    headFeeMap.put(installmentDetail.getFeeHead().getId(), installmentDetail.getAmount());
                }
                newClassFeeInstallmentWise.put(feeInstallment.getInstallment().getId(), headFeeMap);
            }
        }
        Map<Long, Map<Long, Long>> paidFeeInstallmentWise = new HashMap();
        for (FeeTransactionDetail paidFee : paidFees) {
            if (paidFee.getFeeHead().getId() != busFeeHead.getId()) {
                Map<Long, Long> paidFeeHeadWise = (Map) paidFeeInstallmentWise.get(paidFee.getInstallment().getId());
                if (paidFeeHeadWise == null) {
                    paidFeeHeadWise = new HashMap();
                    paidFeeInstallmentWise.put(paidFee.getInstallment().getId(), paidFeeHeadWise);
                }
                Long paidInHead = (Long) paidFeeHeadWise.get(paidFee.getFeeHead().getId());
                if (paidInHead == null) {
                    paidFeeHeadWise.put(paidFee.getFeeHead().getId(), paidFee.getAmount());
                } else {
                    paidFeeHeadWise.put(paidFee.getFeeHead().getId(), Long.valueOf(paidInHead.longValue() + paidFee.getAmount().longValue()));
                }
            }
        }
        Boolean isCompatible = Boolean.TRUE;
        for (Long installmentId : paidFeeInstallmentWise.keySet()) {
            if (((Map) newClassFeeInstallmentWise.get(installmentId)) == null) {
                return Boolean.FALSE;
            }
            for (Object headId : ((Map) paidFeeInstallmentWise.get(installmentId)).keySet()) {
                Long headFee = (Long) ((Map) newClassFeeInstallmentWise.get(installmentId)).get(headId);
                Long paidHeadFee = (Long) ((Map) paidFeeInstallmentWise.get(installmentId)).get(headId);
                if (headFee == null) {
                    headFee = Long.valueOf(0);
                }
                if (paidHeadFee == null) {
                    paidHeadFee = Long.valueOf(0);
                }
                if (paidHeadFee.longValue() > headFee.longValue()) {
                    isCompatible = Boolean.FALSE;
                    break;
                }
            }
            if (!isCompatible.booleanValue()) {
                return isCompatible;
            }
        }
        return isCompatible;
    }

    private Boolean isCurrentBusStopFeePaid(Student student) {
        if (student.getBusStop() == null) {
            return Boolean.FALSE;
        }
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(student.getInstitute().getId());
        List<FeeTransactionDetail> paidFees = this.studentFeeDAO.getPaidFee(student.getId(), student.getAcademicYear().getId());
        if (!(paidFees == null || paidFees.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : paidFees) {
                if (busFeeHead.getId().equals(feeTransactionDetail.getFeeHead().getId()) && feeTransactionDetail.getAmount() != null && feeTransactionDetail.getAmount().longValue() > 0) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    private Boolean isCurrentClassFeePaid(Student student) {
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(student.getInstitute().getId());
        List<FeeTransactionDetail> paidFees = this.studentFeeDAO.getPaidFee(student.getId(), student.getAcademicYear().getId());
        if (!(paidFees == null || paidFees.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : paidFees) {
                if (!busFeeHead.getId().equals(feeTransactionDetail.getFeeHead().getId()) && feeTransactionDetail.getAmount() != null && feeTransactionDetail.getAmount().longValue() > 0) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    private Boolean isCurrentClassFeeAdjusted(Student student) {
        AcademicYearFee academicYearFee = this.academicYearFeeService.getAcademicYearFee(student.getAcademicYearClass().getAcademicYear().getId(), student.getAcademicYearClass().getCourseYear().getId(), student.getAdmissionType().getId());
        if (academicYearFee == null) {
            return Boolean.FALSE;
        }
        List<CustomizeInstallment> customizeInstallments = this.studentFeeService.getCustomizeInstallments(student.getId(), academicYearFee.getId());
        if (customizeInstallments == null || customizeInstallments.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean isNewAndOldBusStopFeeEquals(Student student, Long newBusStopId) {
        if (student.getBusStop() == null && newBusStopId == null) {
            return Boolean.TRUE;
        }
        BusFee newBusStopFee = this.academicYearBusFeeService.getBusFee(student.getAcademicYearClass().getAcademicYear().getId(), newBusStopId);
        BusFee currentBusStopFee = null;
        if (student.getBusStop() != null) {
            currentBusStopFee = this.academicYearBusFeeService.getBusFee(student.getAcademicYearClass().getAcademicYear().getId(), student.getBusStop().getId());
        }
        Long newBusFee = Long.valueOf(0);
        Long oldBusFee = Long.valueOf(0);
        if (!(newBusStopFee == null || newBusStopFee.getRs() == null)) {
            newBusFee = newBusStopFee.getRs();
        }
        if (!(currentBusStopFee == null || currentBusStopFee.getRs() == null)) {
            oldBusFee = currentBusStopFee.getRs();
        }
        if (newBusFee.equals(oldBusFee)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void updateStudentStatus(Long studentId, Long newStudentStatusId, Long userId, String comments) throws OperationCanNotSucceedException {
        if (newStudentStatusId == null || studentId == null || userId == null) {
            throw new OperationCanNotSucceedException();
        }
        Student student = this.studentService.getStudentById(studentId);
        Long oldStudentStatusId = student.getStudentStatus().getId();
        this.studentActivityDAO.updateStudentStatus(studentId, newStudentStatusId);
        this.studentActivityLogService.addStudentStatusChangedActivity(studentId, student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, comments, oldStudentStatusId, newStudentStatusId);
    }
}
