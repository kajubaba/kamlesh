package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupDetailVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupVO;
import java.util.ArrayList;
import java.util.List;

public class EnquiryFollowupVOMapper {
    public static EnquiryFollowupDetailVO prepareEnquiryFollowupDetailVO(EnquiryFolloup enquiryFolloup) {
        EnquiryFollowupDetailVO enquiryFollowupDetailVO = new EnquiryFollowupDetailVO();
        if (enquiryFolloup != null) {
            enquiryFollowupDetailVO.setId(enquiryFolloup.getId());
            if (enquiryFolloup.getFollowupCommType() != null) {
                enquiryFollowupDetailVO.setCommType(enquiryFolloup.getFollowupCommType().getName());
            }
            if (enquiryFolloup.getFollowupCommWith() != null) {
                enquiryFollowupDetailVO.setCommWith(enquiryFolloup.getFollowupCommWith().getName());
            }
            if (enquiryFolloup.getFollowupCommConclusion() != null) {
                enquiryFollowupDetailVO.setCommConclusion(enquiryFolloup.getFollowupCommConclusion().getName());
            }
            if (enquiryFolloup.getFollowupNextAction() != null) {
                enquiryFollowupDetailVO.setNextAction(enquiryFolloup.getFollowupNextAction().getName());
            }
            if (enquiryFolloup.getFollowupSuggestion() != null) {
                enquiryFollowupDetailVO.setSuggestion(enquiryFolloup.getFollowupSuggestion().getName());
            }
            if (enquiryFolloup.getNextFollowupDate() != null) {
                enquiryFollowupDetailVO.setNextFollowupDate(DateUtil.formatDate(enquiryFolloup.getNextFollowupDate(), "dd-MMM-yyyy"));
            }
            enquiryFollowupDetailVO.setNextFollowupHr(enquiryFolloup.getNextFollowupHr());
            enquiryFollowupDetailVO.setNextFollowupMin(enquiryFolloup.getNextFollowupMin());
            enquiryFollowupDetailVO.setAmOrPM(enquiryFolloup.getAmOrPM());
            enquiryFollowupDetailVO.setCommSummary(enquiryFolloup.getCommunicationSummary());
            enquiryFollowupDetailVO.setCreatedByUser(enquiryFolloup.getCreatedBy().getFullName());
            enquiryFollowupDetailVO.setModifiedByUser(enquiryFolloup.getModifiedBy().getFullName());
            enquiryFollowupDetailVO.setCreatedOn(DateUtil.formatDate(enquiryFolloup.getCreatedOn(), "dd-MMM-yyyy"));
            enquiryFollowupDetailVO.setModifiedOn(DateUtil.formatDate(enquiryFolloup.getModifiedOn(), "dd-MMM-yyyy"));
            enquiryFollowupDetailVO.setEnquiryId(enquiryFolloup.getEnquiry().getId());
        }
        return enquiryFollowupDetailVO;
    }

    public static List<EnquiryFollowupDetailVO> prepareEnquiryFollowupDetailVOs(List<EnquiryFolloup> enquiryFolloups) {
        List<EnquiryFollowupDetailVO> enquiryFollowupDetailVOs = new ArrayList();
        if (enquiryFolloups != null) {
            for (EnquiryFolloup enquiryFolloup : enquiryFolloups) {
                enquiryFollowupDetailVOs.add(prepareEnquiryFollowupDetailVO(enquiryFolloup));
            }
        }
        return enquiryFollowupDetailVOs;
    }

    public static EnquiryFollowupVO prepareEnquiryFollowupVO(EnquiryFolloup enquiryFolloup) {
        EnquiryFollowupVO enquiryFollowupVO = new EnquiryFollowupVO();
        if (enquiryFolloup != null) {
            enquiryFollowupVO.setId(enquiryFolloup.getId());
            if (enquiryFolloup.getFollowupCommType() != null) {
                enquiryFollowupVO.setCommTypeId(enquiryFolloup.getFollowupCommType().getId());
            }
            if (enquiryFolloup.getFollowupCommWith() != null) {
                enquiryFollowupVO.setCommWithId(enquiryFolloup.getFollowupCommWith().getId());
            }
            if (enquiryFolloup.getFollowupCommConclusion() != null) {
                enquiryFollowupVO.setCommConclusionId(enquiryFolloup.getFollowupCommConclusion().getId());
            }
            if (enquiryFolloup.getFollowupNextAction() != null) {
                enquiryFollowupVO.setNextActionId(enquiryFolloup.getFollowupNextAction().getId());
            }
            if (enquiryFolloup.getFollowupSuggestion() != null) {
                enquiryFollowupVO.setSuggestionId(enquiryFolloup.getFollowupSuggestion().getId());
            }
            if (enquiryFolloup.getNextFollowupDate() != null) {
                enquiryFollowupVO.setNextFollowupDate(DateUtil.formatDate(enquiryFolloup.getNextFollowupDate(), "dd-MMM-yyyy"));
            }
            enquiryFollowupVO.setCommSummary(enquiryFolloup.getCommunicationSummary());
            enquiryFollowupVO.setNextFollowupHr(enquiryFolloup.getNextFollowupHr());
            enquiryFollowupVO.setNextFollowupMin(enquiryFolloup.getNextFollowupMin());
            enquiryFollowupVO.setAmOrPM(enquiryFolloup.getAmOrPM());
        }
        return enquiryFollowupVO;
    }
}
