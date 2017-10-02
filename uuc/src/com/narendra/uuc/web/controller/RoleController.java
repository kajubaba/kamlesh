package com.narendra.uuc.web.controller;

import com.narendra.uuc.core.domain.Application;
import com.narendra.uuc.core.domain.Privilege;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.core.service.UserService;
import com.narendra.uuc.security.exception.DuplicateNameFoundException;
import com.narendra.uuc.security.service.RoleService;
import com.narendra.uuc.web.utils.AjaxStatus;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/role"})
public class RoleController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public String userListingView(Model model, Long applicationId) {
        List<Role> roles;
        this.logger.info("Preparing role default listing view");
        List<Application> applications = this.userService.getActiveApplications();
        if (applicationId == null || applicationId == Long.valueOf(0)) {
            roles = this.roleService.getAllRoles();
        } else {
            roles = this.roleService.getRoles(applicationId);
        }
        model.addAttribute("applications", applications);
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("roles", roles);
        this.logger.info("Returning role default listing view");
        return "role_list";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/new"})
    public String getNewFeeHeadView(Model model) {
        this.logger.info("Returning new role form");
        model.addAttribute("applications", this.userService.getActiveApplications());
        model.addAttribute("action", "add");
        return "role_form";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{roleId}"})
    public String getRoleUpdateView(@PathVariable Long roleId, Model model) {
        this.logger.info("Returning role form to update role id :" + roleId);
        Role role = this.roleService.getRole(roleId);
        List<Privilege> privileges = this.roleService.getPrivileges(role.getApplication().getId());
        model.addAttribute("role", role);
        model.addAttribute("privileges", privileges);
        model.addAttribute("action", "update");
        return "role_form";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public String addRole(Role role) {
        Boolean duplicateExist = Boolean.FALSE;
        Long roleId = null;
        try {
            roleId = this.roleService.addRole(role, LoggedinUserAssistant.getLoggedInUserId());
        } catch (DuplicateNameFoundException e) {
            duplicateExist = Boolean.TRUE;
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        if (duplicateExist.booleanValue()) {
            jsonObject.put("status", AjaxStatus.DUPLICATE);
        } else {
            jsonObject.put("status", AjaxStatus.OK);
            jsonObject.put("id", roleId);
        }
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public String updateRole(Role role, Long[] privilegeIds) {
        Boolean duplicateExist = Boolean.FALSE;
        try {
            this.roleService.updateRole(role, privilegeIds, LoggedinUserAssistant.getLoggedInUserId());
        } catch (DuplicateNameFoundException e) {
            duplicateExist = Boolean.TRUE;
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        if (duplicateExist.booleanValue()) {
            jsonObject.put("status", AjaxStatus.DUPLICATE);
        } else {
            jsonObject.put("status", AjaxStatus.OK);
        }
        return jsonObject.toString();
    }
}
