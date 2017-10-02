package com.narendra.sams.web.restws.student.vo;

public class StudentDocumentVO {
    private String comments;
    private String docAcademicYear;
    private String documentCategory;
    private Long documentIdToBeUploaded;
    private String documentName;
    private Boolean mandatory;
    private Long studentId;
    private Long uploadeDocumentId;
    private Boolean uploaded;
    private String uploadedDocumentPath;

    public Long getUploadeDocumentId() {
        return this.uploadeDocumentId;
    }

    public void setUploadeDocumentId(Long uploadeDocumentId) {
        this.uploadeDocumentId = uploadeDocumentId;
    }

    public String getDocumentCategory() {
        return this.documentCategory;
    }

    public void setDocumentCategory(String documentCategory) {
        this.documentCategory = documentCategory;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getUploadedDocumentPath() {
        return this.uploadedDocumentPath;
    }

    public String getDocAcademicYear() {
        return this.docAcademicYear;
    }

    public void setDocAcademicYear(String docAcademicYear) {
        this.docAcademicYear = docAcademicYear;
    }

    public void setUploadedDocumentPath(String uploadedDocumentPath) {
        this.uploadedDocumentPath = uploadedDocumentPath;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Boolean getUploaded() {
        return this.uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public Long getDocumentIdToBeUploaded() {
        return this.documentIdToBeUploaded;
    }

    public void setDocumentIdToBeUploaded(Long documentIdToBeUploaded) {
        this.documentIdToBeUploaded = documentIdToBeUploaded;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
