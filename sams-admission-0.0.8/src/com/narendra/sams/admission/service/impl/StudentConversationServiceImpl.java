package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.StudentConversationDAO;
import com.narendra.sams.admission.domain.StudentConversation;
import com.narendra.sams.admission.domain.StudentConversationType;
import com.narendra.sams.admission.service.StudentConversationService;
import com.narendra.sams.core.address.dao.UserViewDAO;
import java.util.Date;
import java.util.List;

public class StudentConversationServiceImpl implements StudentConversationService {
    private StudentConversationDAO studentConversationDAO;
    private UserViewDAO userViewDAO;

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public StudentConversationDAO getStudentConversationDAO() {
        return this.studentConversationDAO;
    }

    public void setStudentConversationDAO(StudentConversationDAO studentConversationDAO) {
        this.studentConversationDAO = studentConversationDAO;
    }

    public void saveConversation(StudentConversation studentConversation, Long userId, Boolean isSelf, Long instituteId) {
        if (studentConversation != null) {
            if (isSelf.booleanValue()) {
                studentConversation.setConversationUser(this.userViewDAO.loadUser(userId).getFullName());
            }
            if (studentConversation.getId() == null) {
                this.studentConversationDAO.addConversation(studentConversation, userId, instituteId);
            } else {
                this.studentConversationDAO.updateConversation(studentConversation, userId);
            }
        }
    }

    public StudentConversation getConversation(Long id) {
        return this.studentConversationDAO.getConversation(id);
    }

    public List<StudentConversation> getStudentConversations(Long studentId) {
        return this.studentConversationDAO.getStudentConversations(studentId);
    }

    public List<StudentConversation> getStudentConversations(Date from, Date to, Long by, Long mode, Long instituteId) {
        return this.studentConversationDAO.getStudentConversations(from, to, by, mode, instituteId);
    }

    public List<StudentConversationType> getActiveConversationTypes(Long instituteId) {
        if (instituteId == null) {
            return null;
        }
        return this.studentConversationDAO.getActiveConversationTypes(instituteId);
    }
}
