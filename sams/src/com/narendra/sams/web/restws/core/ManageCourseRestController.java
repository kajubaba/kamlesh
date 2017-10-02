package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.CourseService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.CourseForm;
import com.narendra.sams.web.restws.mapper.form.CourseFormMapper;
import com.narendra.sams.web.restws.mapper.vo.CourseVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.CourseVO;
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
@RequestMapping({"/ws/course"})
public class ManageCourseRestController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveCourse(@RequestBody CourseForm courseForm) {
        Course course = CourseFormMapper.prepareCourseDomain(courseForm);
        course.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            ajaxResponse.setGeneratedId(this.courseService.saveCourse(course, LoggedinUserAssistant.getLoggedInUserId()));
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{courseId}"})
    public void deleteCourse() {
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{courseId}"})
    public CourseVO getCourse(@PathVariable Long courseId) {
        return CourseVOMapper.prepareCourseVO(this.courseService.getCourse(courseId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<CourseVO> listCourses() {
        return CourseVOMapper.prepareCourseVOs(this.courseService.getAllCourses(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
    }
}
