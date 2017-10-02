package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.vo.AdmissionSchemeVO;
import java.util.ArrayList;
import java.util.List;

public class AdmissionSchemeVOMapper {
    public static List<AdmissionSchemeVO> prepareAdmissionSchemeVO(List<AdmissionScheme> admissionSchemes) {
        List<AdmissionSchemeVO> admissionSchemeVOs = new ArrayList();
        if (!(admissionSchemes == null || admissionSchemes.isEmpty())) {
            for (AdmissionScheme admissionScheme : admissionSchemes) {
                admissionSchemeVOs.add(prepareAdmissionSchemeVO(admissionScheme));
            }
        }
        return admissionSchemeVOs;
    }

    public static AdmissionSchemeVO prepareAdmissionSchemeVO(AdmissionScheme admissionScheme) {
        if (admissionScheme == null) {
            return null;
        }
        AdmissionSchemeVO admissionSchemeVO = new AdmissionSchemeVO();
        admissionSchemeVO.setSchemeId(admissionScheme.getId());
        admissionSchemeVO.setSchemeName(admissionScheme.getName());
        admissionSchemeVO.setActive(admissionScheme.getActive());
        if (admissionScheme.getCreatedBy() != null) {
            admissionSchemeVO.setCreatedBy(admissionScheme.getCreatedBy().getFullName());
        }
        if (admissionScheme.getCreatedDateTime() != null) {
            admissionSchemeVO.setCreatedOn(DateUtil.formatDate(admissionScheme.getCreatedDateTime(), "dd-MMM-yyyy hh:mm a"));
        }
        if (admissionScheme.getModifiedBy() != null) {
            admissionSchemeVO.setModifiedBy(admissionScheme.getModifiedBy().getFullName());
        }
        if (admissionScheme.getModifiedDateTime() == null) {
            return admissionSchemeVO;
        }
        admissionSchemeVO.setModifiedOn(DateUtil.formatDate(admissionScheme.getModifiedDateTime(), "dd-MMM-yyyy hh:mm a"));
        return admissionSchemeVO;
    }
}
