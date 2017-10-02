package com.narendra.sams.communication.service.impl;

import com.narendra.sams.communication.dao.SmsSettingsDAO;
import com.narendra.sams.communication.domain.SMSSetting;
import com.narendra.sams.communication.service.SmsSettingsService;

public class SmsSettingsServiceImpl implements SmsSettingsService {
    private SmsSettingsDAO smsSettingsDAO;

    public SmsSettingsDAO getSmsSettingsDAO() {
        return this.smsSettingsDAO;
    }

    public void setSmsSettingsDAO(SmsSettingsDAO smsSettingsDAO) {
        this.smsSettingsDAO = smsSettingsDAO;
    }

    public SMSSetting getSMSSettings(Long instituteId, String settingsType) {
        if (instituteId == null || settingsType == null) {
            return null;
        }
        return this.smsSettingsDAO.getSMSSettings(instituteId, settingsType);
    }

    public void saveSMSSettings(SMSSetting smsSetting, Long userId) {
        if (smsSetting != null) {
            if (smsSetting.getId() == null) {
                this.smsSettingsDAO.addSMSSettings(smsSetting, userId);
            } else {
                this.smsSettingsDAO.updateSMSSettings(smsSetting, userId);
            }
        }
    }

    public SMSSetting getSMSSettings(String settingsType) {
        if (settingsType == null) {
            return null;
        }
        return this.smsSettingsDAO.getSMSSettings(settingsType);
    }
}
