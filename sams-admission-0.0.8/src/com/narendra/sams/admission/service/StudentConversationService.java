package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.StudentConversation;
import com.narendra.sams.admission.domain.StudentConversationType;
import java.util.Date;
import java.util.List;

public interface StudentConversationService {
    List<StudentConversationType> getActiveConversationTypes(Long l);

    StudentConversation getConversation(Long l);

    List<StudentConversation> getStudentConversations(Long l);

    List<StudentConversation> getStudentConversations(Date date, Date date2, Long l, Long l2, Long l3);

    void saveConversation(StudentConversation studentConversation, Long l, Boolean bool, Long l2);
}
