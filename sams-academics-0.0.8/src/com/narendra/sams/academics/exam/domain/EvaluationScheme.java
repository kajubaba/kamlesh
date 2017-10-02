package com.narendra.sams.academics.exam.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;
import java.util.Set;

public class EvaluationScheme {
    private AcademicYear academicYear;
    private Set<EvaluationSchemeClass> appliedOnClasses;
    private UserView createdByUser;
    private Date createdDateTime;
    private Set<EvaluationType> evaluationTypes;
    private Long id;
    private Institute institute;
    private Boolean isMaster;
    private UserView lastModifiedByUser;
    private Date lastModifiedDateTime;
    private String schemeName;
    private String scorecardTemplate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Set<EvaluationType> getEvaluationTypes() {
        return this.evaluationTypes;
    }

    public void setEvaluationTypes(Set<EvaluationType> evaluationTypes) {
        this.evaluationTypes = evaluationTypes;
    }

    public Set<EvaluationSchemeClass> getAppliedOnClasses() {
        return this.appliedOnClasses;
    }

    public void setAppliedOnClasses(Set<EvaluationSchemeClass> appliedOnClasses) {
        this.appliedOnClasses = appliedOnClasses;
    }

    public Boolean getIsMaster() {
        return this.isMaster;
    }

    public void setIsMaster(Boolean isMaster) {
        this.isMaster = isMaster;
    }

    public String getScorecardTemplate() {
        return this.scorecardTemplate;
    }

    public void setScorecardTemplate(String scorecardTemplate) {
        this.scorecardTemplate = scorecardTemplate;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
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
}
