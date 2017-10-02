package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.UserView;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class CustomizeFee implements Serializable {
    private static final long serialVersionUID = 4248397452968000940L;
    private AcademicYearClass academicYearClass;
    private UserView createdBy;
    private Date createdDate;
    private Set<CustomizeInstallment> customizeInstallments;
    private Long id;
    private UserView modifiedBy;
    private Date modifiedDate;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
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

    public Set<CustomizeInstallment> getCustomizeInstallments() {
        return this.customizeInstallments;
    }

    public void setCustomizeInstallments(Set<CustomizeInstallment> customizeInstallments) {
        this.customizeInstallments = customizeInstallments;
    }
}
