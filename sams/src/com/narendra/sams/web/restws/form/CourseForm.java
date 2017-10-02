package com.narendra.sams.web.restws.form;

public class CourseForm {
    private Boolean active;
    private Long affiliationAuthorityId;
    private String displayName;
    private Short duration;
    private Long id;
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

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
