package com.narendra.sams.web.utils;

import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.web.auth.ApplicationCacheManager;
import com.narendra.uuc.core.domain.Application;
import com.narendra.uuc.core.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class ServletContextManager {
    private static Application application;
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private UserService userService;
    @Autowired
    WebApplicationContext webApplicationContext;

    @PostConstruct
    public void intitializeConfiguration() {
        application = this.userService.getApplicationByName("SAMS000");
        ApplicationCacheManager.setInstituteSettings(this.webApplicationContext, this.academicYearService.getInstituteSettings());
    }

    public static Application getApplication() {
        return application;
    }
}
