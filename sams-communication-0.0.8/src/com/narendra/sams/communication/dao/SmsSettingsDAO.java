package com.narendra.sams.communication.dao;

import com.narendra.sams.communication.domain.SMSSetting;

public interface SmsSettingsDAO {
    void addSMSSettings(SMSSetting sMSSetting, Long l);

    SMSSetting getSMSSettings(Long l, String str);

    SMSSetting getSMSSettings(String str);

    void updateSMSSettings(SMSSetting sMSSetting, Long l);
}
