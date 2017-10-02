package com.narendra.sams.web.fee.controller;

import com.narendra.sams.admission.domain.PaidFee;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.PaidFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping({"/fee/dashboard"})
public class FeeDashboadController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private PaidFeeService paidFeeService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET})
    public String feeDashboard(Model model, HttpServletRequest request) {
        AcademicYear admissionAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission();
        if (admissionAcademicYear != null) {
            long sum = 0;
            List<PaidFee> paidFees = this.paidFeeService.getClassWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), admissionAcademicYear.getId());
            if (!(paidFees == null || paidFees.isEmpty())) {
                for (PaidFee paidFee : paidFees) {
                    if (paidFee.getPaidAmount() != null) {
                        sum += paidFee.getPaidAmount().longValue();
                    }
                }
            }
            model.addAttribute("classWiseSum", Long.valueOf(sum));
            List<PaidFee> todaysPaidFees = this.paidFeeService.getClassWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), DateUtil.getSystemDateTime(), DateUtil.getSystemDateTime(), null);
            long todayFeeSum = 0;
            if (!(todaysPaidFees == null || todaysPaidFees.isEmpty())) {
                for (PaidFee paidFee2 : todaysPaidFees) {
                    if (paidFee2.getPaidAmount() != null) {
                        todayFeeSum += paidFee2.getPaidAmount().longValue();
                    }
                }
            }
            model.addAttribute("todayFeeSum", Long.valueOf(todayFeeSum));
            Date todayDate = DateUtil.getSystemDateTime();
            Date fromDate = DateUtil.oneMonthBefore(todayDate);
            List<PaidFee> onMonthPaidFees = this.paidFeeService.getClassWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), fromDate, todayDate, null);
            long oneMonthFeeSum = 0;
            if (!(onMonthPaidFees == null || onMonthPaidFees.isEmpty())) {
                for (PaidFee paidFee22 : onMonthPaidFees) {
                    if (paidFee22.getPaidAmount() != null) {
                        oneMonthFeeSum += paidFee22.getPaidAmount().longValue();
                    }
                }
            }
            model.addAttribute("oneMonthFeeSum", Long.valueOf(oneMonthFeeSum));
        }
        model.addAttribute("admissionAcademicYear", this.academicYearService.getAcademicYearById(admissionAcademicYear.getId().longValue()));
        loadcolorsInContext(this.servletContext);
        return "fee_dashboard";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise"})
    public String getClassWisePaidFeeXml(Long academicYearId) {
        List<PaidFee> paidFees = this.paidFeeService.getClassWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        StringBuffer dataXml = new StringBuffer();
        dataXml.append("<graph caption='' xAxisName='Class' yAxisName='Fee (Rs.)' showNames='0' decimalPrecision='0' formatNumberScale='10' rotateNames='1'>");
        if (!(paidFees == null || paidFees.isEmpty())) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (PaidFee paidFee : paidFees) {
                dataXml.append("<set ").append("name='").append(paidFee.getClassName()).append(", ").append("(" + paidFee.getClassSem() + " Yr)").append("' ").append("value='").append(paidFee.getPaidAmount()).append("' ");
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
    @RequestMapping(method = {RequestMethod.GET}, value = {"/today"})
    public String getTodaysPaidFeeXml(Long academicYearId) {
        List<PaidFee> todaysPaidFees = this.paidFeeService.getClassWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), DateUtil.getSystemDateTime(), DateUtil.getSystemDateTime(), null);
        StringBuffer dataXml = new StringBuffer();
        dataXml.append("<graph caption='' xAxisName='Class' yAxisName='Fee (Rs.)' showNames='0' decimalPrecision='0' formatNumberScale='10' rotateNames='1'>");
        if (!(todaysPaidFees == null || todaysPaidFees.isEmpty())) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (PaidFee paidFee : todaysPaidFees) {
                dataXml.append("<set ").append("name='").append(paidFee.getClassName()).append(" ").append("(" + paidFee.getClassSem() + " Yr)").append("' ").append("value='").append(paidFee.getPaidAmount()).append("' ");
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
    @RequestMapping(method = {RequestMethod.GET}, value = {"/onemonth"})
    public String getOneMonthPaidFeeXml(Long academicYearId) {
        Date todayDate = DateUtil.getSystemDateTime();
        List<PaidFee> oneMonthPaidFees = this.paidFeeService.getClassWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), DateUtil.oneMonthBefore(todayDate), todayDate, null);
        StringBuffer dataXml = new StringBuffer();
        dataXml.append("<graph caption='' xAxisName='Class' yAxisName='Fee (Rs.)' showNames='0' decimalPrecision='0' formatNumberScale='10' rotateNames='1'>");
        if (!(oneMonthPaidFees == null || oneMonthPaidFees.isEmpty())) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (PaidFee paidFee : oneMonthPaidFees) {
                dataXml.append("<set ").append("name='").append(paidFee.getClassName()).append(" ").append("(" + paidFee.getClassSem() + " Yr)").append("' ").append("value='").append(paidFee.getPaidAmount()).append("' ");
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
