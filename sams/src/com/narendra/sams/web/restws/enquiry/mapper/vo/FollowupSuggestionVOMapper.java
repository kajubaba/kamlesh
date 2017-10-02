package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.enquiry.domain.FollowupSuggestion;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.ArrayList;
import java.util.List;

public class FollowupSuggestionVOMapper {
    public static EnquiryFollowupMasterVO prepareEnquiryFollowupVO(FollowupSuggestion followupSuggestion) {
        EnquiryFollowupMasterVO enquiryFollowupVO = new EnquiryFollowupMasterVO();
        if (followupSuggestion != null) {
            enquiryFollowupVO.setId(followupSuggestion.getId());
            enquiryFollowupVO.setActive(followupSuggestion.getActive());
            enquiryFollowupVO.setName(followupSuggestion.getName());
        }
        return enquiryFollowupVO;
    }

    public static List<EnquiryFollowupMasterVO> prepareEnquiryFollowupVOs(List<FollowupSuggestion> followupSuggestions) {
        List<EnquiryFollowupMasterVO> enquiryFollowupVOs = new ArrayList();
        if (followupSuggestions != null) {
            for (FollowupSuggestion followupSuggestion : followupSuggestions) {
                enquiryFollowupVOs.add(prepareEnquiryFollowupVO(followupSuggestion));
            }
        }
        return enquiryFollowupVOs;
    }
}
