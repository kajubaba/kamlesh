package com.narendra.sams.core.domain;

import java.util.Date;

public class Bank {
    private Boolean active;
    private String bankName;
    private UserView createdByUser;
    private Date createdDateTime;
    private Long id;
    private Institute institute;
    private UserView lastModifiedByUser;
    private Date lastModifiedDateTime;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public UserView getCreatedByUser() {
        return this.createdByUser;
    }

    public void setCreatedByUser(UserView createdByUser) {
        this.createdByUser = createdByUser;
    }

    public UserView getLastModifiedByUser() {
        return this.lastModifiedByUser;
    }

    public void setLastModifiedByUser(UserView lastModifiedByUser) {
        this.lastModifiedByUser = lastModifiedByUser;
    }

    public Date getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
