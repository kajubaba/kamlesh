package com.narendra.sams.web.restws.vo;

public class CourseVO {
    private Boolean active;
    private String affiliationAuthority;
    private Long affiliationAuthorityId;
    private String createdBy;
    private String createdOn;
    private String displayName;
    private Short duration;
    private Long id;
    private String modifiedBy;
    private String modifiedOn;
    private String name;

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

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Short getDuration() {
        return this.duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    public Long getAffiliationAuthorityId() {
        return this.affiliationAuthorityId;
    }

    public void setAffiliationAuthorityId(Long affiliationAuthorityId) {
        this.affiliationAuthorityId = affiliationAuthorityId;
    }

    public String getAffiliationAuthority() {
        return this.affiliationAuthority;
    }

    public void setAffiliationAuthority(String affiliationAuthority) {
        this.affiliationAuthority = affiliationAuthority;
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
}
