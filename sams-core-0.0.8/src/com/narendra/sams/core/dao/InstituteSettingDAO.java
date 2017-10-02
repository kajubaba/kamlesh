package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.InstituteSetting;

public interface InstituteSettingDAO {
    InstituteSetting getInstituteSetting(Long l);

    void updateInstituteSetting(InstituteSetting instituteSetting, Long l);
}
