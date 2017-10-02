package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.enquiry.domain.FollowupCommConclusion;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.ArrayList;
import java.util.List;

public class FollowupCommConclusionVOMapper {
    public static EnquiryFollowupMasterVO prepareEnquiryFollowupVO(FollowupCommConclusion followupCommConclusion) {
        EnquiryFollowupMasterVO enquiryFollowupVO = new EnquiryFollowupMasterVO();
        if (followupCommConclusion != null) {
            enquiryFollowupVO.setId(followupCommConclusion.getId());
            enquiryFollowupVO.setActive(followupCommConclusion.getActive());
            enquiryFollowupVO.setName(followupCommConclusion.getName());
        }
        return enquiryFollowupVO;
    }

    public static List<EnquiryFollowupMasterVO> prepareEnquiryFollowupVOs(List<FollowupCommConclusion> followupCommConclusions) {
        List<EnquiryFollowupMasterVO> enquiryFollowupVOs = new ArrayList();
        if (followupCommConclusions != null) {
            for (FollowupCommConclusion followupCommConclusion : followupCommConclusions) {
                enquiryFollowupVOs.add(prepareEnquiryFollowupVO(followupCommConclusion));
            }
        }
        return enquiryFollowupVOs;
    }
}
