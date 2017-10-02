package com.narendra.sams.web.institute;

import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.core.service.InstituteService;
import com.narendra.sams.web.auth.UserSession;
import com.narendra.sams.web.auth.UserSessionManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/institute"})
public class InstituteController {
    @Autowired
    AcademicYearService academicYearService;
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private InstituteService instituteService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/home"})
    public String instituteHome(Model model) {
        Institute institute = this.instituteService.getDefaultInstitute(Long.valueOf(1));
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(institute.getId());
        FeeHead lateFeeHead = this.feeHeadService.getLateFeeHead(institute.getId());
        List<Institute> institutes = this.instituteService.getInstitutes(Long.valueOf(1));
        UserSessionManager.getUserSession(this.webApplicationContext).setWorkingInstituteId(institute.getId());
        UserSession userSession = UserSessionManager.getUserSession(this.webApplicationContext);
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
        return "home";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/ci/{instituteId}"})
    public String changeWorkingInstitue(@PathVariable Long instituteId, Model model) {
        List<Institute> institutes = this.instituteService.getInstitutes(Long.valueOf(1));
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(instituteId);
        FeeHead lateFeeHead = this.feeHeadService.getLateFeeHead(instituteId);
        UserSessionManager.getUserSession(this.webApplicationContext).setWorkingInstituteId(instituteId);
        UserSession userSession = UserSessionManager.getUserSession(this.webApplicationContext);
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
        return "home";
    }
}
