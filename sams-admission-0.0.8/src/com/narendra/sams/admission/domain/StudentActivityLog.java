package com.narendra.sams.admission.domain;

import java.util.Date;

public class StudentActivityLog {
    public static String ACTIVITY_TYPE_ADMISSION_CONFIRMED = "Admission Confirmed";
    public static String ACTIVITY_TYPE_ADMISSION_RENEWED = "Admission Renewed";
    public static String ACTIVITY_TYPE_ADMISSION_SCHEME_UPDATED = "Admission Scheme Updated";
    public static String ACTIVITY_TYPE_ADMISSION_WITHDREW = "Admission Withdrew";
    public static String ACTIVITY_TYPE_BANK_DETAILS_UPDATED = "Bank Details Updated";
    public static String ACTIVITY_TYPE_BUS_STOP_CHANGED = "Bus Stop Changed";
    public static String ACTIVITY_TYPE_BUS_STOP_CHANGE_REQUESTED = "Bus Stop Change Requested";
    public static String ACTIVITY_TYPE_CLASS_CHANGED = "Class Changed";
    public static String ACTIVITY_TYPE_CLASS_CHANGE_REQUESTED = "Class Change Requested";
    public static String ACTIVITY_TYPE_GAURDIAN_INFORMATION_ADDED = "Gaurdian Information Added";
    public static String ACTIVITY_TYPE_GAURDIAN_INFORMATION_UPDATED = "Gaurdian Information Updated";
    public static String ACTIVITY_TYPE_PARENTS_INFORMATION_UPDATED = "Parents Information Updated";
    public static String ACTIVITY_TYPE_PERSONAL_INFORMATION_UPDATED = "Personal Information Updated";
    public static String ACTIVITY_TYPE_REGESTRED = "Registered";
    public static String ACTIVITY_TYPE_STUDENT_STATUS_UPDATED = "Student Status Updated";
    private Long academicYearId;
    private String activityType;
    private Long ceratedByUserId;
    private String comments;
    private Date createdOn;
    private Long fromId;
    private Long id;
    private Long studentClassId;
    private Long studentId;
    private Long toId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentClassId() {
        return this.studentClassId;
    }

    public void setStudentClassId(Long studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getCeratedByUserId() {
        return this.ceratedByUserId;
    }

    public void setCeratedByUserId(Long ceratedByUserId) {
        this.ceratedByUserId = ceratedByUserId;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getFromId() {
        return this.fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return this.toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
