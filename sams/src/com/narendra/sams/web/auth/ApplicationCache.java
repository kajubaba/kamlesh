package com.narendra.sams.web.auth;

import com.narendra.sams.core.domain.InstituteSetting;
import java.util.Map;

public class ApplicationCache {
    private Map<Long, InstituteSetting> instituteSettings;

    public Map<Long, InstituteSetting> getInstituteSettings() {
        return this.instituteSettings;
    }

    public void setInstituteSettings(Map<Long, InstituteSetting> instituteSettings) {
        this.instituteSettings = instituteSettings;
    }
}
