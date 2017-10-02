package com.narendra.sams.enquiry.domain;

import com.narendra.sams.core.domain.UserView;
import java.io.Serializable;
import java.util.Date;

public class EnquiryActivity implements Serializable {
    private static final long serialVersionUID = 7289852987326636765L;
    private String activityType;
    private UserView assigneeFrom;
    private UserView assigneeTo;
    private String comments;
    private UserView createdBy;
    private Date createdDateTime;
    private Enquiry enquiry;
    private Long enquiryActivityId;
    private String followupActivity;
    private UserView ownerFrom;
    private UserView ownerTo;
    private EnquiryStatus statusFrom;
    private EnquiryStatus statusTo;

    public Long getEnquiryActivityId() {
        return this.enquiryActivityId;
    }

    public void setEnquiryActivityId(Long enquiryActivityId) {
        this.enquiryActivityId = enquiryActivityId;
    }

    public Date getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public UserView getOwnerFrom() {
        return this.ownerFrom;
    }

    public void setOwnerFrom(UserView ownerFrom) {
        this.ownerFrom = ownerFrom;
    }

    public UserView getOwnerTo() {
        return this.ownerTo;
    }

    public void setOwnerTo(UserView ownerTo) {
        this.ownerTo = ownerTo;
    }

    public UserView getAssigneeFrom() {
        return this.assigneeFrom;
    }

    public void setAssigneeFrom(UserView assigneeFrom) {
        this.assigneeFrom = assigneeFrom;
    }

    public UserView getAssigneeTo() {
        return this.assigneeTo;
    }

    public void setAssigneeTo(UserView assigneeTo) {
        this.assigneeTo = assigneeTo;
    }

    public EnquiryStatus getStatusFrom() {
        return this.statusFrom;
    }

    public void setStatusFrom(EnquiryStatus statusFrom) {
        this.statusFrom = statusFrom;
    }

    public EnquiryStatus getStatusTo() {
        return this.statusTo;
    }

    public void setStatusTo(EnquiryStatus statusTo) {
        this.statusTo = statusTo;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Enquiry getEnquiry() {
        return this.enquiry;
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public String getFollowupActivity() {
        return this.followupActivity;
    }

    public void setFollowupActivity(String followupActivity) {
        this.followupActivity = followupActivity;
    }
}
