package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.StudentDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentBankDetails;
import com.narendra.sams.admission.domain.StudentGaurdian;
import com.narendra.sams.admission.domain.StudentParentsInformation;
import com.narendra.sams.admission.domain.StudentPersonalInformation;
import com.narendra.sams.admission.domain.StudentTranslation;
import com.narendra.sams.admission.service.StudentActivityLogService;
import com.narendra.sams.admission.service.StudentService;
import java.util.Collection;

public class StudentServiceImpl implements StudentService {
    private StudentActivityLogService studentActivityLogService;
    private StudentDAO studentDAO;

    public StudentDAO getStudentDAO() {
        return this.studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentActivityLogService getStudentActivityLogService() {
        return this.studentActivityLogService;
    }

    public void setStudentActivityLogService(StudentActivityLogService studentActivityLogService) {
        this.studentActivityLogService = studentActivityLogService;
    }

    public Student getStudentById(Long id) {
        return this.studentDAO.getStudentById(id);
    }

    public void updatePersonalInformation(Student student, Long userId) {
        this.studentDAO.updatePersonalInformation(student, userId);
    }

    public Long addGaurdian(StudentGaurdian studentGaurdian, Long userId) {
        if (studentGaurdian == null) {
            return null;
        }
        Student student = getStudentById(studentGaurdian.getStudent().getId());
        this.studentActivityLogService.addGaurdianAddedActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, null);
        return this.studentDAO.addGaurdian(studentGaurdian, userId);
    }

    public void updateGaurdian(StudentGaurdian studentGaurdian, Long userId) {
        this.studentDAO.updateGaurdian(studentGaurdian, userId);
        Student student = getStudentById(studentGaurdian.getStudent().getId());
        this.studentActivityLogService.addGaurdianAddedActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, null);
    }

    public void updateStudentPersonalInformation(StudentPersonalInformation studentPersonalInformation, Long userId) {
        this.studentDAO.updateStudentPersonalInformation(studentPersonalInformation, userId);
        Student student = getStudentById(studentPersonalInformation.getStudentId());
        this.studentActivityLogService.addPersonalInformationUpdatedActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, null);
    }

    public void updateStudentParentsInformation(StudentParentsInformation studentParentsInformation, Long userId) {
        this.studentDAO.updateStudentParentsInformation(studentParentsInformation, userId);
        Student student = getStudentById(studentParentsInformation.getStudentId());
        this.studentActivityLogService.addParentsInformationUpdatedActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, null);
    }

    public ClassHistory getClassHistory(Long id) {
        return this.studentDAO.getClassHistory(id);
    }

    public StudentBankDetails getStudentBankDetails(Long studentId) {
        return this.studentDAO.getStudentBankDetails(studentId);
    }

    public Long updateStudentBankDetails(StudentBankDetails studentBankDetails, Long userId) {
        Long bankDetailId = this.studentDAO.updateStudentBankDetails(studentBankDetails, userId);
        Student student = getStudentById(studentBankDetails.getStudent().getId());
        this.studentActivityLogService.addBanksDetailsChangedActivity(student.getId(), student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, null);
        return bankDetailId;
    }

    public void updateImageName(Long studentId, String imageName) {
        if (studentId != null && imageName != null) {
            this.studentDAO.updateImageName(studentId, imageName);
        }
    }

    public void updateStudentTranslations(Collection<StudentTranslation> studentTranslations) {
        if (studentTranslations != null) {
            this.studentDAO.updateStudentTranslations(studentTranslations);
        }
    }

    public void updateStudentTranslation(StudentTranslation studentTranslation) {
        if (studentTranslation != null) {
            this.studentDAO.updateStudentTranslation(studentTranslation);
        }
    }

    public Student getStudent(Long academicSessionId, String formNo) {
        if (academicSessionId == null || formNo == null || formNo == null) {
            return null;
        }
        return this.studentDAO.getStudent(academicSessionId, formNo.trim());
    }
}
