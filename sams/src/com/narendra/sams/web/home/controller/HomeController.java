package com.narendra.sams.web.home.controller;

import com.narendra.sams.core.address.service.StopApplicationService;
import com.narendra.sams.core.domain.Company;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.core.service.InstituteService;
import com.narendra.sams.web.auth.UserSession;
import com.narendra.sams.web.auth.UserSessionManager;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/home"})
public class HomeController {
    @Autowired
    AcademicYearService academicYearService;
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private InstituteService instituteService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private StopApplicationService stopApplicationService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {""})
    public String instituteHome(Model model) {
        UserSession userSession = UserSessionManager.getUserSession(this.webApplicationContext);
        if (userSession.getWorkingInstituteId() == null) {
            Institute institute = this.instituteService.getDefaultInstitute(Company.DEFAULT_COMPANY);
            FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(institute.getId());
            FeeHead lateFeeHead = this.feeHeadService.getLateFeeHead(institute.getId());
            List<Institute> institutes = this.instituteService.getInstitutes(Long.valueOf(1));
            userSession.setWorkingInstituteId(institute.getId());
            userSession = UserSessionManager.getUserSession(this.webApplicationContext);
            userSession.setBusFeeHead(busFeeHead);
            userSession.setLateFeeHead(lateFeeHead);
            if (institutes != null && !institutes.isEmpty()) {
                for (Institute inst : institutes) {
                    if (!userSession.getWorkingInstituteId().equals(inst.getId())) {
                        userSession.setNonWorkingInstitute(inst);
                        break;
                    }
                }
            }
        }
        model.addAttribute("warningMessage", this.stopApplicationService.getWarningMessage());
        loadcolorsInContext();
        return "index";
    }

    public void loadcolorsInContext() {
        if (this.servletContext.getAttribute("colors") == null) {
            List<String> colors = new ArrayList();
            colors.add("AFD8F8");
            colors.add("F6BD0F");
            colors.add("8BBA00");
            colors.add("FF8E46");
            colors.add("008E8E");
            colors.add("D64646");
            colors.add("8E468E");
            colors.add("588526");
            colors.add("B3AA00");
            colors.add("008ED6");
            colors.add("9D080D");
            colors.add("A186BE");
            this.servletContext.setAttribute("colors", colors);
        }
    }
}
