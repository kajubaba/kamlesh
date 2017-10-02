package com.narendra.sams.web.admin.controller;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.domain.CourseYearType;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.AcademicYearSettingService;
import com.narendra.sams.core.service.AffiliationAuthorityService;
import com.narendra.sams.core.service.CourseYearTypeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import java.util.List;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/admin/academicyear/setting"})
public class AcademicYearSettingsController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private AcademicYearSettingService academicYearSettingService;
    @Autowired
    private AffiliationAuthorityService affiliationAuthorityService;
    @Autowired
    private CourseYearTypeService courseYearTypeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/default"})
    public String getDefaultView(@RequestParam Long academicYearId, Model model) {
        AcademicYear academicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        List<AffiliationAuthority> affiliationAuthorities = this.affiliationAuthorityService.getAllAffiliationAuthorities(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        model.addAttribute("academicYear", academicYear);
        model.addAttribute("affiliationAuthorities", affiliationAuthorities);
        model.addAttribute("academicYearId", academicYearId);
        return "academic_year_settings";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/courses"})
    public String getAcademicYearCoursesView(@RequestParam Long academicYearId, @RequestParam Long affiliationAuthorityId, @RequestParam Long courseType, Model model) {
        if (Long.valueOf(1).equals(courseType)) {
            model.addAttribute("courseYearSettings", this.academicYearSettingService.getInvidualCourseSettings(academicYearId, affiliationAuthorityId));
            return "academic_year_individual_course_settings";
        }
        model.addAttribute("academicYearCourses", this.academicYearSettingService.getAcademicYearCourses(academicYearId, affiliationAuthorityId));
        return "academic_year_settings_list";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/delete/course/{id}"})
    public String removeAcademicYearCourse(@PathVariable Long id) {
        this.academicYearSettingService.removeCourseFromAcademicYear(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/course/notadded"})
    public String getNotAddedCourseView(Long academicYearId, Long affiliationAuthorityId, Model model) {
        model.addAttribute("courses", this.academicYearSettingService.getNotAddedCourses(academicYearId, affiliationAuthorityId));
        model.addAttribute("academicYearId", academicYearId);
        model.addAttribute("affiliationAuthorityId", affiliationAuthorityId);
        return "academic_year_not_added_courses";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/course/managePropView"})
    public String manageCourseYearPropertiesView(Long courseYearSettingId, Model model) {
        List<CourseYearType> types = this.courseYearTypeService.getAllCourseYearTypes();
        model.addAttribute("courseYearSetting", this.academicYearSettingService.getCourseYearSetting(courseYearSettingId));
        model.addAttribute("types", types);
        return "admin_academic_course_year_prop_popup";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/course/saveProp"})
    public String saveCourseYearProperties(Long courseYearSettingId, Boolean active, Long courseYearTypeId, Long intake) {
        this.academicYearSettingService.saveCourseYearSetting(courseYearSettingId, active, courseYearTypeId, intake);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }
}
