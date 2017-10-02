package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.service.AcademicSessionClassService;
import com.narendra.sams.web.restws.mapper.vo.AcademicSessionClassVOMapper;
import com.narendra.sams.web.restws.vo.AcademicSessionClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/academic-session/class"})
public class AcademicSessionClassRestController {
    @Autowired
    private AcademicSessionClassService academicSessionClassService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{classId}"})
    public AcademicSessionClassVO getClass(@PathVariable Long classId) {
        return AcademicSessionClassVOMapper.prepareAcademicSessionClassVO(this.academicSessionClassService.getClass(classId));
    }
}
