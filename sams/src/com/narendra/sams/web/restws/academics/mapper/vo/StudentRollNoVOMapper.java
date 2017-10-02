package com.narendra.sams.web.restws.academics.mapper.vo;

import com.narendra.sams.academics.exam.domain.StudentRollNo;
import com.narendra.sams.web.restws.academics.exam.vo.StudentRollNoVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;

public class StudentRollNoVOMapper {
    public static StudentRollNoVO prepareStudentRollNoVO(StudentRollNo studentRollNo) {
        if (studentRollNo == null) {
            return null;
        }
        StudentRollNoVO studentRollNoVO = new StudentRollNoVO();
        studentRollNoVO.setId(studentRollNo.getId());
        studentRollNoVO.setStudentDBId(studentRollNo.getStudent().getId());
        studentRollNoVO.setStudentId(studentRollNo.getStudent().getStudentId());
        if (studentRollNo.getStudent().getClassSection() != null) {
            studentRollNoVO.setStudentSection(studentRollNo.getStudent().getClassSection().getSectionName());
        }
        studentRollNoVO.setRollNo(studentRollNo.getRollNo());
        studentRollNoVO.setStudentName(StudentInformationUtil.getFullName(studentRollNo.getStudent().getFirstName(), studentRollNo.getStudent().getMiddleName(), studentRollNo.getStudent().getLastName()));
        studentRollNoVO.setStudentGender(studentRollNo.getStudent().getGender());
        studentRollNoVO.setFatherName(studentRollNo.getStudent().getFatherName());
        return studentRollNoVO;
    }

    public static List<StudentRollNoVO> prepareStudentRollNoVOs(List<StudentRollNo> studentRollNos) {
        List<StudentRollNoVO> studentRollNoVOs = new ArrayList();
        if (studentRollNos != null) {
            for (StudentRollNo studentRollNo : studentRollNos) {
                studentRollNoVOs.add(prepareStudentRollNoVO(studentRollNo));
            }
        }
        return studentRollNoVOs;
    }
}
