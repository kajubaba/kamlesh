package com.narendra.sams.web.restws.admission.form;

public class ChangeRequestForm {
    private String comments;
    private Boolean isForced;
    private Long newId;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getNewId() {
        return this.newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsForced() {
        return this.isForced;
    }

    public void setIsForced(Boolean isForced) {
        this.isForced = isForced;
    }
}
