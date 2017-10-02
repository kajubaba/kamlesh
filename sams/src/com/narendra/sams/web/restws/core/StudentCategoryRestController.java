package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.StudentCategoryService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.StudentCategoryForm;
import com.narendra.sams.web.restws.mapper.form.StudentCategoryFormMapper;
import com.narendra.sams.web.restws.mapper.vo.StudentCategoryVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.StudentCategoryVO;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/studentcategory"})
public class StudentCategoryRestController {
    @Autowired
    private StudentCategoryService studentCategoryService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<StudentCategoryVO> getStudentCategories() {
        return StudentCategoryVOMapper.prepareStudentCategoryVOs(this.studentCategoryService.getAllStudentCategories(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{studentCategoryId}"})
    public StudentCategoryVO getStudentCategory(@PathVariable Long studentCategoryId) {
        return StudentCategoryVOMapper.prepareStudentCategoryVO(this.studentCategoryService.getStudentCategory(studentCategoryId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveAdmissionSchemes(@RequestBody StudentCategoryForm studentCategoryForm) {
        Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId();
        Institute institute = new Institute();
        institute.setId(instituteId);
        StudentCategory studentCategory = StudentCategoryFormMapper.prepareStudentCategoryDomain(studentCategoryForm);
        studentCategory.setInstitute(institute);
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            ajaxResponse.setGeneratedId(this.studentCategoryService.saveStudentCategory(studentCategory, LoggedinUserAssistant.getLoggedInUserId()));
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
