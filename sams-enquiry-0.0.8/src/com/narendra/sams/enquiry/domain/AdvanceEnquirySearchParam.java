package com.narendra.sams.enquiry.domain;

import java.util.Date;

public class AdvanceEnquirySearchParam extends Enquiry {
    private Long academicYearClassId;
    private Date activityFromDate;
    private String activityFromDateStr;
    private Date activityToDate;
    private String activityToDateStr;
    private String activityType;
    private Long affiliationAuthoritId;
    private Long courseId;
    private String registered;
    private String studentFullName;

    public String getStudentFullName() {
        return this.studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getRegistered() {
        return this.registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityFromDateStr() {
        return this.activityFromDateStr;
    }

    public void setActivityFromDateStr(String activityFromDateStr) {
        this.activityFromDateStr = activityFromDateStr;
    }

    public String getActivityToDateStr() {
        return this.activityToDateStr;
    }

    public void setActivityToDateStr(String activityToDateStr) {
        this.activityToDateStr = activityToDateStr;
    }

    public Date getActivityFromDate() {
        return this.activityFromDate;
    }

    public void setActivityFromDate(Date activityFromDate) {
        this.activityFromDate = activityFromDate;
    }

    public Date getActivityToDate() {
        return this.activityToDate;
    }

    public void setActivityToDate(Date activityToDate) {
        this.activityToDate = activityToDate;
    }

    public Long getAffiliationAuthoritId() {
        return this.affiliationAuthoritId;
    }

    public void setAffiliationAuthoritId(Long affiliationAuthoritId) {
        this.affiliationAuthoritId = affiliationAuthoritId;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }
}
