package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.web.restws.form.StudentCategoryForm;

public class StudentCategoryFormMapper {
    public static StudentCategory prepareStudentCategoryDomain(StudentCategoryForm studentCategoryForm) {
        if (studentCategoryForm == null) {
            return null;
        }
        StudentCategory studentCategory = new StudentCategory();
        studentCategory.setId(studentCategoryForm.getId());
        studentCategory.setName(studentCategoryForm.getName());
        studentCategory.setActive(studentCategoryForm.getActive());
        return studentCategory;
    }
}
