package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.enquiry.domain.FollowupNextAction;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.ArrayList;
import java.util.List;

public class FollowupNextItemVOMapper {
    public static EnquiryFollowupMasterVO prepareEnquiryFollowupVO(FollowupNextAction followupNextAction) {
        EnquiryFollowupMasterVO enquiryFollowupVO = new EnquiryFollowupMasterVO();
        if (followupNextAction != null) {
            enquiryFollowupVO.setId(followupNextAction.getId());
            enquiryFollowupVO.setActive(followupNextAction.getActive());
            enquiryFollowupVO.setName(followupNextAction.getName());
        }
        return enquiryFollowupVO;
    }

    public static List<EnquiryFollowupMasterVO> prepareEnquiryFollowupVOs(List<FollowupNextAction> followupNextActions) {
        List<EnquiryFollowupMasterVO> enquiryFollowupVOs = new ArrayList();
        if (followupNextActions != null) {
            for (FollowupNextAction followupNextAction : followupNextActions) {
                enquiryFollowupVOs.add(prepareEnquiryFollowupVO(followupNextAction));
            }
        }
        return enquiryFollowupVOs;
    }
}
