package com.narendra.uuc.web.controller;

import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.security.exception.DuplicateNameFoundException;
import com.narendra.uuc.security.service.RoleService;
import com.narendra.uuc.security.service.UserManagerService;
import com.narendra.uuc.web.utils.AjaxStatus;
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
@RequestMapping({"/user"})
public class UserController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserManagerService userManagerService;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public String userListingView(Model model) {
        this.logger.info("Preparing users default listing view");
        model.addAttribute("users", this.userManagerService.getAllUsers());
        return "user_list";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/new"})
    public String getNewUserView(Model model) {
        this.logger.info("Returning new user form");
        model.addAttribute("action", "add");
        return "user_form";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{userId}"})
    public String getUserUpdateView(@PathVariable Long userId, Model model) {
        this.logger.info("Returning user form to update user id :" + userId);
        User user = this.userManagerService.getUser(userId);
        model.addAttribute("roles", this.roleService.getAllRoles());
        model.addAttribute("user", user);
        model.addAttribute("action", "update");
        return "user_form";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public String addUser(User user) {
        Boolean duplicateExist = Boolean.FALSE;
        Long userId = null;
        try {
            userId = this.userManagerService.addUser(user, LoggedinUserAssistant.getLoggedInUserId());
        } catch (DuplicateNameFoundException e) {
            duplicateExist = Boolean.TRUE;
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        if (duplicateExist.booleanValue()) {
            jsonObject.put("status", AjaxStatus.DUPLICATE);
        } else {
            jsonObject.put("status", AjaxStatus.OK);
            jsonObject.put("id", userId);
        }
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public String updateUser(User user, Long[] roleIds) {
        Boolean duplicateExist = Boolean.FALSE;
        try {
            this.userManagerService.updateUser(user, LoggedinUserAssistant.getLoggedInUserId(), roleIds);
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

    @RequestMapping(method = {RequestMethod.GET}, value = {"/role/{roleId}"})
    public String getUsersOfRole(@PathVariable Long roleId, Model model) {
        this.logger.info("Returning users of role id:" + roleId);
        model.addAttribute("users", this.userManagerService.getUsersOfRole(roleId));
        return "user_list";
    }
}
