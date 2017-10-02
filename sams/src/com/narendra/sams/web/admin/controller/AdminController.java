package com.narendra.sams.web.admin.controller;

import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.web.auth.UserSessionManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    @Autowired
    private AcademicYearService academicYearService;
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping({""})
    public String getDefaultAdminView(Model model) {
        this.logger.info("Preparing academic year listing view");
        model.addAttribute("academicYears", this.academicYearService.getAllAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
        return "admin_view";
    }
}
