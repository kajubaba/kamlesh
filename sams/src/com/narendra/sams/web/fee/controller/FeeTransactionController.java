package com.narendra.sams.web.fee.controller;

import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.util.AmountInWords;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.PaidFeeService;
import com.narendra.sams.fee.service.StudentFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/fee/transaction"})
public class FeeTransactionController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private PaidFeeService paidFeeService;
    @Autowired
    private StudentFeeService studentFeeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET})
    public String getTodaysTransactions(Model model) {
        Date todayDate = DateUtil.getSystemDate();
        Long workingInstituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(workingInstituteId);
        model.addAttribute("feeTransactions", this.paidFeeService.getFeeTransactions(workingInstituteId, todayDate, null, null));
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("fromDate", DateUtil.formatDate(todayDate, "dd-MMM-yyyy"));
        return "fee_transactions";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String getTransactionsInDateRange(String fromDate, String toDate, Long academicYearId, Model model, HttpServletRequest req) {
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
        model.addAttribute("feeTransactions", this.paidFeeService.getFeeTransactions(workingInstituteId, from, to, academicYearId));
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("selectedAcademicYear", academicYearId);
        return "fee_transactions";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/{id}"})
    public String getStudentTransactions(@PathVariable Long id, Model model) {
        List<FeeTransaction> feeTransactions = this.studentFeeService.getFeeTransactions(id);
        Student student = this.studentService.getStudentById(id);
        model.addAttribute("feeTransactions", feeTransactions);
        model.addAttribute("student", student);
        return "student_fee_transactions";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/tr/{trid}"})
    public String getTransaction(@PathVariable Long trid, Model model) {
        FeeTransaction feeTransaction = this.studentFeeService.getFeeTransaction(trid);
        model.addAttribute("feeTransaction", feeTransaction);
        long feeAmount = 0;
        if (feeTransaction != null) {
            feeAmount = feeTransaction.getFeeSum();
        }
        if (0 != feeAmount) {
            model.addAttribute("amtWords", AmountInWords.convertToWords(feeAmount));
        }
        return "fee_transaction";
    }
}
