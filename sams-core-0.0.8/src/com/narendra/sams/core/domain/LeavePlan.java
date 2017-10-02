package com.narendra.sams.core.domain;

import java.util.Date;

public class LeavePlan {
    private UserView createdBy;
    private Date createdDateTime;
    private Long id;
    private Institute institute;
    private Date leaveOn;
    private String reason;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLeaveOn() {
        return this.leaveOn;
    }

    public void setLeaveOn(Date leaveOn) {
        this.leaveOn = leaveOn;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
