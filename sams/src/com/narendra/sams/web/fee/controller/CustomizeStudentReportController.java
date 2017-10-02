package com.narendra.sams.web.fee.controller;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.fee.service.StudentFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/fee/customizestudents"})
public class CustomizeStudentReportController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private StudentFeeService studentFeeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {""})
    public String defaultListCustomizedStudents(Model model) {
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        model.addAttribute("students", this.studentFeeService.getStudentsWhoseFeeIsCustomized(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), null));
        model.addAttribute("academicYearId", UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("selectedAcademicYear", UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        return "customize_student_list";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/search"})
    public String searchCustomizedStudents(Model model, @RequestParam String studentName, Long academicYearId) {
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        model.addAttribute("students", this.studentFeeService.getStudentsWhoseFeeIsCustomized(academicYearId, studentName));
        model.addAttribute("studentName", studentName);
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("selectedAcademicYear", academicYearId);
        return "customize_student_list";
    }
}
