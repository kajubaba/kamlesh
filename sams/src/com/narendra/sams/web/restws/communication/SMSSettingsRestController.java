package com.narendra.sams.web.restws.communication;

import com.narendra.sams.communication.domain.InstituteSMSProvider;
import com.narendra.sams.communication.domain.SMSSetting;
import com.narendra.sams.communication.service.SmsProviderService;
import com.narendra.sams.communication.service.SmsSettingsService;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.communication.form.SMSProviderForm;
import com.narendra.sams.web.restws.communication.form.SMSSettingForm;
import com.narendra.sams.web.restws.communication.mapper.SMSSettingVOMapper;
import com.narendra.sams.web.restws.communication.vo.SMSProviderVO;
import com.narendra.sams.web.restws.communication.vo.SMSSettingVO;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/settings/sms"})
public class SMSSettingsRestController {
    @Autowired
    private SmsProviderService smsProviderService;
    @Autowired
    private SmsSettingsService smsSettingsService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/provider"})
    public SMSProviderVO getSMSProvider() {
        InstituteSMSProvider instituteSMSProvider = this.smsProviderService.getSMSProvider(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        SMSProviderVO smsProviderVO = SMSSettingVOMapper.prepareSMSProviderVO(instituteSMSProvider);
        if (instituteSMSProvider == null) {
            smsProviderVO.setSmsProviderName("MSG91");
        }
        return smsProviderVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/on-fee-collection"})
    public SMSSettingVO getOnFeeCollectionSetting() {
        SMSSetting smsSetting = this.smsSettingsService.getSMSSettings(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId(), SMSSetting.FEE_DEPOSIT_MSG);
        SMSSettingVO smsSettingVO = SMSSettingVOMapper.prepareSMSSettingVO(smsSetting);
        if (smsSetting == null) {
            smsSettingVO.setNotificationType(SMSSetting.FEE_DEPOSIT_MSG);
        }
        return smsSettingVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/on-birth-day"})
    public SMSSettingVO getOnBirthdaySetting() {
        SMSSetting smsSetting = this.smsSettingsService.getSMSSettings(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId(), SMSSetting.BIRTH_DAY_WISH_MSG);
        SMSSettingVO smsSettingVO = SMSSettingVOMapper.prepareSMSSettingVO(smsSetting);
        if (smsSetting == null) {
            smsSettingVO.setNotificationType(SMSSetting.BIRTH_DAY_WISH_MSG);
        }
        return smsSettingVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveSMSProvider(@RequestBody SMSSettingForm smsSettingForm) {
        SMSSetting smsSetting = new SMSSetting();
        smsSetting.setId(smsSettingForm.getId());
        smsSetting.setIsEnabled(smsSettingForm.getIsEnabled());
        smsSetting.setNotificationType(smsSettingForm.getNotificationType());
        smsSetting.setSendToFather(smsSettingForm.getSendToFather());
        smsSetting.setSendToMother(smsSettingForm.getSendToMother());
        smsSetting.setSendToStudent(smsSettingForm.getSendToStudent());
        if (smsSetting.getId() == null) {
            Institute institute = new Institute();
            institute.setId(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
            smsSetting.setInstitute(institute);
        }
        this.smsSettingsService.saveSMSSettings(smsSetting, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/sms-provider"})
    public AjaxSuccessResponse saveSMSSetting(@RequestBody SMSProviderForm smsProviderForm) {
        InstituteSMSProvider instituteSMSProvider = new InstituteSMSProvider();
        instituteSMSProvider.setId(smsProviderForm.getId());
        instituteSMSProvider.setAuthKey(smsProviderForm.getAuthKey());
        instituteSMSProvider.setSenderId(smsProviderForm.getSenderId());
        instituteSMSProvider.setIsEnabled(smsProviderForm.getIsEnabled());
        instituteSMSProvider.setSmsProviderName(smsProviderForm.getSmsProviderName());
        instituteSMSProvider.setUrl(smsProviderForm.getUrl());
        if (instituteSMSProvider.getId() == null) {
            Institute institute = new Institute();
            institute.setId(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
            instituteSMSProvider.setInstitute(institute);
        }
        this.smsProviderService.saveSMSProvider(instituteSMSProvider, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }
}
