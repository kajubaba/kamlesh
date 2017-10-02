package com.narendra.sams.web.restws.enquiry.vo;

public class EnquiryDetailVO extends EnquiryFormVO {
    private String assignedTo;
    private Long assignedToId;
    private String enquiryAcademicSession;
    private String enquiryForClass;
    private String formNo;
    private Long ownerId;
    private String ownerName;
    private Long statusId;
    private String statusName;

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getAssignedToId() {
        return this.assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public Long getStatusId() {
        return this.statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getEnquiryAcademicSession() {
        return this.enquiryAcademicSession;
    }

    public void setEnquiryAcademicSession(String enquiryAcademicSession) {
        this.enquiryAcademicSession = enquiryAcademicSession;
    }

    public String getEnquiryForClass() {
        return this.enquiryForClass;
    }

    public void setEnquiryForClass(String enquiryForClass) {
        this.enquiryForClass = enquiryForClass;
    }

    public String getFormNo() {
        return this.formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }
}
