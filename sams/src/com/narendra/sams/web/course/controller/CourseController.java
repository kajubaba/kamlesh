package com.narendra.sams.web.course.controller;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.web.course.vo.ClassVO;
import com.narendra.sams.web.course.vo.CourseVO;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/course"})
public class CourseController {
    @Autowired
    private AcademicYearService academicYearService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public String getAllActiveCourses(@RequestParam Long affiliationAuthorityId, @RequestParam Long academicYearId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<Course> courses = this.academicYearService.getActiveCourses(academicYearId, affiliationAuthorityId);
        List<CourseVO> courseVOList = null;
        if (!(courses == null || courses.isEmpty())) {
            courseVOList = new ArrayList();
            for (Course course : courses) {
                CourseVO courseVO = new CourseVO();
                BeanUtils.copyProperties(courseVO, course);
                courseVOList.add(courseVO);
            }
        }
        jsonObject.put("courses", JSONArray.fromObject(courseVOList));
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classList"})
    public String getAllActiveClassess(@RequestParam Long courseId, @RequestParam Long academicYearId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<AcademicYearClass> classes = this.academicYearService.getActiveClassess(courseId, academicYearId);
        List<ClassVO> classVOList = null;
        if (!(classes == null || classes.isEmpty())) {
            classVOList = new ArrayList();
            for (AcademicYearClass clazz : classes) {
                ClassVO classVO = new ClassVO();
                classVO.setId(clazz.getId());
                classVO.setName(clazz.getDisplayName());
                classVOList.add(classVO);
            }
        }
        jsonObject.put("classes", JSONArray.fromObject(classVOList));
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/promotionClassList"})
    public String getActivePromotionClassess(@RequestParam Long courseId, @RequestParam Long academicYearId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<AcademicYearClass> classes = this.academicYearService.getPromotionClasses(courseId, academicYearId);
        List<ClassVO> classVOList = null;
        if (!(classes == null || classes.isEmpty())) {
            classVOList = new ArrayList();
            for (AcademicYearClass clazz : classes) {
                ClassVO classVO = new ClassVO();
                classVO.setId(clazz.getId());
                classVO.setName(clazz.getDisplayName());
                classVOList.add(classVO);
            }
        }
        jsonObject.put("classes", JSONArray.fromObject(classVOList));
        return jsonObject.toString();
    }
}
