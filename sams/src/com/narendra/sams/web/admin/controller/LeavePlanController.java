package com.narendra.sams.web.admin.controller;

import com.narendra.sams.core.domain.LeavePlan;
import com.narendra.sams.core.service.LeavePlanService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/admin/leavePlan"})
public class LeavePlanController {
    @Autowired
    private LeavePlanService leavePlanService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public String getLeavePlans(Model model) {
        model.addAttribute("leavePlans", this.leavePlanService.getAllLeavePlans(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
        return "list_leave_plans";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/new"})
    public String newLeavePlan() {
        return "admin_leave_plan";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public String addLeavePlan(LeavePlan leavePlan) {
        this.leavePlanService.addLeavePlan(leavePlan, LoggedinUserAssistant.getLoggedInUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{leavePlanId}"})
    public String getLeavePlans(@PathVariable Long leavePlanId, Model model) {
        model.addAttribute("leavePlan", this.leavePlanService.getLeavePlan(leavePlanId));
        return "leave_plan";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public String updateLeavePlanReason(Long leavePlanId, String reason) {
        this.leavePlanService.updateLeavePlanReason(leavePlanId, reason);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/delete"})
    public String deleteLeavePlan(Long leavePlanId) {
        this.leavePlanService.deleteLeavePlan(leavePlanId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }
}
