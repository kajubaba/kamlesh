package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class StudentActivity {
    private AcademicYear academicYear;
    private AcademicYearClass academicYearClass;
    private AddmissionActionOld addmissionAction;
    private String comments;
    private UserView createdByUser;
    private Date createdDatetime;
    private Long id;
    private String log;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AddmissionActionOld getAddmissionAction() {
        return this.addmissionAction;
    }

    public void setAddmissionAction(AddmissionActionOld addmissionAction) {
        this.addmissionAction = addmissionAction;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public UserView getCreatedByUser() {
        return this.createdByUser;
    }

    public void setCreatedByUser(UserView createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getCreatedDatetime() {
        return this.createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
