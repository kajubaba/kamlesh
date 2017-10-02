package com.narendra.sams.web.restws.core;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.service.AcademicYearAdmissionSchemeService;
import com.narendra.sams.core.service.AdmissionSchemeService;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.AcademicSessionAdmissionSchemeForm;
import com.narendra.sams.web.restws.mapper.vo.AcademicSessionAdmissionSchemeVOMapper;
import com.narendra.sams.web.restws.mapper.vo.AdmissionSchemeVOMapper;
import com.narendra.sams.web.restws.vo.AcademicSessionAdmissionSchemeVO;
import com.narendra.sams.web.restws.vo.AdmissionSchemeDetailVO;
import com.narendra.sams.web.restws.vo.AdmissionSchemeVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
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
@RequestMapping({"/ws/academic-session/admission-scheme"})
public class AcademicSessionAdmissionSchemeRestController {
    @Autowired
    private AcademicYearAdmissionSchemeService academicYearAdmissionSchemeService;
    @Autowired
    private AdmissionListService admissionListService;
    @Autowired
    private AdmissionSchemeService admissionSchemeService;
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/{academicSessionId}"})
    public List<AcademicSessionAdmissionSchemeVO> getAdmissionSchemes(@PathVariable Long academicSessionId) {
        return AcademicSessionAdmissionSchemeVOMapper.prepareAcademicSessionAdmissionSchemeVOs(this.academicYearAdmissionSchemeService.getAdmissionSchemes(academicSessionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/unassigned/{academicSessionId}"})
    public List<AdmissionSchemeVO> getUnAssignedBusStops(@PathVariable Long academicSessionId) {
        return AdmissionSchemeVOMapper.prepareAdmissionSchemeVO(this.academicYearAdmissionSchemeService.getUnAssignedAdmissionSchemes(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId(), academicSessionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/assign"})
    public AjaxResponse assignAdmissionSchemes(@RequestBody AcademicSessionAdmissionSchemeForm academicSessionAdmissionSchemeForm) {
        this.academicYearAdmissionSchemeService.addAdmissionSchemes(academicSessionAdmissionSchemeForm.getAdmissionSchemeIds(), academicSessionAdmissionSchemeForm.getAcademicSessionId(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{admissionSchemeId}"})
    public AcademicSessionAdmissionSchemeVO getAdmissionScheme(@PathVariable Long admissionSchemeId) {
        return AcademicSessionAdmissionSchemeVOMapper.prepareAcademicSessionAdmissionSchemeVO(this.academicYearAdmissionSchemeService.getAcademicYearAdmissionScheme(admissionSchemeId), this.feeHeadService.getAllActiveFeeHeads(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/scheme-detail"})
    public AjaxResponse saveSchemeDetails(@RequestBody AcademicSessionAdmissionSchemeVO admissionScheme) {
        List<AcademicSessionAdmisionSchemeDetail> schemeDetails = null;
        if (admissionScheme.getAdmissionSchemeDetails() != null) {
            schemeDetails = new ArrayList();
            for (AdmissionSchemeDetailVO admissionSchemeDetailVO : admissionScheme.getAdmissionSchemeDetails()) {
                AcademicSessionAdmisionSchemeDetail academicSessionAdmisionSchemeDetail = new AcademicSessionAdmisionSchemeDetail();
                academicSessionAdmisionSchemeDetail.setId(admissionSchemeDetailVO.getId());
                academicSessionAdmisionSchemeDetail.setDiscount(admissionSchemeDetailVO.getDiscount());
                FeeHead feeHead = new FeeHead();
                feeHead.setId(admissionSchemeDetailVO.getFeeHeadId());
                academicSessionAdmisionSchemeDetail.setFeeHead(feeHead);
                schemeDetails.add(academicSessionAdmisionSchemeDetail);
            }
        }
        this.academicYearAdmissionSchemeService.saveSchemeDetails(schemeDetails, admissionScheme.getId());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{admissionSchemeId}"})
    public AjaxResponse unAssignAdmissionScheme(@PathVariable Long admissionSchemeId) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        AcademicYearAdmissionScheme academicYearAdmissionScheme = this.academicYearAdmissionSchemeService.getAcademicYearAdmissionScheme(admissionSchemeId);
        List<ClassHistory> classHistories = this.admissionListService.getUnderSchemeAdmissions(academicYearAdmissionScheme.getAcademicYear().getId(), academicYearAdmissionScheme.getAdmissionScheme().getId());
        if (classHistories == null || classHistories.isEmpty()) {
            this.academicYearAdmissionSchemeService.deleteAdmissionScheme(admissionSchemeId);
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } else {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
        }
        return ajaxResponse;
    }
}
