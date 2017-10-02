package com.narendra.sams.web.auth;

import com.narendra.sams.core.domain.InstituteSetting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.context.WebApplicationContext;

public class ApplicationCacheManager {
    private static final String appCacheName = "applicationCache";

    public static InstituteSetting getInstituteSetting(WebApplicationContext webApplicationContext, Long instituteId) {
        ApplicationCache applicationCache = (ApplicationCache) webApplicationContext.getBean(appCacheName);
        if (applicationCache.getInstituteSettings() != null) {
            return (InstituteSetting) applicationCache.getInstituteSettings().get(instituteId);
        }
        return null;
    }

    public static void setInstituteSetting(WebApplicationContext webApplicationContext, InstituteSetting instituteSetting) {
        ApplicationCache applicationCache = (ApplicationCache) webApplicationContext.getBean(appCacheName);
        if (applicationCache.getInstituteSettings() == null) {
            applicationCache.setInstituteSettings(new HashMap());
        }
        synchronized (applicationCache) {
            applicationCache.getInstituteSettings().put(instituteSetting.getInstitute().getId(), instituteSetting);
        }
    }

    public static void setInstituteSettings(WebApplicationContext webApplicationContext, List<InstituteSetting> instituteSettings) {
        ApplicationCache applicationCache = (ApplicationCache) webApplicationContext.getBean(appCacheName);
        if (applicationCache.getInstituteSettings() == null) {
            applicationCache.setInstituteSettings(new HashMap());
        }
        if (instituteSettings != null) {
            for (InstituteSetting is : instituteSettings) {
                synchronized (applicationCache) {
                    applicationCache.getInstituteSettings().put(is.getInstitute().getId(), is);
                }
            }
        }
    }

    public static Map<Long, InstituteSetting> getAllInstitutes(WebApplicationContext webApplicationContext) {
        return ((ApplicationCache) webApplicationContext.getBean(appCacheName)).getInstituteSettings();
    }
}
