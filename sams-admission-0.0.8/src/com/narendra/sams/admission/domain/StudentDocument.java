package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class StudentDocument {
    private String comments;
    private AcademicYear docAcademicYear;
    private String docName;
    private String docPath;
    private Document document;
    private Long id;
    private Student student;
    private UserView uploadedBy;
    private Date uploadedOn;

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

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDocName() {
        return this.docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public AcademicYear getDocAcademicYear() {
        return this.docAcademicYear;
    }

    public void setDocAcademicYear(AcademicYear docAcademicYear) {
        this.docAcademicYear = docAcademicYear;
    }

    public String getDocPath() {
        return this.docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public UserView getUploadedBy() {
        return this.uploadedBy;
    }

    public void setUploadedBy(UserView uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Date getUploadedOn() {
        return this.uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
