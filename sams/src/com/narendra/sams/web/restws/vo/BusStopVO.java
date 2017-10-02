package com.narendra.sams.web.restws.vo;

public class BusStopVO {
    private Boolean active;
    private String createdBy;
    private String createdOn;
    private Float distance;
    private Long id;
    private String modifiedBy;
    private String modifiedOn;
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

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getNameInOtherLang() {
        return this.nameInOtherLang;
    }

    public void setNameInOtherLang(String nameInOtherLang) {
        this.nameInOtherLang = nameInOtherLang;
    }
}
