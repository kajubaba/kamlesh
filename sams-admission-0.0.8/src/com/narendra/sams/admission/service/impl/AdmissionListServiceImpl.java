package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.AdmissionListDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentQuick;
import com.narendra.sams.admission.service.AdmissionListService;
import java.util.Collection;
import java.util.List;

public class AdmissionListServiceImpl implements AdmissionListService {
    private AdmissionListDAO admissionListDAO;

    public AdmissionListDAO getAdmissionListDAO() {
        return this.admissionListDAO;
    }

    public void setAdmissionListDAO(AdmissionListDAO admissionListDAO) {
        this.admissionListDAO = admissionListDAO;
    }

    public List<ClassHistory> getAdmissionsByClass(Collection<Long> classes) {
        return this.admissionListDAO.getAdmissionsByClass(classes);
    }

    public List<ClassHistory> getAdmissionsByClass(Long academicYearClassId, Long studentStatus, Short admissionType) {
        return this.admissionListDAO.getAdmissionsByClass(academicYearClassId, studentStatus, admissionType);
    }

    public List<ClassHistory> getAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        return this.admissionListDAO.getAdmissions(academicYearId, studentStatusId, admissionTypeId);
    }

    public List<ClassHistory> getUnderSchemeAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        return this.admissionListDAO.getUnderSchemeAdmissions(academicYearId, studentStatusId, admissionTypeId);
    }

    public List<Student> getStudents(Long academicYearId, Long studentStatusId) {
        return this.admissionListDAO.getStudents(academicYearId, studentStatusId);
    }

    public List<Student> getStudents(Long academicYearId, Long courseYearId, Long studentStatusId) {
        return this.admissionListDAO.getStudents(academicYearId, courseYearId, studentStatusId);
    }

    public List<StudentQuick> searchStudents(String chars, Long institueId) {
        return this.admissionListDAO.searchStudents(chars, institueId);
    }

    public List<ClassHistory> getUnderSchemeAdmissions(Long academicYearId, Long admissionSchemeId) {
        return this.admissionListDAO.getUnderSchemeAdmissions(academicYearId, admissionSchemeId);
    }

    public List<Student> getStudentsForTranslations(Long academicSessionId, Long classId, Long studentStatusId) {
        return this.admissionListDAO.getStudentsForTranslations(academicSessionId, classId, studentStatusId);
    }
}
