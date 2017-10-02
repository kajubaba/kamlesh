package com.narendra.sams.web.restws.communication.vo;

public class StudentConversationVO {
    private String conversation;
    private String conversationAgenda;
    private String conversationDate;
    private String conversationType;
    private Long conversationTypeId;
    private String conversationUser;
    private String conversationWith;
    private String createdBy;
    private String createdDateTime;
    private Long id;
    private String lastModifiedBy;
    private String lastModifiedDateTime;
    private String studentClass;
    private Long studentDBId;
    private String studentId;
    private String studentName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getConversation() {
        return this.conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public String getConversationWith() {
        return this.conversationWith;
    }

    public void setConversationWith(String conversationWith) {
        this.conversationWith = conversationWith;
    }

    public String getConversationAgenda() {
        return this.conversationAgenda;
    }

    public void setConversationAgenda(String conversationAgenda) {
        this.conversationAgenda = conversationAgenda;
    }

    public String getConversationUser() {
        return this.conversationUser;
    }

    public void setConversationUser(String conversationUser) {
        this.conversationUser = conversationUser;
    }

    public String getConversationDate() {
        return this.conversationDate;
    }

    public void setConversationDate(String conversationDate) {
        this.conversationDate = conversationDate;
    }

    public Long getConversationTypeId() {
        return this.conversationTypeId;
    }

    public void setConversationTypeId(Long conversationTypeId) {
        this.conversationTypeId = conversationTypeId;
    }

    public Long getStudentDBId() {
        return this.studentDBId;
    }

    public void setStudentDBId(Long studentDBId) {
        this.studentDBId = studentDBId;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
