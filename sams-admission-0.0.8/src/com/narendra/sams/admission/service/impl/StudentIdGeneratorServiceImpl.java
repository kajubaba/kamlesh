package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.StudentDAO;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentIdGeneratorService;
import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYearAdmissionCount;
import com.narendra.sams.core.domain.AdmissionSettings;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.IdGenerationMethodService;
import com.narendra.sams.core.service.InstituteSettingService;
import com.narendra.sams.core.util.DateUtil;

public class StudentIdGeneratorServiceImpl implements StudentIdGeneratorService {
    private AcademicYearDAO academicYearDAO;
    private AcademicYearService academicYearService;
    private IdGenerationMethodService idGenerationMethodService;
    private InstituteSettingService instituteSettingService;
    private StudentDAO studentDAO;

    public StudentDAO getStudentDAO() {
        return this.studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public AcademicYearService getAcademicYearService() {
        return this.academicYearService;
    }

    public void setAcademicYearService(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    public AcademicYearDAO getAcademicYearDAO() {
        return this.academicYearDAO;
    }

    public void setAcademicYearDAO(AcademicYearDAO academicYearDAO) {
        this.academicYearDAO = academicYearDAO;
    }

    public InstituteSettingService getInstituteSettingService() {
        return this.instituteSettingService;
    }

    public void setInstituteSettingService(InstituteSettingService instituteSettingService) {
        this.instituteSettingService = instituteSettingService;
    }

    public IdGenerationMethodService getIdGenerationMethodService() {
        return this.idGenerationMethodService;
    }

    public void setIdGenerationMethodService(IdGenerationMethodService idGenerationMethodService) {
        this.idGenerationMethodService = idGenerationMethodService;
    }

    public synchronized String generateStudentId(Long instituteId, Long academicYearId) {
        String generatedId;
        InstituteSetting instituteSetting = this.instituteSettingService.getInstituteSetting(instituteId);
        generatedId = null;
        if (AdmissionSettings.ID_GENERATION_METHOD_INCREMENTAL.equals(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod())) {
            Long studentId = instituteSetting.getAdmissionSettings().getNextStudentId();
            instituteSetting.getAdmissionSettings().setNextStudentId(Long.valueOf(studentId.longValue() + 1));
            generatedId = studentId.toString();
        } else if (AdmissionSettings.ID_GENERATION_METHOD_CUSTOMIZED.equals(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod())) {
            generatedId = generateCustomizedStudentId(academicYearId, instituteSetting);
        }
        return generatedId;
    }

    private String generateCustomizedStudentId(Long academicYearId, InstituteSetting instituteSetting) {
        String studentId = instituteSetting.getAdmissionSettings().getConfirmStudentIdPrefix();
        AcademicYearAdmissionCount academicYearAdmissionCount = this.studentDAO.loadAcademicYearAdmissionCount(academicYearId);
        long count = academicYearAdmissionCount.getAdmissionCount().longValue() + 1;
        studentId = new StringBuilder(String.valueOf(studentId)).append(this.academicYearDAO.getAcademicYearById(academicYearId.longValue()).getName().split("-")[0].trim()).toString();
        if (count < 10) {
            studentId = new StringBuilder(String.valueOf(studentId)).append("000").append(count).toString();
        } else if (count < 100) {
            studentId = new StringBuilder(String.valueOf(studentId)).append("00").append(count).toString();
        } else if (count < 1000) {
            studentId = new StringBuilder(String.valueOf(studentId)).append("0").append(count).toString();
        } else {
            studentId = new StringBuilder(String.valueOf(studentId)).append(count).toString();
        }
        academicYearAdmissionCount.setAdmissionCount(Long.valueOf(count));
        return studentId;
    }

    public void generateStudentId(Long studentId) {
        Student student = this.studentDAO.getStudentById(studentId);
        if (student.getIsIdGenerated() == null || Boolean.FALSE.equals(student.getIsIdGenerated())) {
            InstituteSetting instituteSetting = this.instituteSettingService.getInstituteSetting(student.getInstitute().getId());
            String generatedId = null;
            if (instituteSetting.getIsIdGenerationMethodSame().booleanValue()) {
                if (AdmissionSettings.ID_GENERATION_METHOD_INCREMENTAL.equals(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod())) {
                    Long nextStudentId = instituteSetting.getAdmissionSettings().getNextStudentId();
                    instituteSetting.getAdmissionSettings().setNextStudentId(Long.valueOf(nextStudentId.longValue() + 1));
                    generatedId = nextStudentId.toString();
                } else if (AdmissionSettings.ID_GENERATION_METHOD_CUSTOMIZED.equals(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod())) {
                    generatedId = generateCustomizedStudentId(student.getAcademicYear().getId(), instituteSetting);
                }
                student.setStudentId(generatedId);
                student.setIsIdGenerated(Boolean.TRUE);
                student.setAdmissionConfirmationDate(DateUtil.getSystemDate());
            }
        }
    }
}
