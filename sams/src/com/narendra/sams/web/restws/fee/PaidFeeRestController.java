package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.PaidFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.fee.helper.PaidFeeDomainToVOConverter;
import com.narendra.sams.web.restws.fee.vo.ClasswisePaidFeeVO;
import com.narendra.sams.web.restws.fee.vo.FeeTransactionVO;
import com.narendra.sams.web.restws.fee.vo.HeadwisePaidFeeVO;
import com.narendra.sams.web.restws.fee.vo.PaidFeeInHeadVO;
import com.narendra.sams.web.restws.fee.vo.PaidFeeStduentsInHeadVO;
import com.narendra.sams.web.restws.fee.vo.PaidFeeVO;
import com.narendra.sams.web.restws.fee.vo.TodaysFeeTransactionsVO;
import com.narendra.sams.web.utils.Validator;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/fee/paid"})
public class PaidFeeRestController {
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private PaidFeeService paidFeeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/asontoday"})
    public PaidFeeVO paidFee() {
        Long paidFee = this.paidFeeService.getTotalPaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        PaidFeeVO paidFeeVO = new PaidFeeVO();
        paidFeeVO.setName("Total paid fee");
        paidFeeVO.setAmount(paidFee);
        return paidFeeVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/today"})
    public PaidFeeVO paidToday() {
        Long paidFee = this.paidFeeService.getTodayPaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        PaidFeeVO paidFeeVO = new PaidFeeVO();
        paidFeeVO.setName("today paid fee");
        paidFeeVO.setAmount(paidFee);
        return paidFeeVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/recentTransactions"})
    public List<FeeTransactionVO> getRecentTransactions() {
        return PaidFeeDomainToVOConverter.prepareFeeTransactions(this.paidFeeService.getRecentFeeTransactions(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/allTransactions"})
    public List<FeeTransactionVO> getTransactions(Long academicYearId, String fromDate, String toDate) {
        return PaidFeeDomainToVOConverter.prepareFeeTransactions(this.paidFeeService.getAllFeeTransactions(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId(), academicYearId, Validator.convertToDate(fromDate), Validator.convertToDate(toDate)));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/todaysTransactions"})
    public TodaysFeeTransactionsVO todaysFeeTransactions() {
        TodaysFeeTransactionsVO todaysFeeTransactionsVO = new TodaysFeeTransactionsVO();
        Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId();
        Date todaysDate = DateUtil.getSystemDate();
        List<FeeTransaction> feeTransactions = this.paidFeeService.getAllFeeTransactions(instituteId, null, todaysDate, todaysDate);
        todaysFeeTransactionsVO.setStartDate(DateUtil.formatDate(todaysDate, "dd-MMM-yyyy"));
        todaysFeeTransactionsVO.setEndDate(DateUtil.formatDate(todaysDate, "dd-MMM-yyyy"));
        todaysFeeTransactionsVO.setFeeTransactions(PaidFeeDomainToVOConverter.prepareFeeTransactions(feeTransactions));
        return todaysFeeTransactionsVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/transaction/{feeTransactionId}"})
    public FeeTransactionVO getTransactionDetail(@PathVariable Long feeTransactionId) {
        return PaidFeeDomainToVOConverter.prepareTransactionDetail(this.paidFeeService.getFeeTransaction(feeTransactionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/headwise"})
    public List<HeadwisePaidFeeVO> getHeadwisePaidFee(Long academicYearId, String fromDate, String toDate) {
        Date from = Validator.convertToDate(fromDate);
        Date to = Validator.convertToDate(toDate);
        return PaidFeeDomainToVOConverter.prepareHeadwisePaidFee(this.paidFeeService.getHeadwisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), from, to, academicYearId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/headwise/{feeHeadId}/students"})
    public PaidFeeStduentsInHeadVO getHeadwisePaidFeeInDateRange(@PathVariable Long feeHeadId, String fromDate, String toDate, Long academicYearId) {
        PaidFeeStduentsInHeadVO paidFeeStduentsInHeadVO = new PaidFeeStduentsInHeadVO();
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
        paidFeeStduentsInHeadVO.setHeadName(this.feeHeadService.getFeeHead(feeHeadId).getName());
        paidFeeStduentsInHeadVO.setStudentPaidFees(this.paidFeeService.getHeadwisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), feeHeadId, from, to, academicYearId));
        return paidFeeStduentsInHeadVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise"})
    public List<ClasswisePaidFeeVO> getClasswisePaidFee(Long academicYearId, String fromDate, String toDate) {
        return PaidFeeDomainToVOConverter.prepareClasswisePaidFee(this.paidFeeService.getClassWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), Validator.convertToDate(fromDate), Validator.convertToDate(toDate), academicYearId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/in-head/datewise/{feeHeadId}"})
    public PaidFeeInHeadVO getDatewisePaidFeeInDateRange(@PathVariable Long feeHeadId, String fromDate, String toDate, Long academicYearId) {
        PaidFeeInHeadVO paidFeeInHeadVO = new PaidFeeInHeadVO();
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
        paidFeeInHeadVO.setHeadName(this.feeHeadService.getFeeHead(feeHeadId).getName());
        paidFeeInHeadVO.setPaidFees(this.paidFeeService.getDateWisePaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), feeHeadId, academicYearId, from, to));
        return paidFeeInHeadVO;
    }
}
