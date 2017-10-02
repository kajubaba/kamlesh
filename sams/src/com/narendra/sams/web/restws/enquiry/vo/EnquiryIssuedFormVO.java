package com.narendra.sams.web.restws.enquiry.vo;

import java.util.List;

public class EnquiryIssuedFormVO {
    private List<EnquiryVO> enquiryVOs;
    private String fromDate;
    private String toDate;

    public String getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<EnquiryVO> getEnquiryVOs() {
        return this.enquiryVOs;
    }

    public void setEnquiryVOs(List<EnquiryVO> enquiryVOs) {
        this.enquiryVOs = enquiryVOs;
    }
}
