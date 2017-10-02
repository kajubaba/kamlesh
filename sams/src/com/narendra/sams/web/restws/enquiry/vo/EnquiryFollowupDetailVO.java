package com.narendra.sams.web.restws.enquiry.vo;

public class EnquiryFollowupDetailVO {
    private String amOrPM;
    private String commConclusion;
    private String commSummary;
    private String commType;
    private String commWith;
    private String createdByUser;
    private String createdOn;
    private Long enquiryId;
    private Long id;
    private String modifiedByUser;
    private String modifiedOn;
    private String nextAction;
    private String nextFollowupDate;
    private Long nextFollowupHr;
    private Long nextFollowupMin;
    private String suggestion;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommType() {
        return this.commType;
    }

    public void setCommType(String commType) {
        this.commType = commType;
    }

    public String getCommWith() {
        return this.commWith;
    }

    public void setCommWith(String commWith) {
        this.commWith = commWith;
    }

    public String getCommConclusion() {
        return this.commConclusion;
    }

    public void setCommConclusion(String commConclusion) {
        this.commConclusion = commConclusion;
    }

    public String getSuggestion() {
        return this.suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getNextAction() {
        return this.nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public String getCommSummary() {
        return this.commSummary;
    }

    public void setCommSummary(String commSummary) {
        this.commSummary = commSummary;
    }

    public String getNextFollowupDate() {
        return this.nextFollowupDate;
    }

    public void setNextFollowupDate(String nextFollowupDate) {
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

    public String getCreatedByUser() {
        return this.createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getModifiedByUser() {
        return this.modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public String getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Long getEnquiryId() {
        return this.enquiryId;
    }

    public void setEnquiryId(Long enquiryId) {
        this.enquiryId = enquiryId;
    }
}
