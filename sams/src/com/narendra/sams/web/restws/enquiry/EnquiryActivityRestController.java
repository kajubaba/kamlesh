package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.service.EnquiryActivityService;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.enquiry.form.EnquiryActivityForm;
import com.narendra.sams.web.restws.enquiry.form.EnquiryIssueForm;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/enquiry/activity"})
public class EnquiryActivityRestController {
    @Autowired
    private EnquiryActivityService enquiryActivityService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update-status"})
    public AjaxResponse updateStatus(@RequestBody EnquiryActivityForm enquiryActivityForm) {
        this.enquiryActivityService.updateEnquiryStatus(enquiryActivityForm.getEnquiryId(), enquiryActivityForm.getNewChangedId(), enquiryActivityForm.getComments(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update-formno"})
    public AjaxResponse updateFormNo(@RequestBody EnquiryIssueForm enquiryIssueForm) {
        this.enquiryActivityService.updateFormDetails(enquiryIssueForm.getEnquiryId(), enquiryIssueForm.getFormNo(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/form-exist"})
    public AjaxResponse isFormExist(String formNo) {
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        List<Enquiry> enquiries = this.enquiryService.getEnquiruesWithFormNo(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), formNo);
        if (enquiries == null || enquiries.isEmpty()) {
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } else {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
        }
        return ajaxResponse;
    }
}
