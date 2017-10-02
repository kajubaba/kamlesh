package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupListVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;

public class EnquiryFollowupListVOMaker {
    public static EnquiryFollowupListVO prepareEnquiryFollowupListVO(EnquiryFolloup enquiryFolloup) {
        EnquiryFollowupListVO enquiryFollowupListVO = new EnquiryFollowupListVO();
        if (enquiryFolloup != null) {
            enquiryFollowupListVO.setFollowupId(enquiryFolloup.getId());
            enquiryFollowupListVO.setEnquiryId(enquiryFolloup.getEnquiry().getId());
            enquiryFollowupListVO.setStudentName(enquiryFolloup.getEnquiry().getStudentFullName());
            if (enquiryFolloup.getEnquiry().getAcademicYearClass() != null) {
                enquiryFollowupListVO.setStudentClass(StudentInformationUtil.getClassName(enquiryFolloup.getEnquiry().getAcademicYearClass()));
            }
            if (enquiryFolloup.getFollowupNextAction() != null) {
                enquiryFollowupListVO.setNextFollowupAction(enquiryFolloup.getFollowupNextAction().getName());
            }
            if (enquiryFolloup.getNextFollowupDate() != null) {
                enquiryFollowupListVO.setNextFollowupOn(DateUtil.formatDate(enquiryFolloup.getNextFollowupDate(), "dd-MMM-yyyy"));
            }
            enquiryFollowupListVO.setNextFollowupHr(enquiryFolloup.getNextFollowupHr());
            enquiryFollowupListVO.setNextFollowupMin(enquiryFolloup.getNextFollowupMin());
            enquiryFollowupListVO.setAmOrPm(enquiryFolloup.getAmOrPM());
            enquiryFollowupListVO.setStudentContactNo(enquiryFolloup.getEnquiry().getStudentContactNo());
            enquiryFollowupListVO.setFatherContactNo(enquiryFolloup.getEnquiry().getFatherContactNo());
            enquiryFollowupListVO.setMotherContactNo(enquiryFolloup.getEnquiry().getMotherContactNo());
        }
        return enquiryFollowupListVO;
    }

    public static List<EnquiryFollowupListVO> prepareEnquiryFollowupListVOs(List<EnquiryFolloup> enquiryFolloups) {
        List<EnquiryFollowupListVO> enquiryFollowupListVOs = new ArrayList();
        if (enquiryFolloups != null) {
            for (EnquiryFolloup enquiryFolloup : enquiryFolloups) {
                enquiryFollowupListVOs.add(prepareEnquiryFollowupListVO(enquiryFolloup));
            }
        }
        return enquiryFollowupListVOs;
    }
}
