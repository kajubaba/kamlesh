package com.narendra.sams.communication.service;

import com.narendra.sams.communication.domain.SMSSetting;

public interface SmsSettingsService {
    SMSSetting getSMSSettings(Long l, String str);

    SMSSetting getSMSSettings(String str);

    void saveSMSSettings(SMSSetting sMSSetting, Long l);
}
