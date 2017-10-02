package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.vo.AcademicSessionVO;
import java.util.ArrayList;
import java.util.List;

public class AcademicSessionVOMapper {
    public static List<AcademicSessionVO> prepareAcademicSessionVOs(List<AcademicYear> academicYears) {
        List<AcademicSessionVO> academicSessionVOs = new ArrayList();
        if (!(academicYears == null || academicYears.isEmpty())) {
            for (AcademicYear academicYear : academicYears) {
                academicSessionVOs.add(prepareAcademicSessionVO(academicYear));
            }
        }
        return academicSessionVOs;
    }

    public static AcademicSessionVO prepareAcademicSessionVO(AcademicYear academicYear) {
        if (academicYear == null) {
            return null;
        }
        AcademicSessionVO academicSessionVO = new AcademicSessionVO();
        academicSessionVO.setId(academicYear.getId());
        academicSessionVO.setName(academicYear.getName());
        academicSessionVO.setFrom(DateUtil.formatDate(academicYear.getFromDate(), "dd-MMM-yyyy"));
        academicSessionVO.setTo(DateUtil.formatDate(academicYear.getToDate(), "dd-MMM-yyyy"));
        academicSessionVO.setOrderNo(academicYear.getOrderNo());
        if (academicYear.getCreatedBy() != null) {
            academicSessionVO.setCreatedBy(academicYear.getCreatedBy().getFullName());
        }
        if (academicYear.getCreatedDate() != null) {
            academicSessionVO.setCreatedOn(DateUtil.formatDate(academicYear.getCreatedDate(), "dd-MMM-yyyy hh:mm a"));
        }
        if (academicYear.getModifiedBy() != null) {
            academicSessionVO.setModifiedBy(academicYear.getModifiedBy().getFullName());
        }
        if (academicYear.getModifiedDate() != null) {
            academicSessionVO.setModifiedOn(DateUtil.formatDate(academicYear.getModifiedDate(), "dd-MMM-yyyy hh:mm a"));
        }
        academicSessionVO.setStatus(academicYear.getStatus());
        return academicSessionVO;
    }
}
