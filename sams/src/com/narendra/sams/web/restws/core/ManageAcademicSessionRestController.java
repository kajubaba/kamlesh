package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.AcademicSessionForm;
import com.narendra.sams.web.restws.mapper.form.AcademicSessionFormMapper;
import com.narendra.sams.web.restws.mapper.vo.AcademicSessionVOMapper;
import com.narendra.sams.web.restws.vo.AcademicSessionVO;
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
@RequestMapping({"/ws/academic-session"})
public class ManageAcademicSessionRestController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveAcademicSession(@RequestBody AcademicSessionForm academicSessionForm) {
        AcademicYear academicYear = AcademicSessionFormMapper.prepareAcademicSessionDomain(academicSessionForm);
        academicYear.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Long academicYearId = this.academicYearService.saveAcademicYear(academicYear, LoggedinUserAssistant.getLoggedInUserId());
            if (academicSessionForm.getImportSettingsFrom() != null) {
                this.academicYearService.copyAcademicYear(academicSessionForm.getImportSettingsFrom(), academicYearId, LoggedinUserAssistant.getLoggedInUserId());
            }
            ajaxResponse.setGeneratedId(academicYearId);
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{feeHeadId}"})
    public void deleteAcademicSession() {
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{academicYearId}"})
    public AcademicSessionVO getAcademicSession(@PathVariable Long academicYearId) {
        return AcademicSessionVOMapper.prepareAcademicSessionVO(this.academicYearService.getAcademicYearById(academicYearId.longValue()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<AcademicSessionVO> listAcademicSessions() {
        return AcademicSessionVOMapper.prepareAcademicSessionVOs(this.academicYearService.getAllAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list-active"})
    public List<AcademicSessionVO> listActiveAcademicSessions() {
        return AcademicSessionVOMapper.prepareAcademicSessionVOs(this.academicYearService.getActiveAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/publish/{academicSessionId}"})
    public AjaxResponse pulishAcademicSession(@PathVariable Long academicSessionId) {
        this.academicYearService.publishAcademicYear(academicSessionId);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }
}
