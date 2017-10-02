package com.narendra.sams.web.restws.academics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/academics/classsection"})
public class ClassSectionListRestController {
    @Autowired
    private WebApplicationContext webApplicationContext;
}
