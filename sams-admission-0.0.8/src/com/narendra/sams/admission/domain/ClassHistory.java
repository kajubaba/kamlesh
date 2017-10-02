package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.domain.UserView;
import java.io.Serializable;
import java.util.Date;

public class ClassHistory implements Serializable {
    private static final long serialVersionUID = -6383423207818559587L;
    private UserView UserView;
    private AcademicYear academicYear;
    private AcademicYearClass academicYearClass;
    private Boolean activeClass;
    private Date admissionConfirmDateTime;
    private String admissionConfirmed;
    private String admissionFormNo;
    private AdmissionScheme admissionScheme;
    private AdmissionType admissionType;
    private BusStop busStop;
    private String changeStatusComments;
    private ClassSection classSection;
    private Date createdDate;
    private Long id;
    private Date lastRegistrationDate;
    private Date modifiedDate;
    private UserView statusChangedBy;
    private Student student;
    private StudentStatus studentStatus;

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

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public UserView getUser() {
        return this.UserView;
    }

    public void setUser(UserView UserView) {
        this.UserView = UserView;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public AdmissionType getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public String getAdmissionConfirmed() {
        return this.admissionConfirmed;
    }

    public void setAdmissionConfirmed(String admissionConfirmed) {
        this.admissionConfirmed = admissionConfirmed;
    }

    public BusStop getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public String getAdmissionFormNo() {
        return this.admissionFormNo;
    }

    public void setAdmissionFormNo(String admissionFormNo) {
        this.admissionFormNo = admissionFormNo;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.academicYear == null ? 0 : this.academicYear.hashCode()) + 31) * 31) + (this.academicYearClass == null ? 0 : this.academicYearClass.hashCode())) * 31;
        if (this.student != null) {
            i = this.student.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ClassHistory)) {
            return false;
        }
        ClassHistory other = (ClassHistory) obj;
        if (this.academicYear == null) {
            if (other.academicYear != null) {
                return false;
            }
        } else if (!this.academicYear.equals(other.academicYear)) {
            return false;
        }
        if (this.academicYearClass == null) {
            if (other.academicYearClass != null) {
                return false;
            }
        } else if (!this.academicYearClass.equals(other.academicYearClass)) {
            return false;
        }
        if (this.student == null) {
            if (other.student != null) {
                return false;
            }
            return true;
        } else if (this.student.equals(other.student)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean getActiveClass() {
        return this.activeClass;
    }

    public void setActiveClass(Boolean activeClass) {
        this.activeClass = activeClass;
    }

    public StudentStatus getStudentStatus() {
        return this.studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getChangeStatusComments() {
        return this.changeStatusComments;
    }

    public void setChangeStatusComments(String changeStatusComments) {
        this.changeStatusComments = changeStatusComments;
    }

    public UserView getStatusChangedBy() {
        return this.statusChangedBy;
    }

    public void setStatusChangedBy(UserView statusChangedBy) {
        this.statusChangedBy = statusChangedBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getAdmissionConfirmDateTime() {
        return this.admissionConfirmDateTime;
    }

    public void setAdmissionConfirmDateTime(Date admissionConfirmDateTime) {
        this.admissionConfirmDateTime = admissionConfirmDateTime;
    }

    public AdmissionScheme getAdmissionScheme() {
        return this.admissionScheme;
    }

    public void setAdmissionScheme(AdmissionScheme admissionScheme) {
        this.admissionScheme = admissionScheme;
    }

    public Date getLastRegistrationDate() {
        return this.lastRegistrationDate;
    }

    public void setLastRegistrationDate(Date lastRegistrationDate) {
        this.lastRegistrationDate = lastRegistrationDate;
    }

    public ClassSection getClassSection() {
        return this.classSection;
    }

    public void setClassSection(ClassSection classSection) {
        this.classSection = classSection;
    }
}
