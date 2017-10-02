package com.narendra.sams.web.enquiry.controller;

import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryActivity;
import com.narendra.sams.enquiry.service.EnquiryActivityService;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.enquiry.service.EnquiryStatusService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import com.narendra.sams.web.utils.ServletContextManager;
import com.narendra.uuc.core.service.UserService;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/enquiry/activity"})
public class EnquiryActivityController {
    @Autowired
    private EnquiryActivityService enquiryActivityService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private EnquiryStatusService enquiryStatusService;
    protected final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/viewChangeOwner"})
    public String changeOwnerView(Model model) {
        model.addAttribute("users", this.userService.getActiveUsers(ServletContextManager.getApplication().getId()));
        return "popup_change_enq_owner";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/changeOwner"})
    public String changeOwner(Long[] enquiryIds, Long newOwnerId, String comments) {
        if (enquiryIds != null) {
            this.enquiryActivityService.updateEnquiryOwner(enquiryIds, newOwnerId, comments, LoggedinUserAssistant.getLoggedInUserId());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/viewChangeAssignee"})
    public String changeAssigneeView(Model model) {
        model.addAttribute("users", this.userService.getActiveUsers(ServletContextManager.getApplication().getId()));
        return "popup_change_enq_assignee";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/changeAssignee"})
    public String changeAssignee(Long[] enquiryIds, Long newAssigneeId, String comments) {
        if (enquiryIds != null) {
            this.enquiryActivityService.updateEnquiryAssignee(enquiryIds, newAssigneeId, comments, LoggedinUserAssistant.getLoggedInUserId());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/viewChangeStatus"})
    public String changeStatusView(Model model) {
        model.addAttribute("enqStatusList", this.enquiryStatusService.getAllActiveEnquiryStatus(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
        return "enquiry_change_status_popup";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/changeStatus"})
    public String changeStatus(Long[] enquiryIds, Long newStatusId, String comments) {
        if (enquiryIds != null) {
            this.enquiryActivityService.updateEnquiryStatus(enquiryIds, newStatusId, comments, LoggedinUserAssistant.getLoggedInUserId());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/viewFollow"})
    public String followEnquiry(Model model) {
        return "popup_enq_followup";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/follow"})
    public String followEnquiry(Long[] enquiryIds, String followupActivity, String comments) {
        if (enquiryIds != null) {
            this.enquiryActivityService.followEnquiry(enquiryIds, followupActivity, comments, LoggedinUserAssistant.getLoggedInUserId());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/log"})
    public String viewEnquiryLogs(Long enquiryId, Model model) {
        List<EnquiryActivity> enqActivities = this.enquiryActivityService.getEnquiryActivities(enquiryId);
        Enquiry enquiry = this.enquiryService.getEnquiry(enquiryId);
        model.addAttribute("enqActivities", enqActivities);
        model.addAttribute("enquiry", enquiry);
        return "enquiry_activities";
    }
}
