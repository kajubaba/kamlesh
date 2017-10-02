package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class StudentConversation {
    private String conversation;
    private String conversationAgenda;
    private Date conversationDate;
    private StudentConversationType conversationType;
    private String conversationUser;
    private String conversationWith;
    private UserView createdBy;
    private Date createdDateTime;
    private Long id;
    private Institute institute;
    private UserView lastModifiedBy;
    private Date lastModifiedDateTime;
    private Student student;
    private AcademicYearClass studentClass;

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

    public AcademicYearClass getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(AcademicYearClass studentClass) {
        this.studentClass = studentClass;
    }

    public String getConversation() {
        return this.conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public UserView getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(UserView lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public StudentConversationType getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(StudentConversationType conversationType) {
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

    public Date getConversationDate() {
        return this.conversationDate;
    }

    public void setConversationDate(Date conversationDate) {
        this.conversationDate = conversationDate;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
