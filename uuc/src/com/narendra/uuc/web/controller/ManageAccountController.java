package com.narendra.uuc.web.controller;

import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.security.service.UserManagerService;
import com.narendra.uuc.web.formbean.Userbean;
import com.narendra.uuc.web.utils.AjaxStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/manageaccount"})
public class ManageAccountController {
    @Autowired
    private UserManagerService userManagerService;

    @RequestMapping(method = {RequestMethod.GET}, value = {""})
    public String defaultView() {
        return "manage_account";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/resolve"})
    public String resolveTrouble(String actionName, String changePassUserName, String forgotPassUserName, Model model) {
        User user = null;
        if ("forgotPass".equals(actionName)) {
            user = this.userManagerService.loadByUsername(forgotPassUserName);
        } else if ("changePass".equals(actionName)) {
            user = this.userManagerService.loadByUsername(changePassUserName);
        }
        JSONObject jsonObject = new JSONObject();
        if (user == null || !user.getActive().booleanValue()) {
            jsonObject.put("status", AjaxStatus.ERROR);
            jsonObject.put("msg", "Invalid username");
        } else {
            jsonObject.put("status", AjaxStatus.OK);
        }
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/change"})
    public String change(String actionName, String changePassUserName, String forgotPassUserName, Model model) {
        if ("forgotPass".equals(actionName)) {
            User user = this.userManagerService.loadByUsername(forgotPassUserName);
        } else if ("changePass".equals(actionName)) {
            model.addAttribute("user", this.userManagerService.loadByUsername(changePassUserName));
            model.addAttribute("displayCss", "none");
            return "change_password";
        }
        return "";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/changePass"})
    public String changePassword(Userbean userbean, Model model) {
        User user = this.userManagerService.authenticateUser(userbean.getUserName(), userbean.getPassword());
        if (user != null) {
            this.userManagerService.changePassword(user.getId(), userbean.getNewPassword());
            model.addAttribute("user", user);
            return "relogin";
        }
        model.addAttribute("user", this.userManagerService.loadByUsername(userbean.getUserName()));
        model.addAttribute("error", "Invalid password");
        model.addAttribute("displayCss", "block");
        return "change_password";
    }
}
