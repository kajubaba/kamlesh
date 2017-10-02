package com.narendra.sams.communication.domain;

import com.narendra.sams.core.domain.Institute;

public class InstituteSMSProvider {
    private String authKey;
    private Long id;
    private Institute institute;
    private Boolean isEnabled;
    private String senderId;
    private String smsProviderName;
    private String url;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
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

    public String getSmsProviderName() {
        return this.smsProviderName;
    }

    public void setSmsProviderName(String smsProviderName) {
        this.smsProviderName = smsProviderName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
