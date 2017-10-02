package com.narendra.sams.communication.domain;

import com.narendra.sams.core.domain.Institute;

public class SMSSetting {
    public static String BIRTH_DAY_WISH_MSG = "BIRTH_DAY_WISH_MSG";
    public static String FEE_DEPOSIT_MSG = "FEE_DEPOSIT_MSG";
    private Long id;
    private Institute institute;
    private Boolean isEnabled;
    private String notificationType;
    private Boolean sendToFather;
    private Boolean sendToMother;
    private Boolean sendToStudent;

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

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
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
