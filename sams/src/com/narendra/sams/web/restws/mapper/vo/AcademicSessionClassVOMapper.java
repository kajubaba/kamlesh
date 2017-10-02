package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.web.restws.vo.AcademicSessionClassVO;

public class AcademicSessionClassVOMapper {
    public static AcademicSessionClassVO prepareAcademicSessionClassVO(AcademicYearClass academicYearClass) {
        AcademicSessionClassVO academicSessionClassVO = new AcademicSessionClassVO();
        if (academicYearClass != null) {
            academicSessionClassVO.setDisplayName(academicYearClass.getDisplayName());
            academicSessionClassVO.setActive(academicYearClass.getActive());
            academicSessionClassVO.setId(academicYearClass.getId());
            academicSessionClassVO.setIntake(academicYearClass.getIntake());
            academicSessionClassVO.setNextClassName(academicYearClass.getNextClassName());
        }
        return academicSessionClassVO;
    }
}
