package com.narendra.sams.web.fee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/miscfee/home"})
public class MiscFeeHomeController {
    @RequestMapping(method = {RequestMethod.GET}, value = {""})
    public String getDefaultView(Long academicYearId, Model model) {
        return "misc_activity_home";
    }
}
