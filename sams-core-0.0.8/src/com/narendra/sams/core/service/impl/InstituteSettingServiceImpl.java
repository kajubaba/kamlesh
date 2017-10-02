package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.InstituteSettingDAO;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.service.InstituteSettingService;

public class InstituteSettingServiceImpl implements InstituteSettingService {
    private InstituteSettingDAO instituteSettingDAO;

    public InstituteSettingDAO getInstituteSettingDAO() {
        return this.instituteSettingDAO;
    }

    public void setInstituteSettingDAO(InstituteSettingDAO instituteSettingDAO) {
        this.instituteSettingDAO = instituteSettingDAO;
    }

    public InstituteSetting getInstituteSetting(Long instituteId) {
        return this.instituteSettingDAO.getInstituteSetting(instituteId);
    }

    public void updateInstituteSetting(InstituteSetting instituteSetting, Long userId) {
        this.instituteSettingDAO.updateInstituteSetting(instituteSetting, userId);
    }
}
