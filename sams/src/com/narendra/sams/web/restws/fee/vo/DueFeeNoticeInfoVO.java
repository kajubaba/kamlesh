package com.narendra.sams.web.restws.fee.vo;

public class DueFeeNoticeInfoVO {
    private String addressedBy;
    private String noticeGenerationDate;
    private String noticeHeader;
    private String noticeMessage;
    private String noticeName;
    private String noticeSubHeader;
    private String noticeType;

    public String getNoticeHeader() {
        return this.noticeHeader;
    }

    public void setNoticeHeader(String noticeHeader) {
        this.noticeHeader = noticeHeader;
    }

    public String getNoticeSubHeader() {
        return this.noticeSubHeader;
    }

    public void setNoticeSubHeader(String noticeSubHeader) {
        this.noticeSubHeader = noticeSubHeader;
    }

    public String getNoticeName() {
        return this.noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getNoticeMessage() {
        return this.noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public String getAddressedBy() {
        return this.addressedBy;
    }

    public void setAddressedBy(String addressedBy) {
        this.addressedBy = addressedBy;
    }

    public String getNoticeGenerationDate() {
        return this.noticeGenerationDate;
    }

    public void setNoticeGenerationDate(String noticeGenerationDate) {
        this.noticeGenerationDate = noticeGenerationDate;
    }

    public String getNoticeType() {
        return this.noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }
}
