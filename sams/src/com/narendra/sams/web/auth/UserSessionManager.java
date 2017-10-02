package com.narendra.sams.web.auth;

import com.narendra.sams.core.domain.InstituteSetting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.context.WebApplicationContext;

public class UserSessionManager {
    public static UserSession getUserSession(WebApplicationContext webApplicationContext) {
        UserSession userSession = (UserSession) webApplicationContext.getBean("userSession");
        userSession.setInstituteSetting(ApplicationCacheManager.getInstituteSetting(webApplicationContext, userSession.getWorkingInstituteId()));
        Map<Long, InstituteSetting> map = ApplicationCacheManager.getAllInstitutes(webApplicationContext);
        List<InstituteSetting> institutes = new ArrayList();
        if (map != null) {
            for (Long id : map.keySet()) {
                institutes.add((InstituteSetting) map.get(id));
            }
        }
        userSession.setInstitutes(institutes);
        return (UserSession) webApplicationContext.getBean("userSession");
    }
}
