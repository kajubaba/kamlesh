package com.narendra.sams.core.domain;

import java.util.Date;
import java.util.Set;

public class IDGenerationMethod {
    private Set<AcademicYearClassIDGenMethod> academicYearClassIDGenMethods;
    private Long id;
    private String idPrefix;
    private Institute institute;
    private UserView lastModifiedBy;
    private Date lastModifiedOn;
    private String name;
    private Long nextNo;

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

    public String getIdPrefix() {
        return this.idPrefix;
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    public Long getNextNo() {
        return this.nextNo;
    }

    public void setNextNo(Long nextNo) {
        this.nextNo = nextNo;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public UserView getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(UserView lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedOn() {
        return this.lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Set<AcademicYearClassIDGenMethod> getAcademicYearClassIDGenMethods() {
        return this.academicYearClassIDGenMethods;
    }

    public void setAcademicYearClassIDGenMethods(Set<AcademicYearClassIDGenMethod> academicYearClassIDGenMethods) {
        this.academicYearClassIDGenMethods = academicYearClassIDGenMethods;
    }
}
