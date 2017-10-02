package com.narendra.sams.web.fee.controller;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.PaidFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/fee/paid"})
public class PaidFeeDetailController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private PaidFeeService paidFeeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.POST}, value = {"/daterange"})
    public String getPaidFeeInDateRange(String fromDate, String toDate, Long academicYearId, Model model) {
        Date from = null;
        Date to = null;
        if (!"".equals(fromDate)) {
            try {
                from = DateUtil.parseDate(fromDate, "dd-MMM-yyyy");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!"".equals(toDate)) {
            try {
                to = DateUtil.parseDate(toDate, "dd-MMM-yyyy");
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(workingInstituteId);
        model.addAttribute("paidFees", this.paidFeeService.getClassWisePaidFee(workingInstituteId, from, to, academicYearId));
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("selectedAcademicYear", academicYearId);
        return "paid_fee_listing";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise"})
    public String getClasswisePaidFee(Model model) {
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(workingInstituteId);
        model.addAttribute("paidFees", this.paidFeeService.getClassWisePaidFee(workingInstituteId, UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId()));
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("selectedAcademicYear", UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        return "paid_fee_listing";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/today"})
    public String getTodaysPaidFee(Model model) {
        Date todayDate = DateUtil.getSystemDate();
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(workingInstituteId);
        model.addAttribute("paidFees", this.paidFeeService.getClassWisePaidFee(workingInstituteId, todayDate, null, null));
        model.addAttribute("fromDate", DateUtil.formatDate(todayDate, "dd-MMM-yyyy"));
        model.addAttribute("academicYears", academicYears);
        return "paid_fee_listing";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/lastonemonth"})
    public String getLastOneMonthPaidFee(Model model) {
        Date toDate = DateUtil.getSystemDateTime();
        Date fromDate = DateUtil.oneMonthBefore(toDate);
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(workingInstituteId);
        model.addAttribute("paidFees", this.paidFeeService.getClassWisePaidFee(workingInstituteId, fromDate, toDate, null));
        model.addAttribute("fromDate", DateUtil.formatDate(fromDate, "dd-MMM-yyyy"));
        model.addAttribute("toDate", DateUtil.formatDate(toDate, "dd-MMM-yyyy"));
        model.addAttribute("academicYears", academicYears);
        return "paid_fee_listing";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/headwise"})
    public String getHeadwisePaidFee(Model model) {
        Date todayDate = DateUtil.getSystemDate();
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(workingInstituteId);
        model.addAttribute("paidFees", this.paidFeeService.getHeadwisePaidFee(workingInstituteId, todayDate, null, null));
        model.addAttribute("fromDate", DateUtil.formatDate(todayDate, "dd-MMM-yyyy"));
        model.addAttribute("academicYears", academicYears);
        return "paid_fee_listing_head_wise";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/headwise/daterange"})
    public String getHeadwisePaidFeeInDateRange(String fromDate, String toDate, Long academicYearId, Model model) {
        Date from = null;
        Date to = null;
        if (!"".equals(fromDate)) {
            try {
                from = DateUtil.parseDate(fromDate, "dd-MMM-yyyy");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!"".equals(toDate)) {
            try {
                to = DateUtil.parseDate(toDate, "dd-MMM-yyyy");
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(workingInstituteId);
        model.addAttribute("paidFees", this.paidFeeService.getHeadwisePaidFee(workingInstituteId, from, to, academicYearId));
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("selectedAcademicYear", academicYearId);
        return "paid_fee_listing_head_wise";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/headwise/{feeHeadId}/students"})
    public String getHeadwisePaidFeeInDateRange(@PathVariable Long feeHeadId, String fromDate, String toDate, Long academicYearId, Model model) {
        Date from = null;
        Date to = null;
        if (!"".equals(fromDate)) {
            try {
                from = DateUtil.parseDate(fromDate, "dd-MMM-yyyy");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!"".equals(toDate)) {
            try {
                to = DateUtil.parseDate(toDate, "dd-MMM-yyyy");
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        Model model2 = model;
        model2.addAttribute("paidFees", this.paidFeeService.getHeadwisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), feeHeadId, from, to, academicYearId));
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        AcademicYear academicYear = null;
        if (academicYearId != null) {
            academicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        }
        model.addAttribute("selectedAcademicYear", academicYear);
        return "paid_fee_student_list";
    }
}
