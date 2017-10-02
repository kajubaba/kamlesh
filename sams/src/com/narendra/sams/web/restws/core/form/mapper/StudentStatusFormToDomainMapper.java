package com.narendra.sams.web.restws.core.form.mapper;

import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.web.restws.core.form.StudentStatusForm;

public class StudentStatusFormToDomainMapper {
    public static StudentStatus mapToDomain(StudentStatusForm studentStatusForm) {
        if (studentStatusForm == null) {
            return null;
        }
        StudentStatus studentStatus = new StudentStatus();
        studentStatus.setId(studentStatusForm.getId());
        studentStatus.setName(studentStatusForm.getName());
        studentStatus.setActive(studentStatusForm.isActive());
        return studentStatus;
    }
}
