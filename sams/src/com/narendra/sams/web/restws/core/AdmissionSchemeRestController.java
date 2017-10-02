package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.AdmissionSchemeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.AdmissionSchemeForm;
import com.narendra.sams.web.restws.mapper.form.AdmissionSchemeFormMapper;
import com.narendra.sams.web.restws.mapper.vo.AdmissionSchemeVOMapper;
import com.narendra.sams.web.restws.vo.AdmissionSchemeVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/admissionscheme"})
public class AdmissionSchemeRestController {
    @Autowired
    private AdmissionSchemeService admissionSchemeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<AdmissionSchemeVO> getInstituteAdmissionSchemes() {
        return AdmissionSchemeVOMapper.prepareAdmissionSchemeVO(this.admissionSchemeService.getAdmissionSchemes(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{admissionSchemeId}"})
    public AdmissionSchemeVO getAdmissionScheme(@PathVariable Long admissionSchemeId) {
        return AdmissionSchemeVOMapper.prepareAdmissionSchemeVO(this.admissionSchemeService.getAdmissionScheme(admissionSchemeId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveAdmissionSchemes(@RequestBody AdmissionSchemeForm admissionSchemeForm) {
        Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId();
        Institute institute = new Institute();
        institute.setId(instituteId);
        AdmissionScheme admissionScheme = AdmissionSchemeFormMapper.prepareAdmissionSchemeDomain(admissionSchemeForm);
        admissionScheme.setInstitute(institute);
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            ajaxResponse.setGeneratedId(this.admissionSchemeService.saveAdmissionScheme(admissionScheme, LoggedinUserAssistant.getLoggedInUserId()));
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/academicsession/{academicSessionId}"})
    public List<AdmissionSchemeVO> getAcademicSessionAdmissionSchemes(@PathVariable Long academicSessionId) {
        return AdmissionSchemeVOMapper.prepareAdmissionSchemeVO(this.admissionSchemeService.getAdmissionSchemes(academicSessionId));
    }
}
