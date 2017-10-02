package com.narendra.sams.enquiry.domain;

import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class EnquiryFolloup {
    private String amOrPM;
    private String communicationSummary;
    private UserView createdBy;
    private Date createdOn;
    private Enquiry enquiry;
    private Date followedOn;
    private FollowupCommConclusion followupCommConclusion;
    private FollowupCommType followupCommType;
    private FollowupCommWith followupCommWith;
    private FollowupNextAction followupNextAction;
    private FollowupSuggestion followupSuggestion;
    private Long id;
    private UserView modifiedBy;
    private Date modifiedOn;
    private Date nextFollowupDate;
    private Long nextFollowupHr;
    private Long nextFollowupMin;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enquiry getEnquiry() {
        return this.enquiry;
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public FollowupCommType getFollowupCommType() {
        return this.followupCommType;
    }

    public void setFollowupCommType(FollowupCommType followupCommType) {
        this.followupCommType = followupCommType;
    }

    public FollowupCommWith getFollowupCommWith() {
        return this.followupCommWith;
    }

    public void setFollowupCommWith(FollowupCommWith followupCommWith) {
        this.followupCommWith = followupCommWith;
    }

    public FollowupCommConclusion getFollowupCommConclusion() {
        return this.followupCommConclusion;
    }

    public void setFollowupCommConclusion(FollowupCommConclusion followupCommConclusion) {
        this.followupCommConclusion = followupCommConclusion;
    }

    public FollowupNextAction getFollowupNextAction() {
        return this.followupNextAction;
    }

    public void setFollowupNextAction(FollowupNextAction followupNextAction) {
        this.followupNextAction = followupNextAction;
    }

    public FollowupSuggestion getFollowupSuggestion() {
        return this.followupSuggestion;
    }

    public void setFollowupSuggestion(FollowupSuggestion followupSuggestion) {
        this.followupSuggestion = followupSuggestion;
    }

    public String getCommunicationSummary() {
        return this.communicationSummary;
    }

    public void setCommunicationSummary(String communicationSummary) {
        this.communicationSummary = communicationSummary;
    }

    public Date getNextFollowupDate() {
        return this.nextFollowupDate;
    }

    public void setNextFollowupDate(Date nextFollowupDate) {
        this.nextFollowupDate = nextFollowupDate;
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

    public String getAmOrPM() {
        return this.amOrPM;
    }

    public void setAmOrPM(String amOrPM) {
        this.amOrPM = amOrPM;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public UserView getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getFollowedOn() {
        return this.followedOn;
    }

    public void setFollowedOn(Date followedOn) {
        this.followedOn = followedOn;
    }
}
