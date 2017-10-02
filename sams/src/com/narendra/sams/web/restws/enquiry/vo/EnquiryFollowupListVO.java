package com.narendra.sams.web.restws.enquiry.vo;

public class EnquiryFollowupListVO {
    private String amOrPm;
    private Long enquiryId;
    private String fatherContactNo;
    private Long followupId;
    private String motherContactNo;
    private String nextFollowupAction;
    private Long nextFollowupHr;
    private Long nextFollowupMin;
    private String nextFollowupOn;
    private String studentClass;
    private String studentContactNo;
    private String studentName;

    public Long getFollowupId() {
        return this.followupId;
    }

    public void setFollowupId(Long followupId) {
        this.followupId = followupId;
    }

    public Long getEnquiryId() {
        return this.enquiryId;
    }

    public void setEnquiryId(Long enquiryId) {
        this.enquiryId = enquiryId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentContactNo() {
        return this.studentContactNo;
    }

    public void setStudentContactNo(String studentContactNo) {
        this.studentContactNo = studentContactNo;
    }

    public String getFatherContactNo() {
        return this.fatherContactNo;
    }

    public void setFatherContactNo(String fatherContactNo) {
        this.fatherContactNo = fatherContactNo;
    }

    public String getMotherContactNo() {
        return this.motherContactNo;
    }

    public void setMotherContactNo(String motherContactNo) {
        this.motherContactNo = motherContactNo;
    }

    public String getNextFollowupAction() {
        return this.nextFollowupAction;
    }

    public void setNextFollowupAction(String nextFollowupAction) {
        this.nextFollowupAction = nextFollowupAction;
    }

    public String getNextFollowupOn() {
        return this.nextFollowupOn;
    }

    public void setNextFollowupOn(String nextFollowupOn) {
        this.nextFollowupOn = nextFollowupOn;
    }

    public Long getNextFollowupHr() {
        return this.nextFollowupHr;
    }

    public void setNextFollowupHr(Long nextFollowupHr) {
        this.nextFollowupHr = nextFollowupHr;
    }

    public Long getNextFollowupMin() {
        return this.nextFollowupMin;
    }

    public void setNextFollowupMin(Long nextFollowupMin) {
        this.nextFollowupMin = nextFollowupMin;
    }

    public String getAmOrPm() {
        return this.amOrPm;
    }

    public void setAmOrPm(String amOrPm) {
        this.amOrPm = amOrPm;
    }
}
