package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.InstallmentService;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.mapper.form.BusFeeInstallmentFormMapper;
import com.narendra.sams.web.restws.mapper.vo.BusFeeInstallmentVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.BusFeeInstallmentPercentageVO;
import com.narendra.sams.web.restws.vo.BusFeeVO;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academic-session/busfee/installment"})
public class BusFeeInstallmentRestController {
    @Autowired
    private AcademicYearBusFeeService academicYearBusFeeService;
    @Autowired
    private InstallmentService installmentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/{academicSessionId}"})
    public BusFeeVO getBusFeeInstallments(@PathVariable Long academicSessionId) {
        BusFeeVO busFeeVO = null;
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(academicSessionId);
        if (busFeeInstallment != null) {
            if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeInstallment.getSetupType())) {
                busFeeVO = BusFeeInstallmentVOMapper.prepareBusFeeInstallmentVO(busFeeInstallment);
                busFeeVO.setSetupType(BusFeeInstallment.SETUP_TYPE_PERCENTAGE);
            } else if (BusFeeInstallment.SETUP_TYPE_MANUAL.equals(busFeeInstallment.getSetupType())) {
                busFeeVO = BusFeeInstallmentVOMapper.prepareAcademicSessionBusStopFeeVOs(this.academicYearBusFeeService.getAssigedBusFee(academicSessionId), busFeeInstallment.getInstallmentCount());
                busFeeVO.setAcademicSessionId(academicSessionId);
                busFeeVO.setSetupType(BusFeeInstallment.SETUP_TYPE_MANUAL);
            }
            busFeeVO.setBusFeeInstallmentId(busFeeInstallment.getId());
            return busFeeVO;
        }
        busFeeVO = BusFeeInstallmentVOMapper.prepareAcademicSessionBusStopFeeVOs(this.academicYearBusFeeService.getAssigedBusFee(academicSessionId), Long.valueOf(2));
        busFeeVO.setAcademicSessionId(academicSessionId);
        busFeeVO.setSetupType(BusFeeInstallment.SETUP_TYPE_MANUAL);
        return busFeeVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/percentage/change/{academicSessionId}"})
    public List<BusFeeInstallmentPercentageVO> changePercentageBusFeeInstallments(@PathVariable Long academicSessionId, Long installmentCount) {
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(academicSessionId);
        if (installmentCount == null) {
            if (busFeeInstallment != null) {
                installmentCount = busFeeInstallment.getInstallmentCount();
            } else {
                installmentCount = Long.valueOf(2);
            }
        }
        return BusFeeInstallmentVOMapper.prepareBusFeeInstallmentVO(busFeeInstallment, this.installmentService.getInstallments(installmentCount));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/percentage/save"})
    public AjaxResponse savePercentageBusFeeInstallments(@RequestBody BusFeeVO busFeeVO) {
        BusFeeInstallment busFeeInstallment = new BusFeeInstallment();
        busFeeInstallment.setId(busFeeVO.getBusFeeInstallmentId());
        busFeeInstallment.setInstallmentCount(busFeeVO.getInstallments());
        busFeeInstallment.setSetupType(BusFeeInstallment.SETUP_TYPE_PERCENTAGE);
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(busFeeVO.getAcademicSessionId());
        busFeeInstallment.setAcademicYear(academicYear);
        busFeeInstallment.setBusFeeInstallmentDetails(new HashSet());
        for (BusFeeInstallmentPercentageVO busFeeInstallmentPercentageVO : busFeeVO.getBusFeeInstallmentInPercentage()) {
            BusFeeInstallmentDetail busFeeInstallmentDetail = new BusFeeInstallmentDetail();
            busFeeInstallmentDetail.setId(busFeeInstallmentPercentageVO.getId());
            busFeeInstallmentDetail.setBusFeeInstallment(busFeeInstallment);
            busFeeInstallmentDetail.setFeePercent(busFeeInstallmentPercentageVO.getFeePercent());
            Installment installment = new Installment();
            installment.setId(busFeeInstallmentPercentageVO.getInstallmentId());
            busFeeInstallmentDetail.setInstallment(installment);
            busFeeInstallment.getBusFeeInstallmentDetails().add(busFeeInstallmentDetail);
        }
        this.academicYearBusFeeService.saveBusFeeInstallment(busFeeInstallment, LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/manual/change/{academicSessionId}"})
    public BusFeeVO getInstallmentLists(@PathVariable Long academicSessionId, Long installments) {
        BusFeeInstallment busFeeInstallment = null;
        if (installments == null) {
            busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(academicSessionId);
            if (busFeeInstallment != null) {
                installments = busFeeInstallment.getInstallmentCount();
            } else {
                installments = Long.valueOf(2);
            }
        }
        BusFeeVO busFeeVO = BusFeeInstallmentVOMapper.prepareAcademicSessionBusStopFeeVOs(this.academicYearBusFeeService.getAssigedBusFee(academicSessionId), installments);
        if (busFeeInstallment != null) {
            busFeeVO.setBusFeeInstallmentId(busFeeInstallment.getId());
        }
        busFeeVO.setAcademicSessionId(academicSessionId);
        busFeeVO.setSetupType(BusFeeInstallment.SETUP_TYPE_MANUAL);
        return busFeeVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/manual/save"})
    public BusFeeVO saveBusFeeInstallments(@RequestBody BusFeeVO busFeeVO) {
        this.academicYearBusFeeService.saveBusFeeInstallments(BusFeeInstallmentFormMapper.prepareBusFeeInstallments(busFeeVO), busFeeVO.getInstallments(), busFeeVO.getAcademicSessionId(), LoggedinUserAssistant.getLoggedInUserId());
        BusFeeVO newBusFeeVO = BusFeeInstallmentVOMapper.prepareAcademicSessionBusStopFeeVOs(this.academicYearBusFeeService.getAssigedBusFee(busFeeVO.getAcademicSessionId()), busFeeVO.getInstallments());
        newBusFeeVO.setSetupType(BusFeeInstallment.SETUP_TYPE_MANUAL);
        newBusFeeVO.setInstallments(busFeeVO.getInstallments());
        newBusFeeVO.setBusFeeInstallmentId(this.academicYearBusFeeService.getBusFeeInstallment(busFeeVO.getAcademicSessionId()).getId());
        newBusFeeVO.setAcademicSessionId(busFeeVO.getAcademicSessionId());
        return newBusFeeVO;
    }
}
