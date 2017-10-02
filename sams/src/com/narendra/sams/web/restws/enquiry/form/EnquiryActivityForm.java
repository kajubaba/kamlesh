package com.narendra.sams.web.restws.enquiry.form;

public class EnquiryActivityForm {
    private String comments;
    private Long enquiryId;
    private Long newChangedId;

    public Long getEnquiryId() {
        return this.enquiryId;
    }

    public void setEnquiryId(Long enquiryId) {
        this.enquiryId = enquiryId;
    }

    public Long getNewChangedId() {
        return this.newChangedId;
    }

    public void setNewChangedId(Long newChangedId) {
        this.newChangedId = newChangedId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
