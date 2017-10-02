package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.service.AdmissionRenewalService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AdmissionCountVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.admission.vo.StudentVO;
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
@RequestMapping({"/ws/admision/pendingRenewal"})
public class AdmissionRenewalRestController {
    @Autowired
    private AdmissionRenewalService admissionRenewalService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/count"})
    public AdmissionCountVO getAdmissionCountPendingForRenewal() {
        long admisions = this.admissionRenewalService.getAdmissionCountPendingForRenewal(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()).longValue();
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(Long.valueOf(admisions));
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<StudentVO> defaultView(Model model) {
        return StudentListVOMaker.prepareStudentToDisplay(this.admissionRenewalService.getAdmissionsPendingForRenewal(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/list"})
    public List<AcademicYearClassAdmissionCount> classWiseView(Model model) {
        return this.admissionRenewalService.getClasswiseAdmissionsPendingForRenewal(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/class/{academicYearClassId}"})
    public List<StudentVO> busStopWiseNewAdmissionCount(@PathVariable Long academicYearClassId) {
        return StudentListVOMaker.prepareStudentToDisplay(this.admissionRenewalService.getClassAdmissionsPendingForRenewal(academicYearClassId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/isEligible/{studentId}"})
    public AjaxSuccessResponse isEligibleForRenewal(@PathVariable Long studentId) {
        Boolean isEligible = this.admissionRenewalService.getRenewalEligibility(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId(), studentId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(isEligible.toString());
        return ajaxSuccessResponse;
    }
}
