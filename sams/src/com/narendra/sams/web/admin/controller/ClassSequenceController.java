package com.narendra.sams.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"admin/classseq"})
public class ClassSequenceController {
    @RequestMapping(method = {RequestMethod.GET}, value = {""})
    public String getDefaultView(Model model) {
        model.addAttribute("classes", null);
        return "academic_year_class_sequence";
    }
}
