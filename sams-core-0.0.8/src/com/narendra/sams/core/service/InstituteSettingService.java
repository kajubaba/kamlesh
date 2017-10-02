package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.InstituteSetting;

public interface InstituteSettingService {
    InstituteSetting getInstituteSetting(Long l);

    void updateInstituteSetting(InstituteSetting instituteSetting, Long l);
}
