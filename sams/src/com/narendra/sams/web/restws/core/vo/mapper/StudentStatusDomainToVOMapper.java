package com.narendra.sams.web.restws.core.vo.mapper;

import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.web.restws.core.vo.StudentStatusVO;
import java.util.ArrayList;
import java.util.List;

public class StudentStatusDomainToVOMapper {
    public static StudentStatusVO mapToVO(StudentStatus studentStatus) {
        if (studentStatus == null) {
            return null;
        }
        StudentStatusVO studentStatusVO = new StudentStatusVO();
        studentStatusVO.setId(studentStatus.getId());
        studentStatusVO.setName(studentStatus.getName());
        studentStatusVO.setActive(studentStatus.getActive());
        return studentStatusVO;
    }

    public static List<StudentStatusVO> mapToVOs(List<StudentStatus> studentStatusList) {
        if (studentStatusList == null) {
            return null;
        }
        List<StudentStatusVO> studentStatusVOs = new ArrayList();
        for (StudentStatus studentStatus : studentStatusList) {
            studentStatusVOs.add(mapToVO(studentStatus));
        }
        return studentStatusVOs;
    }
}
