package com.narendra.sams.web.enquiry.controller;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryCountData;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.utils.LoggedInUserAuthority;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/enquiry/dashboard"})
public class EnquiryDashboardController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET})
    public String enquiryDashboard(Model model, HttpServletRequest request, Long academicYearId) {
        AcademicYear academicYear = null;
        List<AcademicYear> academicYears = this.academicYearService.getAllAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        }
        if (academicYearId != null) {
            academicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
            int totalEnq = 0;
            Long userId = null;
            if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
                userId = LoggedinUserAssistant.getLoggedInUserId();
            }
            List<Enquiry> enquiries = this.enquiryService.getAllEnquiries(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, userId);
            if (enquiries != null) {
                totalEnq = enquiries.size();
            }
            model.addAttribute("totalEnq", Integer.valueOf(totalEnq));
            model.addAttribute("academicYears", academicYears);
            loadcolorsInContext(this.servletContext);
        }
        model.addAttribute("academicYearId", academicYearId);
        model.addAttribute("currentAcademicYear", academicYear);
        return "enquiry_dashboard_new";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/enquiries/classwise"})
    public String getClassWiseXml(Long academicYearId) {
        if (academicYearId == null) {
            return null;
        }
        Long userId = null;
        if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
            userId = LoggedinUserAssistant.getLoggedInUserId();
        }
        List<EnquiryCountData> enquiryCountDataList = this.enquiryService.getClassWiseEnquiryCount(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, userId);
        StringBuffer dataXml = new StringBuffer();
        dataXml.append("<graph caption='' xAxisName='Class' yAxisName='Enquiries' showNames='1' decimalPrecision='0' formatNumberScale='10' rotateNames='1'>");
        if (enquiryCountDataList != null) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (EnquiryCountData enquiryCountData : enquiryCountDataList) {
                dataXml.append("<set ").append("name='").append(enquiryCountData.getDisplayName()).append("' ").append("value='").append(enquiryCountData.getEnquiryCount()).append("' ");
                if (colorCount == colors.size()) {
                    colorCount = 0;
                }
                int colorCount2 = colorCount + 1;
                dataXml.append("color='").append((String) colors.get(colorCount)).append("' ").append(" />");
                colorCount = colorCount2;
            }
        }
        dataXml.append("</graph>");
        return dataXml.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/enquiries/citywise"})
    public String getCityWiseXml(Long academicYearId) {
        if (academicYearId == null) {
            return null;
        }
        Long userId = null;
        if (!LoggedinUserAssistant.hasPermission(LoggedInUserAuthority.ROLE_ENQUIRY_VIEW_ALL.toString()).booleanValue()) {
            userId = LoggedinUserAssistant.getLoggedInUserId();
        }
        List<EnquiryCountData> enquiryCountDataList = this.enquiryService.getCityWiseEnquiryCount(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId, userId);
        StringBuffer dataXml = new StringBuffer();
        dataXml.append("<graph caption='' xAxisName='City' yAxisName='Enquiries' showNames='1' decimalPrecision='0' formatNumberScale='10' rotateNames='1'>");
        if (enquiryCountDataList != null) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (EnquiryCountData enquiryCountData : enquiryCountDataList) {
                dataXml.append("<set ");
                if ("".equals(enquiryCountData.getDisplayName())) {
                    dataXml.append("name='").append("Other").append("' ");
                }
                dataXml.append("name='").append(enquiryCountData.getDisplayName()).append("' ").append("value='").append(enquiryCountData.getEnquiryCount()).append("' ");
                if (colorCount == colors.size()) {
                    colorCount = 0;
                }
                int colorCount2 = colorCount + 1;
                dataXml.append("color='").append((String) colors.get(colorCount)).append("' ");
                dataXml.append(" link='list/city?searchStr=").append(enquiryCountData.getDisplayName().trim()).append("&academicYearId=").append(academicYearId).append("' ").append(" />");
                colorCount = colorCount2;
            }
        }
        dataXml.append("</graph>");
        return dataXml.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/cay"})
    public String changeAcademicYearView(Model model) {
        model.addAttribute("academicYears", this.academicYearService.getAllAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
        return "popup_change_academic_year";
    }

    public void loadcolorsInContext(ServletContext servletContext) {
        if (servletContext.getAttribute("colors") == null) {
            List<String> colors = new ArrayList();
            colors.add("AFD8F8");
            colors.add("F6BD0F");
            colors.add("8BBA00");
            colors.add("FF8E46");
            colors.add("008E8E");
            colors.add("D64646");
            colors.add("8E468E");
            colors.add("588526");
            colors.add("B3AA00");
            colors.add("008ED6");
            colors.add("9D080D");
            colors.add("A186BE");
            servletContext.setAttribute("colors", colors);
        }
    }
}
