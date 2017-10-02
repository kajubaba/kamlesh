package com.narendra.sams.web.restws.enquiry.form;

public class EnquiryFollowupForm {
    private String amOrPM;
    private Long commConclusionId;
    private String commSummary;
    private Long commTypeId;
    private Long commWithId;
    private Long enquiryId;
    private Long id;
    private Long nextActionId;
    private String nextFollowupDate;
    private Long nextFollowupHr;
    private Long nextFollowupMin;
    private Long suggestionId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnquiryId() {
        return this.enquiryId;
    }

    public void setEnquiryId(Long enquiryId) {
        this.enquiryId = enquiryId;
    }

    public Long getCommTypeId() {
        return this.commTypeId;
    }

    public void setCommTypeId(Long commTypeId) {
        this.commTypeId = commTypeId;
    }

    public Long getCommWithId() {
        return this.commWithId;
    }

    public void setCommWithId(Long commWithId) {
        this.commWithId = commWithId;
    }

    public Long getCommConclusionId() {
        return this.commConclusionId;
    }

    public void setCommConclusionId(Long commConclusionId) {
        this.commConclusionId = commConclusionId;
    }

    public Long getSuggestionId() {
        return this.suggestionId;
    }

    public void setSuggestionId(Long suggestionId) {
        this.suggestionId = suggestionId;
    }

    public Long getNextActionId() {
        return this.nextActionId;
    }

    public void setNextActionId(Long nextActionId) {
        this.nextActionId = nextActionId;
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
}
