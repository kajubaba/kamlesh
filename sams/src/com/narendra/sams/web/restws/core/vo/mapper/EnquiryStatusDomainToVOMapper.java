package com.narendra.sams.web.restws.core.vo.mapper;

import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.web.restws.core.vo.EnquiryStatusVO;
import java.util.ArrayList;
import java.util.List;

public class EnquiryStatusDomainToVOMapper {
    public static EnquiryStatusVO mapToVO(EnquiryStatus enquiryStatus) {
        if (enquiryStatus == null) {
            return null;
        }
        EnquiryStatusVO enquiryStatusVO = new EnquiryStatusVO();
        enquiryStatusVO.setId(enquiryStatus.getId());
        enquiryStatusVO.setName(enquiryStatus.getName());
        enquiryStatusVO.setActive(enquiryStatus.getActive());
        return enquiryStatusVO;
    }

    public static List<EnquiryStatusVO> mapToVOs(List<EnquiryStatus> enquiryStatusList) {
        if (enquiryStatusList == null) {
            return null;
        }
        List<EnquiryStatusVO> enquiryStatusVOs = new ArrayList();
        for (EnquiryStatus enquiryStatus : enquiryStatusList) {
            enquiryStatusVOs.add(mapToVO(enquiryStatus));
        }
        return enquiryStatusVOs;
    }
}
