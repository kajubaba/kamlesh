package com.narendra.sams.core.domain;

import java.util.Date;
import java.util.Set;

public class Course {
    private AffiliationAuthority affiliatedTo;
    private Set<CourseYear> courseYears;
    private UserView createdBy;
    private Date createdDate;
    private String description;
    private String displayName;
    private Short duration;
    private Long id;
    private Institute institute;
    private UserView modifiedBy;
    private Date modifiedDate;
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

    public AffiliationAuthority getAffiliatedTo() {
        return this.affiliatedTo;
    }

    public void setAffiliatedTo(AffiliationAuthority affiliatedTo) {
        this.affiliatedTo = affiliatedTo;
    }

    public Set<CourseYear> getCourseYears() {
        return this.courseYears;
    }

    public void setCourseYears(Set<CourseYear> courseYears) {
        this.courseYears = courseYears;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
