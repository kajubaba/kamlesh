package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface StudentCategoryService {
    List<StudentCategory> getActiveStudentCategories(Long l);

    List<StudentCategory> getAllStudentCategories(Long l);

    StudentCategory getStudentCategory(Long l);

    Long saveStudentCategory(StudentCategory studentCategory, Long l) throws DuplicateNameFoundException;
}
