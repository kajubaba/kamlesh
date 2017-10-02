package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.vo.AcademicSessionAdmissionSchemeVO;
import com.narendra.sams.web.restws.vo.AdmissionSchemeDetailVO;
import java.util.ArrayList;
import java.util.List;

public class AcademicSessionAdmissionSchemeVOMapper {
    public static List<AcademicSessionAdmissionSchemeVO> prepareAcademicSessionAdmissionSchemeVOs(List<AcademicYearAdmissionScheme> academicYearAdmissionSchemes) {
        List<AcademicSessionAdmissionSchemeVO> academicSessionAdmissionSchemeVOs = new ArrayList();
        if (!(academicYearAdmissionSchemes == null || academicYearAdmissionSchemes.isEmpty())) {
            for (AcademicYearAdmissionScheme academicYearAdmissionScheme : academicYearAdmissionSchemes) {
                academicSessionAdmissionSchemeVOs.add(prepareAcademicSessionAdmissionSchemeVO(academicYearAdmissionScheme, null));
            }
        }
        return academicSessionAdmissionSchemeVOs;
    }

    public static AcademicSessionAdmissionSchemeVO prepareAcademicSessionAdmissionSchemeVO(AcademicYearAdmissionScheme academicYearAdmissionScheme, List<FeeHead> feeHeads) {
        if (academicYearAdmissionScheme == null) {
            return null;
        }
        AcademicSessionAdmissionSchemeVO academicSessionAdmissionSchemeVO = new AcademicSessionAdmissionSchemeVO();
        academicSessionAdmissionSchemeVO.setId(academicYearAdmissionScheme.getId());
        academicSessionAdmissionSchemeVO.setAdmissionSchemeId(academicYearAdmissionScheme.getAdmissionScheme().getId());
        academicSessionAdmissionSchemeVO.setAdmissionSchemeName(academicYearAdmissionScheme.getAdmissionScheme().getName());
        if (academicYearAdmissionScheme.getCreatedBy() != null) {
            academicSessionAdmissionSchemeVO.setAssignedBy(academicYearAdmissionScheme.getCreatedBy().getFullName());
        }
        if (academicYearAdmissionScheme.getCreatedDateTime() != null) {
            academicSessionAdmissionSchemeVO.setAssignedOn(DateUtil.formatDate(academicYearAdmissionScheme.getCreatedDateTime(), "dd-MMM-yyyy hh:mm a"));
        }
        AdmissionSchemeDetailVO admissionSchemeDetailVO;
        if (academicYearAdmissionScheme.getSchemeDetails() != null && !academicYearAdmissionScheme.getSchemeDetails().isEmpty()) {
            academicSessionAdmissionSchemeVO.setAdmissionSchemeDetails(new ArrayList());
            for (AcademicSessionAdmisionSchemeDetail academicSessionAdmisionSchemeDetail : academicYearAdmissionScheme.getSchemeDetails()) {
                admissionSchemeDetailVO = new AdmissionSchemeDetailVO();
                admissionSchemeDetailVO.setId(academicSessionAdmisionSchemeDetail.getId());
                admissionSchemeDetailVO.setFeeHeadId(academicSessionAdmisionSchemeDetail.getFeeHead().getId());
                admissionSchemeDetailVO.setFeeHead(academicSessionAdmisionSchemeDetail.getFeeHead().getName());
                admissionSchemeDetailVO.setDiscount(academicSessionAdmisionSchemeDetail.getDiscount());
                academicSessionAdmissionSchemeVO.getAdmissionSchemeDetails().add(admissionSchemeDetailVO);
            }
            return academicSessionAdmissionSchemeVO;
        } else if (feeHeads == null || feeHeads.isEmpty()) {
            return academicSessionAdmissionSchemeVO;
        } else {
            academicSessionAdmissionSchemeVO.setAdmissionSchemeDetails(new ArrayList());
            for (FeeHead feeHead : feeHeads) {
                admissionSchemeDetailVO = new AdmissionSchemeDetailVO();
                admissionSchemeDetailVO.setFeeHeadId(feeHead.getId());
                admissionSchemeDetailVO.setFeeHead(feeHead.getName());
                academicSessionAdmissionSchemeVO.getAdmissionSchemeDetails().add(admissionSchemeDetailVO);
            }
            return academicSessionAdmissionSchemeVO;
        }
    }
}
