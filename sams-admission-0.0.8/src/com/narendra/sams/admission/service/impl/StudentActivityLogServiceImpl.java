package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.StudentActivityLogDAO;
import com.narendra.sams.admission.domain.StudentActivityLog;
import com.narendra.sams.admission.service.StudentActivityLogService;
import com.narendra.sams.core.util.DateUtil;

public class StudentActivityLogServiceImpl implements StudentActivityLogService {
    private StudentActivityLogDAO studentActivityLogDAO;

    public StudentActivityLogDAO getStudentActivityLogDAO() {
        return this.studentActivityLogDAO;
    }

    public void setStudentActivityLogDAO(StudentActivityLogDAO studentActivityLogDAO) {
        this.studentActivityLogDAO = studentActivityLogDAO;
    }

    public void addRegistrationActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_REGESTRED);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addAdmissionRenewActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long previousClassId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_ADMISSION_RENEWED);
        studentActivityLog.setFromId(previousClassId);
        studentActivityLog.setToId(studentCurrentClassId);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addAdmissionConfirmedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_ADMISSION_CONFIRMED);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addGaurdianAddedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_GAURDIAN_INFORMATION_ADDED);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addGaurdianUpdatedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_GAURDIAN_INFORMATION_UPDATED);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addParentsInformationUpdatedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_PARENTS_INFORMATION_UPDATED);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addPersonalInformationUpdatedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_PERSONAL_INFORMATION_UPDATED);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addBusStopChangedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments, Long fromId, Long toId) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_BUS_STOP_CHANGED);
        studentActivityLog.setFromId(fromId);
        studentActivityLog.setToId(toId);
        studentActivityLog.setComments(comments);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addBusStopChangeRequestActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments, Long fromId, Long toId) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_BUS_STOP_CHANGE_REQUESTED);
        studentActivityLog.setFromId(fromId);
        studentActivityLog.setToId(toId);
        studentActivityLog.setComments(comments);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addClassChangedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments, Long fromId, Long toId) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_CLASS_CHANGED);
        studentActivityLog.setFromId(fromId);
        studentActivityLog.setToId(toId);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addClassChangeRequestActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments, Long fromId, Long toId) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_CLASS_CHANGE_REQUESTED);
        studentActivityLog.setFromId(fromId);
        studentActivityLog.setToId(toId);
        studentActivityLog.setComments(comments);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addStudentStatusChangedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments, Long fromId, Long toId) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_STUDENT_STATUS_UPDATED);
        studentActivityLog.setFromId(fromId);
        studentActivityLog.setToId(toId);
        studentActivityLog.setComments(comments);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addAdmissionSchemeChangedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments, Long fromId, Long toId) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_ADMISSION_SCHEME_UPDATED);
        studentActivityLog.setFromId(fromId);
        studentActivityLog.setToId(toId);
        studentActivityLog.setComments(comments);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    public void addBanksDetailsChangedActivity(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = prepareLog(studentId, studentCurrentClassId, currentAcademicYearId, userId, comments);
        studentActivityLog.setActivityType(StudentActivityLog.ACTIVITY_TYPE_BANK_DETAILS_UPDATED);
        this.studentActivityLogDAO.addActivityLog(studentActivityLog);
    }

    private StudentActivityLog prepareLog(Long studentId, Long studentCurrentClassId, Long currentAcademicYearId, Long userId, String comments) {
        StudentActivityLog studentActivityLog = new StudentActivityLog();
        studentActivityLog.setStudentId(studentId);
        studentActivityLog.setStudentClassId(studentCurrentClassId);
        studentActivityLog.setAcademicYearId(currentAcademicYearId);
        studentActivityLog.setCeratedByUserId(userId);
        studentActivityLog.setCreatedOn(DateUtil.getSystemDateTime());
        studentActivityLog.setComments(comments);
        return studentActivityLog;
    }
}
