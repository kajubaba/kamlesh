package com.narendra.sams.web.restws.communication.vo;

public class SMSSettingVO {
    private Long id;
    private Boolean isEnabled = Boolean.FALSE;
    private String notificationType = "";
    private Boolean sendToFather = Boolean.FALSE;
    private Boolean sendToMother = Boolean.FALSE;
    private Boolean sendToStudent = Boolean.FALSE;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationType() {
        return this.notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getSendToStudent() {
        return this.sendToStudent;
    }

    public void setSendToStudent(Boolean sendToStudent) {
        this.sendToStudent = sendToStudent;
    }

    public Boolean getSendToFather() {
        return this.sendToFather;
    }

    public void setSendToFather(Boolean sendToFather) {
        this.sendToFather = sendToFather;
    }

    public Boolean getSendToMother() {
        return this.sendToMother;
    }

    public void setSendToMother(Boolean sendToMother) {
        this.sendToMother = sendToMother;
    }
}
