package com.narendra.sams.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class AcademicYear implements Serializable {
    public static final String ADMISSION = "admission";
    public static final String ENQUIRY = "enquiry";
    public static final String STATUS_DRAFT = "Draft";
    public static final String STATUS_IN_ACTIVE = "In-Active";
    public static final String STATUS_PUBLISHED = "Published";
    private static final long serialVersionUID = -9119508559261718151L;
    private Set<AcademicYearCourse> courses;
    private UserView createdBy;
    private Date createdDate;
    private String description;
    private Date fromDate;
    private Long id;
    private Institute institute;
    private UserView modifiedBy;
    private Date modifiedDate;
    private String name;
    private Short orderNo;
    private String status;
    private Date toDate;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<AcademicYearCourse> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<AcademicYearCourse> courses) {
        this.courses = courses;
    }

    public Date getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return this.toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void copyEditableProperties(AcademicYear academicYear) {
        setName(academicYear.getName());
        setFromDate(academicYear.getFromDate());
        setToDate(academicYear.toDate);
        setDescription(academicYear.description);
    }

    public Short getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(Short orderNo) {
        this.orderNo = orderNo;
    }

    public int hashCode() {
        return (this.id == null ? 0 : this.id.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AcademicYear)) {
            return false;
        }
        AcademicYear other = (AcademicYear) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
            return true;
        } else if (this.id.equals(other.id)) {
            return true;
        } else {
            return false;
        }
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
