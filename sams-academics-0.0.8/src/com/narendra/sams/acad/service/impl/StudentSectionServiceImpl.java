package com.narendra.sams.acad.service.impl;

import com.narendra.sams.acad.dao.StudentSectionDAO;
import com.narendra.sams.acad.service.StudentSectionService;
import com.narendra.sams.admission.domain.ClassHistory;
import java.util.Collection;
import java.util.List;

public class StudentSectionServiceImpl implements StudentSectionService {
    private StudentSectionDAO studentSectionDAO;

    public StudentSectionDAO getStudentSectionDAO() {
        return this.studentSectionDAO;
    }

    public void setStudentSectionDAO(StudentSectionDAO studentSectionDAO) {
        this.studentSectionDAO = studentSectionDAO;
    }

    public List<ClassHistory> getStudents(Long academicYearClassId, Long sectionId) {
        if (academicYearClassId == null) {
            return null;
        }
        return this.studentSectionDAO.getStudents(academicYearClassId, sectionId);
    }

    public void updateStudentSection(Collection<Long> studentIds, Long studentClassId, Long newSectionId) {
        if (studentIds != null && studentClassId != null && newSectionId != null) {
            this.studentSectionDAO.updateStudentSection(studentIds, studentClassId, newSectionId);
        }
    }
}
