package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.StudentCategory;
import java.util.List;

public interface StudentCategoryDAO {
    Long addStudentCategory(StudentCategory studentCategory, Long l);

    List<StudentCategory> getActiveStudentCategories(Long l);

    List<StudentCategory> getAllStudentCategories(Long l);

    StudentCategory getStudentCategory(Long l);

    StudentCategory getStudentCategoryByName(String str, Long l);

    void updateStudentCategory(StudentCategory studentCategory, Long l);
}
