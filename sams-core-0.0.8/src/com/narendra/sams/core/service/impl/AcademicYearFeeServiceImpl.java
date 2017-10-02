package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AcademicYearFeeDAO;
import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.service.AcademicYearFeeService;
import java.util.Date;
import java.util.List;

public class AcademicYearFeeServiceImpl implements AcademicYearFeeService {
    private AcademicYearFeeDAO academicYearFeeDAO;

    public AcademicYearFeeDAO getAcademicYearFeeDAO() {
        return this.academicYearFeeDAO;
    }

    public void setAcademicYearFeeDAO(AcademicYearFeeDAO academicYearFeeDAO) {
        this.academicYearFeeDAO = academicYearFeeDAO;
    }

    public void saveCourseYearFee(List<AcademicYearFee> academicYearFees) {
        if (academicYearFees != null && !academicYearFees.isEmpty()) {
            this.academicYearFeeDAO.saveCourseYearFee(academicYearFees);
        }
    }

    public List<AcademicYearFee> getAcademicYearFee(Long courseYearId, Long academicYearId) {
        return this.academicYearFeeDAO.getAcademicYearFee(courseYearId, academicYearId);
    }

    public List<AcademicYearFee> getAcademicYearFeeForAllAdmissionType(Long courseYearSetting) {
        return this.academicYearFeeDAO.getAcademicYearFeeForAllAdmissionType(courseYearSetting);
    }

    public AcademicYearFee getAcademicYearFeeForNewAdmissions(Long courseYearSetting) {
        if (courseYearSetting == null) {
            return null;
        }
        return this.academicYearFeeDAO.getAcademicYearFee(courseYearSetting, AdmissionType.NEW_ADMISSION_ID);
    }

    public AcademicYearFee getAcademicYearFeeForRegularAdmissions(Long courseYearSetting) {
        if (courseYearSetting == null) {
            return null;
        }
        return this.academicYearFeeDAO.getAcademicYearFee(courseYearSetting, AdmissionType.REGULAR_ADMISSION_ID);
    }

    public void updateCourseYearFeeDetail(List<AcademicYearFeeDetail> academicYearFeeDetails) {
        this.academicYearFeeDAO.updateCourseYearFeeDetail(academicYearFeeDetails);
    }

    public AcademicYearFee getAcademicYearFeeById(Long academicYearFeeId) {
        return this.academicYearFeeDAO.getAcademicYearFeeById(academicYearFeeId);
    }

    public void saveAcademicYearFeeInstallments(List<AcademicYearFeeInstallment> academicYearFeeInstallments) {
        if (academicYearFeeInstallments != null) {
            this.academicYearFeeDAO.saveAcademicYearFeeInstallments(academicYearFeeInstallments);
        }
    }

    public void updateInstallmentDueDateAndLateFeeRule(List<AcademicYearFeeInstallment> academicYearFeeInstallments) {
        if (academicYearFeeInstallments != null) {
            this.academicYearFeeDAO.updateInstallmentDueDateAndLateFeeRule(academicYearFeeInstallments);
        }
    }

    public AcademicYearFee getAcademicYearFee(Long academicYearId, Long courseYearId, Short admissionTypeId) {
        return this.academicYearFeeDAO.getAcademicYearFee(academicYearId, courseYearId, admissionTypeId);
    }

    public List<AcademicYearFeeInstallment> getFeeInstallments(Long academicYearId, Long courseYearId, Short admissionTypeId, Short[] installmentIds) {
        return this.academicYearFeeDAO.getFeeInstallments(academicYearId, courseYearId, admissionTypeId, installmentIds);
    }

    public List<AcademicYearFeeInstallment> getDueFeeInstallments(Long academicYearId, Long courseYearId, Short admissionTypeId, Date dueDate) {
        return this.academicYearFeeDAO.getDueFeeInstallments(academicYearId, courseYearId, admissionTypeId, dueDate);
    }

    public AcademicYearFeeInstallment getAcademicYearFeeInstallment(Long id) {
        return this.academicYearFeeDAO.getAcademicYearFeeInstallment(id);
    }

    public List<Date> getAcademicInstallmentDueDates(Long academicYearId) {
        return this.academicYearFeeDAO.getAcademicInstallmentDueDates(academicYearId);
    }

    public void saveAcademicYearCourses(List<AcademicYearCourse> academicYearCourses) {
        this.academicYearFeeDAO.saveAcademicYearCourses(academicYearCourses);
    }
}
