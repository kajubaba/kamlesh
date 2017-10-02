package com.narendra.sams.web.restws.academics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/academics/settings"})
public class AcademicSettingsController {
    @RequestMapping(method = {RequestMethod.GET}, value = {""})
    public String displayPgae(Model model) {
        return "academic_settings";
    }
}
