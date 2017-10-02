package com.narendra.sams.web.restws.communication.mapper;

import com.narendra.sams.admission.domain.StudentConversation;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.communication.vo.StudentConversationVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;

public class StudentConversationVOMapper {
    public static StudentConversationVO prepareConversationVO(StudentConversation studentConversation) {
        StudentConversationVO studentConversationVO = new StudentConversationVO();
        studentConversationVO.setId(studentConversation.getId());
        studentConversationVO.setStudentDBId(studentConversation.getStudent().getId());
        studentConversationVO.setStudentId(studentConversation.getStudent().getStudentId());
        studentConversationVO.setStudentName(studentConversation.getStudent().getFullName());
        studentConversationVO.setStudentClass(StudentInformationUtil.getClassName(studentConversation.getStudentClass()));
        studentConversationVO.setConversation(studentConversation.getConversation());
        studentConversationVO.setCreatedBy(studentConversation.getCreatedBy().getFullName());
        studentConversationVO.setLastModifiedBy(studentConversation.getLastModifiedBy().getFullName());
        studentConversationVO.setConversationWith(studentConversation.getConversationWith());
        studentConversationVO.setConversationAgenda(studentConversation.getConversationAgenda());
        studentConversationVO.setConversationUser(studentConversation.getConversationUser());
        if (studentConversation.getConversationDate() != null) {
            studentConversationVO.setConversationDate(DateUtil.formatDate(studentConversation.getConversationDate(), "dd-MMM-yyyy hh:mm a"));
        }
        if (studentConversation.getConversationType() != null) {
            studentConversationVO.setConversationTypeId(studentConversation.getConversationType().getId());
            studentConversationVO.setConversationType(studentConversation.getConversationType().getName());
        }
        return studentConversationVO;
    }

    public static List<StudentConversationVO> prepareStudentConversationsVO(List<StudentConversation> studentConversations) {
        List<StudentConversationVO> studentConversationVOs = new ArrayList();
        if (!(studentConversations == null || studentConversations.isEmpty())) {
            for (StudentConversation studentConversation : studentConversations) {
                studentConversationVOs.add(prepareConversationVO(studentConversation));
            }
        }
        return studentConversationVOs;
    }
}
