package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.vo.StudentCategoryVO;
import java.util.ArrayList;
import java.util.List;

public class StudentCategoryVOMapper {
    public static List<StudentCategoryVO> prepareStudentCategoryVOs(List<StudentCategory> studentCategories) {
        List<StudentCategoryVO> studentCategoryVOs = new ArrayList();
        if (!(studentCategories == null || studentCategories.isEmpty())) {
            for (StudentCategory studentCategory : studentCategories) {
                studentCategoryVOs.add(prepareStudentCategoryVO(studentCategory));
            }
        }
        return studentCategoryVOs;
    }

    public static StudentCategoryVO prepareStudentCategoryVO(StudentCategory studentCategory) {
        if (studentCategory == null) {
            return null;
        }
        StudentCategoryVO studentCategoryVO = new StudentCategoryVO();
        studentCategoryVO.setId(studentCategory.getId());
        studentCategoryVO.setName(studentCategory.getName());
        studentCategoryVO.setActive(studentCategory.getActive());
        if (studentCategory.getCreatedBy() != null) {
            studentCategoryVO.setCreatedBy(studentCategory.getCreatedBy().getFullName());
        }
        if (studentCategory.getCreatedDateTime() != null) {
            studentCategoryVO.setCreatedOn(DateUtil.formatDate(studentCategory.getCreatedDateTime(), "dd-MMM-yyyy hh:mm a"));
        }
        if (studentCategory.getModifiedBy() != null) {
            studentCategoryVO.setModifiedBy(studentCategory.getModifiedBy().getFullName());
        }
        if (studentCategory.getModifiedDateTime() == null) {
            return studentCategoryVO;
        }
        studentCategoryVO.setModifiedOn(DateUtil.formatDate(studentCategory.getModifiedDateTime(), "dd-MMM-yyyy hh:mm a"));
        return studentCategoryVO;
    }
}
