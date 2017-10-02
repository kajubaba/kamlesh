package com.narendra.sams.web.restws.communication.form;

public class StudentConversationForm {
    private String conversation;
    private String conversationAgenda;
    private Long conversationType;
    private String conversationUser;
    private String conversationWith;
    private Long id;
    private Boolean isSelf;
    private Long studentId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getConversation() {
        return this.conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public Long getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(Long conversationType) {
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

    public Boolean getIsSelf() {
        return this.isSelf;
    }

    public void setIsSelf(Boolean isSelf) {
        this.isSelf = isSelf;
    }
}
