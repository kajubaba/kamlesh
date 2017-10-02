package com.narendra.sams.web.restws.communication.mapper;

import com.narendra.sams.communication.domain.InstituteSMSProvider;
import com.narendra.sams.communication.domain.SMSSetting;
import com.narendra.sams.web.restws.communication.vo.SMSProviderVO;
import com.narendra.sams.web.restws.communication.vo.SMSSettingVO;

public class SMSSettingVOMapper {
    public static SMSSettingVO prepareSMSSettingVO(SMSSetting smsSetting) {
        SMSSettingVO smsSettingVO = new SMSSettingVO();
        if (smsSetting != null) {
            smsSettingVO.setId(smsSetting.getId());
            smsSettingVO.setIsEnabled(smsSetting.getIsEnabled());
            smsSettingVO.setNotificationType(smsSetting.getNotificationType());
            smsSettingVO.setSendToFather(smsSetting.getSendToFather());
            smsSettingVO.setSendToMother(smsSetting.getSendToMother());
            smsSettingVO.setSendToStudent(smsSetting.getSendToStudent());
        }
        return smsSettingVO;
    }

    public static SMSProviderVO prepareSMSProviderVO(InstituteSMSProvider instituteSMSProvider) {
        SMSProviderVO smsProviderVO = new SMSProviderVO();
        if (instituteSMSProvider != null) {
            smsProviderVO.setId(instituteSMSProvider.getId());
            smsProviderVO.setAuthKey(instituteSMSProvider.getAuthKey());
            smsProviderVO.setSenderId(instituteSMSProvider.getSenderId());
            smsProviderVO.setIsEnabled(instituteSMSProvider.getIsEnabled());
            smsProviderVO.setSmsProviderName(instituteSMSProvider.getSmsProviderName());
            smsProviderVO.setUrl(instituteSMSProvider.getUrl());
        }
        return smsProviderVO;
    }
}
