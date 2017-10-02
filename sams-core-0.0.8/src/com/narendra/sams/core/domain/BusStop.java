package com.narendra.sams.core.domain;

import java.util.Date;
import java.util.Set;

public class BusStop {
    private Boolean active;
    private Set<BusFee> busFees;
    private UserView createdBy;
    private Date createdDate;
    private Float distance;
    private Long id;
    private Institute institute;
    private UserView modifiedBy;
    private Date modifiedDate;
    private String name;
    private String nameInOtherLang;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDistance() {
        return this.distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserView getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<BusFee> getBusFees() {
        return this.busFees;
    }

    public void setBusFees(Set<BusFee> busFees) {
        this.busFees = busFees;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getNameInOtherLang() {
        return this.nameInOtherLang;
    }

    public void setNameInOtherLang(String nameInOtherLang) {
        this.nameInOtherLang = nameInOtherLang;
    }
}
