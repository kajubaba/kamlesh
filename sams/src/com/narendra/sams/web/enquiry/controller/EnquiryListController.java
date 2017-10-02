package com.narendra.sams.web.enquiry.controller;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.AffiliationAuthorityService;
import com.narendra.sams.enquiry.domain.AdvanceEnquirySearchParam;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.enquiry.service.EnquiryStatusService;
import com.narendra.sams.web.auth.UserDetail;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.utils.LoggedInUserAuthority;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import com.narendra.sams.web.utils.ServletContextManager;
import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.core.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/enquiry/list"})
public class EnquiryListController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private AffiliationAuthorityService affiliationAuthorityService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private EnquiryStatusService enquiryStatusService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/city"})
    public String citywiseListing(Model model, Long academicYearId, @RequestParam String searchStr) {
        AcademicYear enqAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfEnquiry();
        UserDetail loggedInUser = LoggedinUserAssistant.getLoggedInUser();
        List<User> users = this.userService.getActiveUsers(ServletContextManager.getApplication().getId());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("searchCriteria", "name");
        model.addAttribute("users", users);
        List<Enquiry> enquiries = null;
        if (searchStr != null) {
            Long userId = null;
            if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
                userId = LoggedinUserAssistant.getLoggedInUserId();
            }
            enquiries = this.enquiryService.getAllEnquiries(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), searchStr, "address.city", userId, academicYearId);
            model.addAttribute("searchStr", searchStr.trim());
            model.addAttribute("searchCriteria", "city");
        }
        model.addAttribute("enquiries", enquiries);
        model.addAttribute("totalEnquiries", Long.valueOf(0));
        model.addAttribute("academicYearId", academicYearId);
        AcademicYear currentAcademicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        model.addAttribute("currentAcademicYear", currentAcademicYear);
        if (enqAcademicYear.getId().equals(currentAcademicYear.getId())) {
            model.addAttribute("isEnqAcademicYear", Boolean.TRUE);
        } else {
            model.addAttribute("isEnqAcademicYear", Boolean.FALSE);
        }
        if (enquiries != null) {
            model.addAttribute("totalEnquiries", Integer.valueOf(enquiries.size()));
        }
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<EnquiryStatus> enqStatusList = this.enquiryStatusService.getAllActiveEnquiryStatus(workingInstituteId);
        Model model2 = model;
        model2.addAttribute("affiliationAuthorities", this.affiliationAuthorityService.getAllActive(workingInstituteId));
        model.addAttribute("enqStatusList", enqStatusList);
        model2 = model;
        model2.addAttribute("instituteSetting", UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting());
        return "enquiry_listing_view_new";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/all"})
    public String defaultEnquiryListing(Model model, Long academicYearId) {
        AcademicYear enqAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfEnquiry();
        UserDetail loggedInUser = LoggedinUserAssistant.getLoggedInUser();
        List<User> users = this.userService.getActiveUsers(ServletContextManager.getApplication().getId());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("searchCriteria", "name");
        model.addAttribute("users", users);
        Long userId = null;
        if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
            userId = LoggedinUserAssistant.getLoggedInUserId();
        }
        List<Enquiry> enquiries = this.enquiryService.getAllEnquiries(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, userId);
        model.addAttribute("enquiries", enquiries);
        model.addAttribute("totalEnquiries", Long.valueOf(0));
        model.addAttribute("academicYearId", academicYearId);
        AcademicYear currentAcademicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        model.addAttribute("currentAcademicYear", currentAcademicYear);
        if (enqAcademicYear.getId().equals(currentAcademicYear.getId())) {
            model.addAttribute("isEnqAcademicYear", Boolean.TRUE);
        } else {
            model.addAttribute("isEnqAcademicYear", Boolean.FALSE);
        }
        if (enquiries != null) {
            model.addAttribute("totalEnquiries", Integer.valueOf(enquiries.size()));
        }
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<EnquiryStatus> enqStatusList = this.enquiryStatusService.getAllActiveEnquiryStatus(workingInstituteId);
        Model model2 = model;
        model2.addAttribute("affiliationAuthorities", this.affiliationAuthorityService.getAllActive(workingInstituteId));
        model.addAttribute("enqStatusList", enqStatusList);
        model2 = model;
        model2.addAttribute("instituteSetting", UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting());
        return "enquiry_listing_view_new";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public String enquiryListing(Model model, Long academicYearId) {
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        }
        Long userId = null;
        if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
            userId = LoggedinUserAssistant.getLoggedInUserId();
        }
        List<Enquiry> enquiries = this.enquiryService.getAllEnquiries(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, userId);
        List<User> users = this.userService.getActiveUsers(ServletContextManager.getApplication().getId());
        model.addAttribute("totalEnquiries", Long.valueOf(0));
        model.addAttribute("searchCriteria", "name");
        model.addAttribute("users", users);
        model.addAttribute("academicYearId", academicYearId);
        if (enquiries != null) {
            model.addAttribute("totalEnquiries", Integer.valueOf(enquiries.size()));
        }
        model.addAttribute("enquiries", enquiries);
        return "enquiry_listing_new";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/search"})
    public String enquerySearch(@RequestParam String searchStr, @RequestParam String searchCriteria, Long academicYearId, Model model) {
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        }
        Long userId = null;
        if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
            userId = LoggedinUserAssistant.getLoggedInUserId();
        }
        List<Enquiry> enquiries = this.enquiryService.getAllEnquiries(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, userId, searchStr, searchCriteria);
        model.addAttribute("searchStr", searchStr);
        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("enquiries", enquiries);
        model.addAttribute("totalEnquiries", Long.valueOf(0));
        if (enquiries != null) {
            model.addAttribute("totalEnquiries", Integer.valueOf(enquiries.size()));
        }
        model.addAttribute("academicYearId", academicYearId);
        return "enquiry_listing_new";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/advSearch"})
    public String advanceSearch(Long academicYearId, AdvanceEnquirySearchParam advanceEnquirySearchParam, Model model) {
        Long userId = null;
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        }
        if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
            userId = LoggedinUserAssistant.getLoggedInUserId();
        }
        List<Enquiry> enquiries = this.enquiryService.advanceSearch(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, advanceEnquirySearchParam, userId);
        model.addAttribute("enquiries", enquiries);
        model.addAttribute("totalEnquiries", Long.valueOf(0));
        if (enquiries != null) {
            model.addAttribute("totalEnquiries", Integer.valueOf(enquiries.size()));
        }
        model.addAttribute("academicYearId", academicYearId);
        return "enquiry_listing_new";
    }
}
