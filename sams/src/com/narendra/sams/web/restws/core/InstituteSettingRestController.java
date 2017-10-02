package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.service.InstituteService;
import com.narendra.sams.core.service.InstituteSettingService;
import com.narendra.sams.web.auth.ApplicationCacheManager;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.InstituteSettingForm;
import com.narendra.sams.web.restws.mapper.form.InstituteSettingFormMapper;
import com.narendra.sams.web.restws.vo.AdmissionSettingsVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.EnquirySettingsVO;
import com.narendra.sams.web.restws.vo.FeeSettingsVO;
import com.narendra.sams.web.restws.vo.InstituteSettingVO;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/institute/setting"})
public class InstituteSettingRestController {
    @Autowired
    private InstituteService instituteService;
    @Autowired
    private InstituteSettingService instituteSettingService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get"})
    public InstituteSettingVO getSettings() {
        InstituteSetting instituteSetting = this.instituteSettingService.getInstituteSetting(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        Institute institute = this.instituteService.getInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        InstituteSettingVO instituteSettingVO = new InstituteSettingVO();
        instituteSettingVO.setId(instituteSetting.getId());
        AdmissionSettingsVO admissionSettingsVO = new AdmissionSettingsVO();
        if (instituteSetting.getAdmissionSettings() != null) {
            if (instituteSetting.getAdmissionSettings().getActiveAcademicYear() != null) {
                admissionSettingsVO.setAdmissionAcademicSessionId(instituteSetting.getAdmissionSettings().getActiveAcademicYear().getId());
            }
            admissionSettingsVO.setConfirmStudentIdPrefix(instituteSetting.getAdmissionSettings().getConfirmStudentIdPrefix());
            admissionSettingsVO.setRegisteredStudentIdPrefix(instituteSetting.getAdmissionSettings().getRegisteredStudentIdPrefix());
            admissionSettingsVO.setStudentIdGenerationMethod(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod());
            admissionSettingsVO.setNextStudentId(instituteSetting.getAdmissionSettings().getNextStudentId());
        }
        instituteSettingVO.setAdmissionSettings(admissionSettingsVO);
        FeeSettingsVO feeSettingsVO = new FeeSettingsVO();
        if (instituteSetting.getFeeSettings() != null) {
            feeSettingsVO.setLastFeeReceiptNo(instituteSetting.getFeeSettings().getLastFeeReceiptNo());
            feeSettingsVO.setFeeReceiptStartNo(instituteSetting.getFeeSettings().getFeeReceiptStartNo());
            feeSettingsVO.setReceiptType(instituteSetting.getFeeSettings().getReceiptType());
            if (instituteSetting.getFeeSettings().getIsFeeReceiptNoInCont() == null) {
                feeSettingsVO.setIsFeeReceiptNoInCont(Boolean.FALSE);
            } else {
                feeSettingsVO.setIsFeeReceiptNoInCont(instituteSetting.getFeeSettings().getIsFeeReceiptNoInCont());
            }
        }
        instituteSettingVO.setFeeSettings(feeSettingsVO);
        EnquirySettingsVO enquirySettingsVO = new EnquirySettingsVO();
        if (instituteSetting.getEnquirySettings() != null) {
            if (instituteSetting.getEnquirySettings().getActiveAcademicYear() != null) {
                enquirySettingsVO.setEnquiryAcademicSessionId(instituteSetting.getEnquirySettings().getActiveAcademicYear().getId());
            }
            enquirySettingsVO.setEnableCompetitiveExam(instituteSetting.getEnquirySettings().getEnableCompetitiveExam());
            enquirySettingsVO.setEnableDuplicateEnq(instituteSetting.getEnquirySettings().getEnableDuplicateEnq());
            enquirySettingsVO.setEnableInternalExam(instituteSetting.getEnquirySettings().getEnableInternalExam());
            enquirySettingsVO.setEnablePreviousClass(instituteSetting.getEnquirySettings().getEnablePreviousClass());
            enquirySettingsVO.setEnableRegistered(instituteSetting.getEnquirySettings().getEnableRegistered());
            enquirySettingsVO.setFormFee(instituteSetting.getEnquirySettings().getFormFee());
            enquirySettingsVO.setNextFormReceiptNo(instituteSetting.getEnquirySettings().getNextFormReceiptNo());
        }
        instituteSettingVO.setEnquirySettings(enquirySettingsVO);
        instituteSettingVO.setInstituteName(institute.getName());
        instituteSettingVO.setLine1(institute.getAddress());
        instituteSettingVO.setLine2(institute.getLine2());
        instituteSettingVO.setAffiliationNo(institute.getAffiliationNo());
        if (instituteSetting.getIsIdGenerationMethodSame() == null) {
            instituteSettingVO.setIsIdGenerationMethodSame(Boolean.TRUE.toString());
        } else {
            instituteSettingVO.setIsIdGenerationMethodSame(instituteSetting.getIsIdGenerationMethodSame().toString());
        }
        return instituteSettingVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveSettings(@RequestBody InstituteSettingForm instituteSetting) {
        Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId();
        this.instituteSettingService.updateInstituteSetting(InstituteSettingFormMapper.prepareInstitueSettingsDomain(instituteSetting), LoggedinUserAssistant.getLoggedInUserId());
        this.instituteService.updateInstituteDetails(InstituteSettingFormMapper.prepareInstitueDomain(instituteSetting, instituteId));
        InstituteSetting setting = this.instituteSettingService.getInstituteSetting(instituteId);
        System.out.println(setting.getAdmissionSettings().getActiveAcademicYear());
        System.out.println(setting.getEnquirySettings().getActiveAcademicYear());
        ApplicationCacheManager.setInstituteSetting(this.webApplicationContext, setting);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }
}
