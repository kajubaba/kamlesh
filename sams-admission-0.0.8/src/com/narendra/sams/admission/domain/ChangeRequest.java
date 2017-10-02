package com.narendra.sams.admission.domain;

import java.util.Date;

public class ChangeRequest {
    public static String STATUS_CLOSED = "Closed";
    public static String STATUS_DECLINED = "Declined";
    public static String STATUS_IN_PROGRESS = "In-Progress";
    public static String STATUS_REQUESTED = "Requested";
    public static String TYPE_BUS_STOP_CHANGE = "Bus Stop Change";
    public static String TYPE_CLASS_CHANGE = "Class Change";
    private Long academicYearId;
    private Long fromId;
    private Long id;
    private String requestStatus;
    private String requestType;
    private Long requestedByUserId;
    private Date requestedOnDateTime;
    private String requestorComments;
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

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
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

    public Long getRequestedByUserId() {
        return this.requestedByUserId;
    }

    public void setRequestedByUserId(Long requestedByUserId) {
        this.requestedByUserId = requestedByUserId;
    }

    public Date getRequestedOnDateTime() {
        return this.requestedOnDateTime;
    }

    public void setRequestedOnDateTime(Date requestedOnDateTime) {
        this.requestedOnDateTime = requestedOnDateTime;
    }

    public String getRequestorComments() {
        return this.requestorComments;
    }

    public void setRequestorComments(String requestorComments) {
        this.requestorComments = requestorComments;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
