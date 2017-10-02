package com.narendra.sams.web.enquiry.controller;

import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.address.service.AddressService;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.AffiliationAuthorityService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.List;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/enquiry"})
public class EnquiryController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AffiliationAuthorityService affiliationAuthorityService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/new"})
    public String newEnquery(Model model) {
        List<AffiliationAuthority> affiliationAuthorities = this.affiliationAuthorityService.getAllActive(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        List<State> states = this.addressService.getAllStates();
        List<Country> countries = this.addressService.getAllContries();
        model.addAttribute("affiliationAuthorities", affiliationAuthorities);
        model.addAttribute("states", states);
        model.addAttribute("countries", countries);
        model.addAttribute("activeAcademicYearId", UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId());
        model.addAttribute("instituteSetting", UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting());
        return "enquiry_new";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public String addEnquery(Enquiry enquiry) throws Exception {
        enquiry.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        enquiry.setAcademicYear(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfEnquiry());
        if (enquiry.getRegistrationFee() == null) {
            enquiry.setRegistrationFee(Long.valueOf(0));
        }
        if (enquiry.getAcademicYearClass().getId() == null) {
            enquiry.setAcademicYearClass(null);
        }
        if (!"".equals(enquiry.getStudentDobString())) {
            enquiry.setStudentDob(DateUtil.parseDate(enquiry.getStudentDobString(), "dd-MMM-yyyy"));
        }
        if (!(enquiry.getStudentPrevClass() == null || enquiry.getStudentPrevClass().getStudentStatus() == null || !"".equals(enquiry.getStudentPrevClass().getStudentStatus().trim()))) {
            enquiry.getStudentPrevClass().setStudentStatus(null);
        }
        this.enquiryService.addEnquiry(enquiry, LoggedinUserAssistant.getLoggedInUser().getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/view"})
    public String loadEnquiryToUpdate(@RequestParam Long enquiryId, Model model) {
        Enquiry enquiry = this.enquiryService.getEnquiry(enquiryId);
        List<AffiliationAuthority> affiliationAuthorities = this.affiliationAuthorityService.getAllActive(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        List<Course> courses = null;
        List<AcademicYearClass> classes = null;
        if (enquiry.getAcademicYearClass() != null) {
            courses = this.academicYearService.getActiveCourses(enquiry.getAcademicYear().getId(), enquiry.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId());
            classes = this.academicYearService.getActiveClassess(enquiry.getAcademicYearClass().getCourseYear().getCourse().getId(), enquiry.getAcademicYear().getId());
        }
        model.addAttribute("affiliationAuthorities", affiliationAuthorities);
        List<State> states = this.addressService.getAllStates();
        List<Country> countries = this.addressService.getAllContries();
        model.addAttribute("courses", courses);
        model.addAttribute("classes", classes);
        model.addAttribute("states", states);
        model.addAttribute("countries", countries);
        model.addAttribute("enquiry", enquiry);
        model.addAttribute("activeAcademicYearId", UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId());
        model.addAttribute("action", "update");
        model.addAttribute("instituteSetting", UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting());
        return "enquiry_new";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public String updateEnquery(Enquiry enquiry) throws Exception {
        if (enquiry.getAcademicYearClass() == null || (enquiry.getAcademicYearClass() != null && enquiry.getAcademicYearClass().getId() == null)) {
            enquiry.setAcademicYearClass(null);
        }
        if (!"".equals(enquiry.getStudentDobString())) {
            enquiry.setStudentDob(DateUtil.parseDate(enquiry.getStudentDobString(), "dd-MMM-yyyy"));
        }
        if (enquiry.getStudentPrevClass() != null && "".equals(enquiry.getStudentPrevClass().getStudentStatus())) {
            enquiry.getStudentPrevClass().setStudentStatus(null);
        }
        this.enquiryService.updateEnquiry(enquiry, LoggedinUserAssistant.getLoggedInUser().getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/isExist"})
    public String isEnquiryExist(@RequestParam String studentFirstName, @RequestParam String phone1, Long academicYearId) {
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        }
        Boolean isExist = this.enquiryService.isEnquiryExist(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, studentFirstName, phone1);
        JSONObject jsonObject = new JSONObject();
        if (isExist.booleanValue()) {
            jsonObject.put("status", AjaxStatus.DUPLICATE);
        } else {
            jsonObject.put("status", AjaxStatus.OK);
        }
        return jsonObject.toString();
    }
}
