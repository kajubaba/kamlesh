package com.narendra.uuc.web.auth;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(method = {RequestMethod.GET}, value = {"/"})
    public String getLoginForm(Model model, HttpServletRequest request) {
        model.addAttribute("manageaccounturl", makeURL(request));
        return "login";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/loginfailed"})
    public String LoginFail(Model model, HttpServletRequest request) {
        model.addAttribute("error", "true");
        model.addAttribute("manageaccounturl", makeURL(request));
        return "login";
    }

    private String makeURL(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uuc/manageaccount";
    }
}
