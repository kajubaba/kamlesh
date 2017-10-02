package com.narendra.sams.web.restws.common;

import com.narendra.sams.core.domain.Company;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.core.service.InstituteService;
import com.narendra.sams.core.service.InstituteSettingService;
import com.narendra.sams.web.auth.ApplicationCacheManager;
import com.narendra.sams.web.auth.UserSession;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.common.vo.InstituteVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/institute"})
public class InstituteRestController {
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private InstituteService instituteService;
    @Autowired
    private InstituteSettingService instituteSettingService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<InstituteVO> companyInstitutes() {
        List<InstituteVO> instituteVOs = new ArrayList();
        List<Institute> institutes = this.instituteService.getInstitutes(Company.DEFAULT_COMPANY);
        if (institutes != null) {
            for (Institute institute : institutes) {
                InstituteVO instituteVO = new InstituteVO();
                instituteVO.setId(institute.getId());
                instituteVO.setName(institute.getName());
                if (UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId().equals(institute.getId())) {
                    instituteVO.setIsWorking(Boolean.valueOf(true));
                } else {
                    instituteVO.setIsWorking(Boolean.valueOf(false));
                }
                instituteVOs.add(instituteVO);
            }
        }
        return instituteVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/switchinstitute/{instituteId}"})
    public AjaxSuccessResponse changeWorkingInstitue(@PathVariable Long instituteId, Model model) {
        FeeHead busFeeHead = this.feeHeadService.getBusFeeHead(instituteId);
        FeeHead lateFeeHead = this.feeHeadService.getLateFeeHead(instituteId);
        UserSessionManager.getUserSession(this.webApplicationContext).setWorkingInstituteId(instituteId);
        UserSession userSession = UserSessionManager.getUserSession(this.webApplicationContext);
        userSession.setBusFeeHead(busFeeHead);
        userSession.setLateFeeHead(lateFeeHead);
        ApplicationCacheManager.setInstituteSetting(this.webApplicationContext, this.instituteSettingService.getInstituteSetting(instituteId));
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }
}
