package com.narendra.sams.web.restws.communication.mapper;

import com.narendra.sams.admission.domain.StudentConversationType;
import com.narendra.sams.web.restws.admission.vo.ConversationTypeVO;
import java.util.ArrayList;
import java.util.List;

public class ConversationTypeVOMapper {
    public static List<ConversationTypeVO> prepareConversationTypeVOs(List<StudentConversationType> studentConversationTypes) {
        List<ConversationTypeVO> conversationTypeVOs = new ArrayList();
        if (!(studentConversationTypes == null || studentConversationTypes.isEmpty())) {
            for (StudentConversationType studentConversationType : studentConversationTypes) {
                conversationTypeVOs.add(prepareConversationTypeVO(studentConversationType));
            }
        }
        return conversationTypeVOs;
    }

    public static ConversationTypeVO prepareConversationTypeVO(StudentConversationType studentConversationType) {
        if (studentConversationType == null) {
            return null;
        }
        ConversationTypeVO conversationTypeVO = new ConversationTypeVO();
        conversationTypeVO.setId(studentConversationType.getId());
        conversationTypeVO.setName(studentConversationType.getName());
        conversationTypeVO.setActive(studentConversationType.getActive());
        return conversationTypeVO;
    }
}
