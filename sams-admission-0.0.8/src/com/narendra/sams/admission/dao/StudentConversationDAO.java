package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.StudentConversation;
import com.narendra.sams.admission.domain.StudentConversationType;
import java.util.Date;
import java.util.List;

public interface StudentConversationDAO {
    void addConversation(StudentConversation studentConversation, Long l, Long l2);

    List<StudentConversationType> getActiveConversationTypes(Long l);

    StudentConversation getConversation(Long l);

    List<StudentConversation> getStudentConversations(Long l);

    List<StudentConversation> getStudentConversations(Date date, Date date2, Long l, Long l2, Long l3);

    void updateConversation(StudentConversation studentConversation, Long l);
}
