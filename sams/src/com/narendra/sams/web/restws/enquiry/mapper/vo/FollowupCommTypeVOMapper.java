package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.enquiry.domain.FollowupCommType;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.ArrayList;
import java.util.List;

public class FollowupCommTypeVOMapper {
    public static EnquiryFollowupMasterVO prepareEnquiryFollowupVO(FollowupCommType followupCommType) {
        EnquiryFollowupMasterVO enquiryFollowupVO = new EnquiryFollowupMasterVO();
        if (followupCommType != null) {
            enquiryFollowupVO.setId(followupCommType.getId());
            enquiryFollowupVO.setActive(followupCommType.getActive());
            enquiryFollowupVO.setName(followupCommType.getName());
        }
        return enquiryFollowupVO;
    }

    public static List<EnquiryFollowupMasterVO> prepareEnquiryFollowupVOs(List<FollowupCommType> followupCommTypes) {
        List<EnquiryFollowupMasterVO> enquiryFollowupVOs = new ArrayList();
        if (followupCommTypes != null) {
            for (FollowupCommType followupCommType : followupCommTypes) {
                enquiryFollowupVOs.add(prepareEnquiryFollowupVO(followupCommType));
            }
        }
        return enquiryFollowupVOs;
    }
}
