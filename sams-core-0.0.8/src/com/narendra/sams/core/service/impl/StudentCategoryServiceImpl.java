package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.StudentCategoryDAO;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.StudentCategoryService;
import java.util.List;

public class StudentCategoryServiceImpl implements StudentCategoryService {
    private StudentCategoryDAO studentCategoryDAO;

    public StudentCategoryDAO getStudentCategoryDAO() {
        return this.studentCategoryDAO;
    }

    public void setStudentCategoryDAO(StudentCategoryDAO studentCategoryDAO) {
        this.studentCategoryDAO = studentCategoryDAO;
    }

    public List<StudentCategory> getActiveStudentCategories(Long instituteId) {
        return this.studentCategoryDAO.getActiveStudentCategories(instituteId);
    }

    public List<StudentCategory> getAllStudentCategories(Long instituteId) {
        return this.studentCategoryDAO.getAllStudentCategories(instituteId);
    }

    public StudentCategory getStudentCategory(Long studentCategoryId) {
        return this.studentCategoryDAO.getStudentCategory(studentCategoryId);
    }

    public Long saveStudentCategory(StudentCategory studentCategory, Long userId) throws DuplicateNameFoundException {
        if (studentCategory == null || userId == null) {
            return null;
        }
        StudentCategory persistStudentCategory = this.studentCategoryDAO.getStudentCategoryByName(studentCategory.getName(), studentCategory.getInstitute().getId());
        if (studentCategory.getId() == null) {
            if (persistStudentCategory == null) {
                return this.studentCategoryDAO.addStudentCategory(studentCategory, userId);
            }
            throw new DuplicateNameFoundException("Admission Scheme [" + studentCategory.getName() + "] already exists");
        } else if (persistStudentCategory == null || studentCategory.getId().equals(persistStudentCategory.getId())) {
            this.studentCategoryDAO.updateStudentCategory(studentCategory, userId);
            return studentCategory.getId();
        } else {
            throw new DuplicateNameFoundException("Admission Scheme [" + persistStudentCategory.getName() + "] already exists");
        }
    }
}
