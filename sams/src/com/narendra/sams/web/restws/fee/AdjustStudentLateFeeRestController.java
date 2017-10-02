package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.fee.service.StudentFeeService;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.fee.formbean.StudentLateFeeAdjustmentForm;
import com.narendra.sams.web.restws.fee.vo.InstallmentLateFeeVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/fee/adjustlatefee"})
public class AdjustStudentLateFeeRestController {
    @Autowired
    private StudentFeeService studentFeeService;
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping({"/{studentId}/{classHistoryId}"})
    public List<InstallmentLateFeeVO> getStudnetLateFeeDetails(@PathVariable Long studentId, @PathVariable Long classHistoryId) {
        List<InstallmentLateFeeVO> installmentLateFees = new ArrayList();
        List<DaysOverdue> lateFees = this.studentFeeService.getLateFeeDetails(studentId, this.studentService.getClassHistory(classHistoryId).getAcademicYear().getId());
        if (!(lateFees == null || lateFees.isEmpty())) {
            for (DaysOverdue daysOverdue : lateFees) {
                InstallmentLateFeeVO installmentLateFeeVO = new InstallmentLateFeeVO();
                installmentLateFeeVO.setDaysOverDueId(daysOverdue.getId());
                installmentLateFeeVO.setDaysOverdue(daysOverdue.getDaysOverdue());
                installmentLateFeeVO.setDisableLateFeeCalculation(daysOverdue.getCalculate());
                installmentLateFeeVO.setDiscountGiven(daysOverdue.getDiscount());
                installmentLateFeeVO.setInstallmentName(daysOverdue.getInstallment().getName());
                installmentLateFeeVO.setLateFee(daysOverdue.getLateFee());
                installmentLateFees.add(installmentLateFeeVO);
            }
        }
        return installmentLateFees;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/savechanges"})
    public AjaxSuccessResponse saveLateFeeAdjustments(@RequestBody StudentLateFeeAdjustmentForm studentLateFeeAdjustmentForm) {
        if (!(studentLateFeeAdjustmentForm.getInstallmentLateFees() == null || studentLateFeeAdjustmentForm.getInstallmentLateFees().isEmpty())) {
            List<DaysOverdue> daysOverdues = new ArrayList();
            for (InstallmentLateFeeVO installmentLateFee : studentLateFeeAdjustmentForm.getInstallmentLateFees()) {
                DaysOverdue daysOverdue = new DaysOverdue();
                daysOverdue.setId(installmentLateFee.getDaysOverDueId());
                daysOverdue.setDiscount(installmentLateFee.getDiscountGiven());
                daysOverdue.setCalculate(installmentLateFee.getDisableLateFeeCalculation());
                daysOverdues.add(daysOverdue);
            }
            this.studentFeeService.customizeLateFee(daysOverdues, LoggedinUserAssistant.getLoggedInUserId());
        }
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/onofflatefeecalculation/{dueInstallmentId}/{enableCalculation}"})
    public AjaxSuccessResponse onOffLateFeeCalculation(@PathVariable Long dueInstallmentId, @PathVariable Boolean enableCalculation) {
        this.studentFeeService.updateLateFeeCalculationFlag(dueInstallmentId, enableCalculation, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }
}
