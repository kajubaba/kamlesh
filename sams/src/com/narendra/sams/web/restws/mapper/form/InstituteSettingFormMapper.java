package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AdmissionSettings;
import com.narendra.sams.core.domain.EnquirySettings;
import com.narendra.sams.core.domain.FeeSettings;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.web.restws.form.InstituteSettingForm;
import org.springframework.beans.BeanUtils;

public class InstituteSettingFormMapper {
    public static InstituteSetting prepareInstitueSettingsDomain(InstituteSettingForm instituteSettingForm) {
        InstituteSetting instituteSetting = new InstituteSetting();
        instituteSetting.setId(instituteSettingForm.getId());
        AdmissionSettings admissionSettings = new AdmissionSettings();
        BeanUtils.copyProperties(instituteSettingForm.getAdmissionSettings(), admissionSettings);
        FeeSettings feeSettings = new FeeSettings();
        BeanUtils.copyProperties(instituteSettingForm.getFeeSettings(), feeSettings);
        EnquirySettings enquirySettings = new EnquirySettings();
        BeanUtils.copyProperties(instituteSettingForm.getEnquirySettings(), enquirySettings);
        instituteSetting.setAdmissionSettings(admissionSettings);
        instituteSetting.setEnquirySettings(enquirySettings);
        instituteSetting.setFeeSettings(feeSettings);
        if (instituteSettingForm.getAdmissionSettings().getAdmissionAcademicSessionId() != null) {
            AcademicYear academicYear = new AcademicYear();
            academicYear.setId(instituteSettingForm.getAdmissionSettings().getAdmissionAcademicSessionId());
            instituteSetting.getAdmissionSettings().setActiveAcademicYear(academicYear);
        }
        if (instituteSettingForm.getEnquirySettings().getEnquiryAcademicSessionId() != null) {
        	AcademicYear academicYear = new AcademicYear();
            academicYear.setId(instituteSettingForm.getEnquirySettings().getEnquiryAcademicSessionId());
            instituteSetting.getEnquirySettings().setActiveAcademicYear(academicYear);
        }
        instituteSetting.setIsIdGenerationMethodSame(instituteSettingForm.getIsIdGenerationMethodSame());
        return instituteSetting;
    }

    public static Institute prepareInstitueDomain(InstituteSettingForm instituteSettingForm, Long instituteId) {
        Institute institute = new Institute();
        institute.setId(instituteId);
        institute.setName(instituteSettingForm.getInstituteName());
        institute.setAddress(instituteSettingForm.getLine1());
        institute.setLine2(instituteSettingForm.getLine2());
        institute.setAffiliationNo(instituteSettingForm.getAffiliationNo());
        return institute;
    }
}
