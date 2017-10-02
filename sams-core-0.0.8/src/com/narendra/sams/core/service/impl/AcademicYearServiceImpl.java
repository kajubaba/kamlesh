package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.AcademicYearAdmissionSchemeService;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.AcademicYearSettingService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AcademicYearServiceImpl implements AcademicYearService {
    private AcademicYearAdmissionSchemeService academicYearAdmissionSchemeService;
    private AcademicYearBusFeeService academicYearBusFeeService;
    private AcademicYearDAO academicYearDAO;
    private AcademicYearFeeService academicYearFeeService;
    private AcademicYearSettingService academicYearSettingService;

    public AcademicYearDAO getAcademicYearDAO() {
        return this.academicYearDAO;
    }

    public void setAcademicYearDAO(AcademicYearDAO academicYearDAO) {
        this.academicYearDAO = academicYearDAO;
    }

    public AcademicYear getActiveAcademicYearForAdmission(Long instituteId) {
        return this.academicYearDAO.getActiveAcademicYearForAdmission(instituteId);
    }

    public AcademicYear getActiveAcademicYearForEnquiry(Long instituteId) {
        return this.academicYearDAO.getActiveAcademicYearForEnquiry(instituteId);
    }

    public List<AcademicYear> getFeeAcademicYears(Long instituteId) {
        return this.academicYearDAO.getFeeAcademicYears(instituteId);
    }

    public AcademicYearSettingService getAcademicYearSettingService() {
        return this.academicYearSettingService;
    }

    public void setAcademicYearSettingService(AcademicYearSettingService academicYearSettingService) {
        this.academicYearSettingService = academicYearSettingService;
    }

    public AcademicYearBusFeeService getAcademicYearBusFeeService() {
        return this.academicYearBusFeeService;
    }

    public void setAcademicYearBusFeeService(AcademicYearBusFeeService academicYearBusFeeService) {
        this.academicYearBusFeeService = academicYearBusFeeService;
    }

    public AcademicYearFeeService getAcademicYearFeeService() {
        return this.academicYearFeeService;
    }

    public void setAcademicYearFeeService(AcademicYearFeeService academicYearFeeService) {
        this.academicYearFeeService = academicYearFeeService;
    }

    public Long saveAcademicYear(AcademicYear academicYear, Long userId) throws DuplicateNameFoundException {
        if (academicYear == null) {
            return null;
        }
        Long academicYearId = academicYear.getId();
        if (academicYear.getId() != null) {
            AcademicYear persistAcademicYear = this.academicYearDAO.getAcademicYearByName(academicYear.getInstitute().getId(), academicYear.getName());
            if (persistAcademicYear == null || persistAcademicYear.getId().equals(academicYear.getId())) {
                this.academicYearDAO.updateAcademicYear(academicYear, userId);
                return academicYearId;
            }
            throw new DuplicateNameFoundException("Academic Session ['" + academicYear.getName() + "'] already exists");
        } else if (this.academicYearDAO.isAcademicYearNameExists(academicYear.getInstitute().getId(), academicYear.getName()).booleanValue()) {
            throw new DuplicateNameFoundException("Academic Session ['" + academicYear.getName() + "'] already exist");
        } else {
            academicYear.setStatus("draft");
            academicYearId = this.academicYearDAO.addAcademicYear(academicYear, userId);
            this.academicYearDAO.saveAcademicYearConfiguration(academicYearId);
            return academicYearId;
        }
    }

    public AcademicYear getAcademicYearById(long id) {
        return this.academicYearDAO.getAcademicYearById(id);
    }

    public List<AcademicYear> getAllAcademicYears(Long instituteId) {
        return this.academicYearDAO.getAllAcademicYears(instituteId);
    }

    public List<AcademicYear> getActiveAcademicYears(Long instituteId) {
        if (instituteId == null) {
            return null;
        }
        return this.academicYearDAO.getActiveAcademicYears(instituteId);
    }

    public Boolean isAcademicYearNameExists(Long instituteId, String name) {
        return this.academicYearDAO.isAcademicYearNameExists(instituteId, name);
    }

    public AcademicYear getAcademicYearByName(Long instituteId, String name) {
        return this.academicYearDAO.getAcademicYearByName(instituteId, name);
    }

    public List<AcademicYearClass> getActiveClassess(Long courseId, Long academicYearId) {
        return this.academicYearDAO.getActiveClassess(courseId, academicYearId);
    }

    public List<AcademicYearClass> getActiveAcademicYearClassess(Long affiliationAuthorityId, Long academicYearId) {
        return this.academicYearDAO.getActiveAcademicYearClassess(affiliationAuthorityId, academicYearId);
    }

    public List<Course> getActiveCourses(Long academicYearId, Long affiliationAuthorityId) {
        return this.academicYearDAO.getActiveCourses(academicYearId, affiliationAuthorityId);
    }

    public List<InstituteSetting> getInstituteSettings() {
        return this.academicYearDAO.getInstituteSettings();
    }

    public List<AcademicYearClass> getPromotionClasses(Long courseId, Long academicYearId) {
        return this.academicYearDAO.getPromotionClasses(courseId, academicYearId);
    }

    public AcademicYear getAcademicYearByOrder(Short orderNo, Long instituteId) {
        return this.academicYearDAO.getAcademicYearByOrder(orderNo, instituteId);
    }

    public void copyAcademicYear(Long fromAcademicYear, Long toAcademicYear, Long userId) {
        List<AcademicYearCourse> fromAcademicYearCourses = this.academicYearSettingService.getAcademicYearCourses(fromAcademicYear);
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(toAcademicYear);
        List<AcademicYearCourse> toAcademicYearCourses = new ArrayList();
        for (AcademicYearCourse fromAcademicYearCourse : fromAcademicYearCourses) {
            AcademicYearCourse toAcademicYearCourse = new AcademicYearCourse();
            toAcademicYearCourse.setAcademicYear(academicYear);
            toAcademicYearCourse.setActive(fromAcademicYearCourse.getActive());
            toAcademicYearCourse.setCourse(fromAcademicYearCourse.getCourse());
            toAcademicYearCourse.setCourseYearSettings(new HashSet());
            for (CourseYearSetting fromCourseYearSetting : fromAcademicYearCourse.getCourseYearSettings()) {
                CourseYearSetting toCourseYearSetting = new CourseYearSetting();
                toCourseYearSetting.setAcademicYear(academicYear);
                toCourseYearSetting.setActive(fromCourseYearSetting.getActive());
                toCourseYearSetting.setCourseYear(fromCourseYearSetting.getCourseYear());
                toCourseYearSetting.setCourseYearType(fromCourseYearSetting.getCourseYearType());
                toCourseYearSetting.setIntake(fromCourseYearSetting.getIntake());
                toCourseYearSetting.setAcademicYearClasses(new HashSet());
                for (AcademicYearClass fromAcademicYearClass : fromCourseYearSetting.getAcademicYearClasses()) {
                    AcademicYearClass toAcademicYearClass = new AcademicYearClass();
                    toAcademicYearClass.setName(fromAcademicYearClass.getName());
                    toAcademicYearClass.setActive(fromAcademicYearClass.getActive());
                    toAcademicYearClass.setAcademicYear(academicYear);
                    toAcademicYearClass.setCourseYear(fromAcademicYearClass.getCourseYear());
                    toAcademicYearClass.setCourseYearSetting(toCourseYearSetting);
                    toAcademicYearClass.setDisplayName(fromAcademicYearClass.getDisplayName());
                    toCourseYearSetting.getAcademicYearClasses().add(toAcademicYearClass);
                }
                toCourseYearSetting.setAcademicYearFees(new HashSet());
                for (AcademicYearFee fromAcademicYearFee : fromCourseYearSetting.getAcademicYearFees()) {
                    AcademicYearFee toAcademicYearFee = new AcademicYearFee();
                    toAcademicYearFee.setAcademicYear(academicYear);
                    toAcademicYearFee.setCourseYear(fromAcademicYearFee.getCourseYear());
                    toAcademicYearFee.setCourseYearSetting(toCourseYearSetting);
                    toAcademicYearFee.setAdmissionType(fromAcademicYearFee.getAdmissionType());
                    toAcademicYearFee.setAcademicYearFeeDetails(new HashSet());
                    for (AcademicYearFeeDetail fromAcademicYearFeeDetail : fromAcademicYearFee.getAcademicYearFeeDetails()) {
                        AcademicYearFeeDetail toAcademicYearFeeDetail = new AcademicYearFeeDetail();
                        toAcademicYearFeeDetail.setAcademicYearFee(toAcademicYearFee);
                        toAcademicYearFeeDetail.setFeeHead(fromAcademicYearFeeDetail.getFeeHead());
                        toAcademicYearFeeDetail.setAmount(fromAcademicYearFeeDetail.getAmount());
                        toAcademicYearFee.getAcademicYearFeeDetails().add(toAcademicYearFeeDetail);
                    }
                    toAcademicYearFee.setAcademicYearFeeInstallments(new HashSet());
                    for (AcademicYearFeeInstallment fromAcademicYearFeeInstallment : fromAcademicYearFee.getAcademicYearFeeInstallments()) {
                        AcademicYearFeeInstallment toAcademicYearFeeInstallment = new AcademicYearFeeInstallment();
                        toAcademicYearFeeInstallment.setInstallment(fromAcademicYearFeeInstallment.getInstallment());
                        toAcademicYearFeeInstallment.setAcademicYearFee(toAcademicYearFee);
                        toAcademicYearFeeInstallment.setDueDate(fromAcademicYearFeeInstallment.getDueDate());
                        toAcademicYearFeeInstallment.setLateFeeRule(fromAcademicYearFeeInstallment.getLateFeeRule());
                        toAcademicYearFeeInstallment.setAcademicYearFeeInstallmentDetails(new HashSet());
                        for (AcademicYearFeeInstallmentDetail fromAcademicYearFeeInstallmentDetail : fromAcademicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                            AcademicYearFeeInstallmentDetail toAcademicYearFeeInstallmentDetail = new AcademicYearFeeInstallmentDetail();
                            toAcademicYearFeeInstallmentDetail.setAcademicYearFeeInstallment(toAcademicYearFeeInstallment);
                            toAcademicYearFeeInstallmentDetail.setFeeHead(fromAcademicYearFeeInstallmentDetail.getFeeHead());
                            toAcademicYearFeeInstallmentDetail.setAmount(fromAcademicYearFeeInstallmentDetail.getAmount());
                            toAcademicYearFeeInstallment.getAcademicYearFeeInstallmentDetails().add(toAcademicYearFeeInstallmentDetail);
                        }
                        toAcademicYearFee.getAcademicYearFeeInstallments().add(toAcademicYearFeeInstallment);
                    }
                    toCourseYearSetting.getAcademicYearFees().add(toAcademicYearFee);
                }
                toAcademicYearCourse.getCourseYearSettings().add(toCourseYearSetting);
            }
            toAcademicYearCourses.add(toAcademicYearCourse);
        }
        this.academicYearFeeService.saveAcademicYearCourses(toAcademicYearCourses);
        this.academicYearBusFeeService.copyBusStopAndFee(fromAcademicYear, toAcademicYear);
        this.academicYearBusFeeService.copyBusFeeInstallments(fromAcademicYear, toAcademicYear, userId);
        this.academicYearAdmissionSchemeService.copyAdmissionSchemes(fromAcademicYear, toAcademicYear, userId);
    }

    public void publishAcademicYear(Long academicSessionId) {
        this.academicYearDAO.publishAcademicYear(academicSessionId);
    }

    public AcademicYearAdmissionSchemeService getAcademicYearAdmissionSchemeService() {
        return this.academicYearAdmissionSchemeService;
    }

    public void setAcademicYearAdmissionSchemeService(AcademicYearAdmissionSchemeService academicYearAdmissionSchemeService) {
        this.academicYearAdmissionSchemeService = academicYearAdmissionSchemeService;
    }
}
