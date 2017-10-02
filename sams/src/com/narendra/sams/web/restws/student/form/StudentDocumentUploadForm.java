package com.narendra.sams.web.restws.student.form;

import org.springframework.web.multipart.MultipartFile;

public class StudentDocumentUploadForm {
    private String comments;
    private Long documentCategoryId;
    private MultipartFile documentFile;
    private Long documentId;
    private String documentName;
    private Long studentId;

    public Long getDocumentCategoryId() {
        return this.documentCategoryId;
    }

    public void setDocumentCategoryId(Long documentCategoryId) {
        this.documentCategoryId = documentCategoryId;
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public MultipartFile getDocumentFile() {
        return this.documentFile;
    }

    public void setDocumentFile(MultipartFile documentFile) {
        this.documentFile = documentFile;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
