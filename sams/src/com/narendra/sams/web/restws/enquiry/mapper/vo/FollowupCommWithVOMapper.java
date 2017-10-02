package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.enquiry.domain.FollowupCommWith;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.ArrayList;
import java.util.List;

public class FollowupCommWithVOMapper {
    public static EnquiryFollowupMasterVO prepareEnquiryFollowupVO(FollowupCommWith followupCommWith) {
        EnquiryFollowupMasterVO enquiryFollowupVO = new EnquiryFollowupMasterVO();
        if (followupCommWith != null) {
            enquiryFollowupVO.setId(followupCommWith.getId());
            enquiryFollowupVO.setActive(followupCommWith.getActive());
            enquiryFollowupVO.setName(followupCommWith.getName());
        }
        return enquiryFollowupVO;
    }

    public static List<EnquiryFollowupMasterVO> prepareEnquiryFollowupVOs(List<FollowupCommWith> followupCommWithList) {
        List<EnquiryFollowupMasterVO> enquiryFollowupVOs = new ArrayList();
        if (followupCommWithList != null) {
            for (FollowupCommWith followupCommWith : followupCommWithList) {
                enquiryFollowupVOs.add(prepareEnquiryFollowupVO(followupCommWith));
            }
        }
        return enquiryFollowupVOs;
    }
}
