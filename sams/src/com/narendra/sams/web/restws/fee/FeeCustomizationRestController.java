package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.FeeCustomizeComments;
import com.narendra.sams.admission.domain.FeeDiscount;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeDetail;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.service.LateFeeRuleService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.fee.service.StudentFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.fee.vo.FeeAdjustVO;
import com.narendra.sams.web.restws.fee.vo.FeeHeadVO;
import com.narendra.sams.web.restws.fee.vo.InstallmentDueDateVO;
import com.narendra.sams.web.restws.fee.vo.InstallmentFeeVO;
import com.narendra.sams.web.restws.fee.vo.LateFeeRuleVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/fee/customize"})
public class FeeCustomizationRestController {
    @Autowired
    private AcademicYearBusFeeService academicYearBusFeeService;
    @Autowired
    private AcademicYearFeeService academicYearFeeService;
    @Autowired
    private CustomizeStudentFeeService customizeStudentFeeService;
    @Autowired
    private LateFeeRuleService lateFeeRuleService;
    @Autowired
    private StudentFeeService studentFeeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentId}/{classHistoryId}"})
    public FeeAdjustVO getCustomizedInstallments(@PathVariable Long studentId, @PathVariable Long classHistoryId, Integer selectedInstallmentCount) {
        FeeAdjustVO customizeFee = new FeeAdjustVO();
        int prevInstallmentCount = 0;
        Student student = this.studentService.getStudentById(studentId);
        ClassHistory currentClass = this.studentService.getClassHistory(classHistoryId);
        AcademicYearFee academicYearFee = this.academicYearFeeService.getAcademicYearFee(currentClass.getAcademicYear().getId(), currentClass.getAcademicYearClass().getCourseYear().getId(), currentClass.getAdmissionType().getId());
        customizeFee.setAcademicYearFeeId(academicYearFee.getId());
        customizeFee.setLateFeeRuleVOs(preppareLateFeeRuleToDisplay(this.lateFeeRuleService.getAllLateFeeRules(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId())));
        Boolean customized = Boolean.FALSE;
        if (!(student == null || academicYearFee == null)) {
            BusStop studentBusStop = currentClass.getBusStop();
            AcademicYear feeAcademicYear = academicYearFee.getAcademicYear();
            List<FeeHeadVO> custInstllments = null;
            List<CustomizeInstallment> customizeInstallments = this.studentFeeService.getCustomizeInstallments(student.getId(), academicYearFee.getId());
            BusFee busFee = null;
            if (studentBusStop != null) {
                busFee = this.academicYearBusFeeService.getBusFee(feeAcademicYear.getId(), studentBusStop.getId());
            }
            if (customizeInstallments != null && !customizeInstallments.isEmpty()) {
                customized = Boolean.TRUE;
                prevInstallmentCount = customizeInstallments.size();
            } else if (!(academicYearFee == null || academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty())) {
                customized = Boolean.FALSE;
                prevInstallmentCount = academicYearFee.getAcademicYearFeeInstallments().size();
            }
            List<FeeTransactionDetail> feeTransactionDetails = this.studentFeeService.getAllTransactionDetails(student.getId(), academicYearFee.getId(), customized);
            int installmentCount;
            if (customizeInstallments != null && !customizeInstallments.isEmpty()) {
                List<FeeDiscount> feeDiscounts = this.customizeStudentFeeService.getFeeDiscounts(student.getId(), academicYearFee.getId());
                installmentCount = customizeInstallments.size();
                if (selectedInstallmentCount != null) {
                    installmentCount = selectedInstallmentCount.intValue();
                }
                custInstllments = prepareCustomizedInstallments(academicYearFee.getAcademicYearFeeDetails(), feeDiscounts, customizeInstallments, busFee, installmentCount, feeTransactionDetails);
                customizeFee.setInstallmentDueDates(prepareCustomizeInstallmentDueDate(customizeInstallments, installmentCount));
            } else if (!(academicYearFee == null || academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty())) {
                installmentCount = academicYearFee.getAcademicYearFeeInstallments().size();
                if (selectedInstallmentCount != null) {
                    installmentCount = selectedInstallmentCount.intValue();
                }
                custInstllments = prepareCustomizedInstallments(academicYearFee, installmentCount, busFee, feeTransactionDetails);
                List<AcademicYearFeeInstallment> academicYearFeeInstallments = new ArrayList(academicYearFee.getAcademicYearFeeInstallments());
                Collections.sort(academicYearFeeInstallments, new BeanComparator("installment.name"));
                customizeFee.setInstallmentDueDates(prepareAcademicYearFeeDueDate(academicYearFeeInstallments, installmentCount));
            }
            FeeCustomizeComments feeCustomizeComments = this.customizeStudentFeeService.getComments(student.getId(), academicYearFee.getId());
            customizeFee.setCustomized(customized);
            customizeFee.setStudentId(studentId);
            customizeFee.setFeeHeads(custInstllments);
            customizeFee.setAcademicYearClassId(currentClass.getAcademicYearClass().getId());
            if (feeCustomizeComments != null) {
                customizeFee.setCommentsId(feeCustomizeComments.getId());
                customizeFee.setComments(feeCustomizeComments.getComments());
            }
        }
        customizeFee.setPrevInstallmentCount(prevInstallmentCount);
        return customizeFee;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/reset/adjustment/{studentId}/{academicYearFeeId}"})
    public AjaxResponse resetFeeAdjustment(@PathVariable Long studentId, @PathVariable Long academicYearFeeId) {
        this.customizeStudentFeeService.deleteFeeAdjustment(studentId, academicYearFeeId);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveCusomizedFee(@RequestBody FeeAdjustVO customizedFee) {
        CustomizeInstallment customizeInstallment;
        Student loadedStudent = this.studentService.getStudentById(customizedFee.getStudentId());
        AcademicYearFee academicYearFee = this.academicYearFeeService.getAcademicYearFeeById(customizedFee.getAcademicYearFeeId());
        List<FeeDiscount> feeDiscounts = null;
        Map<Long, CustomizeInstallment> custInstallmentMap = null;
        List<CustomizeInstallment> customizeInstallments = null;
        List<CustomizeInstallment> updateList = null;
        List<CustomizeInstallmentDetail> customizeInstallmentDetails = null;
        List<CustomizeInstallment> addList = null;
        FeeCustomizeComments feeCustomizeComments = new FeeCustomizeComments();
        Boolean customized = Boolean.FALSE;
        AcademicYearClass academicYearClass = new AcademicYearClass();
        academicYearClass.setId(customizedFee.getAcademicYearClassId());
        if (!(customizedFee.getFeeHeads() == null || customizedFee.getFeeHeads().isEmpty())) {
            feeDiscounts = new ArrayList();
            custInstallmentMap = new HashMap();
            for (FeeHeadVO feeHeadVO : customizedFee.getFeeHeads()) {
                FeeHead feeHead = new FeeHead();
                feeHead.setId(feeHeadVO.getHeadId());
                FeeDiscount feeDiscount = new FeeDiscount();
                feeDiscount.setId(feeHeadVO.getFeeDiscountId());
                feeDiscount.setAmount(feeHeadVO.getDiscount());
                feeDiscount.setFeeHead(feeHead);
                feeDiscount.setStudent(loadedStudent);
                feeDiscount.setAcademicYearFee(academicYearFee);
                feeDiscount.setAcademicYearClass(academicYearClass);
                feeDiscounts.add(feeDiscount);
                for (InstallmentFeeVO installmentFeeVO : feeHeadVO.getInstallments()) {
                    customizeInstallment = (CustomizeInstallment) custInstallmentMap.get(installmentFeeVO.getInstallmentId());
                    if (customizeInstallment == null) {
                        Installment installment = new Installment();
                        installment.setId(installmentFeeVO.getInstallmentId());
                        customizeInstallment = new CustomizeInstallment();
                        customizeInstallment.setStudent(loadedStudent);
                        customizeInstallment.setAcademicYearClass(academicYearClass);
                        customizeInstallment.setAcademicYearFee(academicYearFee);
                        customizeInstallment.setInstallment(installment);
                        custInstallmentMap.put(installmentFeeVO.getInstallmentId(), customizeInstallment);
                    }
                    if (customizeInstallment.getCustomizeInstallmentDetails() == null) {
                        customizeInstallment.setCustomizeInstallmentDetails(new HashSet());
                    }
                    CustomizeInstallmentDetail customizeInstallmentDetail = new CustomizeInstallmentDetail();
                    customizeInstallmentDetail.setId(installmentFeeVO.getInstallmentFeeId());
                    customizeInstallmentDetail.setFeeHead(feeHead);
                    customizeInstallmentDetail.setAmount(installmentFeeVO.getAmount());
                    customizeInstallmentDetail.setCustomizeInstallment(customizeInstallment);
                    customizeInstallment.getCustomizeInstallmentDetails().add(customizeInstallmentDetail);
                }
            }
        }
        if (custInstallmentMap != null) {
            customizeInstallments = new ArrayList(custInstallmentMap.values());
            Collections.sort(customizeInstallments, new BeanComparator("installment.id", new NullComparator()));
            for (CustomizeInstallment customizeInstallment2 : customizeInstallments) {
                int index = customizeInstallment2.getInstallment().getId().intValue() - 1;
                String dueDate = ((InstallmentDueDateVO) customizedFee.getInstallmentDueDates().get(index)).getDueDateStr();
                customizeInstallment2.setId(((InstallmentDueDateVO) customizedFee.getInstallmentDueDates().get(index)).getCustomizeInstallmentId());
                if (!(dueDate == null || "".equals(dueDate))) {
                    try {
                        customizeInstallment2.setDueDate(DateUtil.parseDate(dueDate, "dd-MMM-yyyy"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (((InstallmentDueDateVO) customizedFee.getInstallmentDueDates().get(index)).getLatefeeRuleId() != null) {
                    LateFeeRule lateFeeRule = new LateFeeRule();
                    lateFeeRule.setId(((InstallmentDueDateVO) customizedFee.getInstallmentDueDates().get(index)).getLatefeeRuleId());
                    customizeInstallment2.setLateFeeRule(lateFeeRule);
                }
            }
            if (customizedFee.getCustomized().booleanValue()) {
                int orgCustInstlCount = this.studentFeeService.getCustomizeInstallments(loadedStudent.getId(), customizedFee.getAcademicYearFeeId()).size();
                if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
                    customized = Boolean.TRUE;
                    customizeInstallmentDetails = new ArrayList();
                    updateList = customizeInstallments.subList(0, orgCustInstlCount);
                    for (CustomizeInstallment customizeInstallment22 : updateList) {
                        customizeInstallmentDetails.addAll(customizeInstallment22.getCustomizeInstallmentDetails());
                    }
                    addList = customizeInstallments.subList(orgCustInstlCount, customizeInstallments.size());
                }
            }
        }
        feeCustomizeComments.setId(customizedFee.getCommentsId());
        feeCustomizeComments.setComments(customizedFee.getComments());
        feeCustomizeComments.setStudent(loadedStudent);
        feeCustomizeComments.setAcademicYearFee(academicYearFee);
        this.customizeStudentFeeService.save(feeDiscounts, customized, customizeInstallmentDetails, updateList, addList, customizeInstallments, feeCustomizeComments, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    public List<FeeHeadVO> prepareCustomizedInstallments(Set<AcademicYearFeeDetail> academicYearFeeDetails, List<FeeDiscount> feeDiscounts, List<CustomizeInstallment> customizeInstallments, BusFee busFee, int installmentCount, List<FeeTransactionDetail> feeTransactionDetails) {
        if (academicYearFeeDetails == null) {
            return null;
        }
        Map<Long, FeeHeadVO> feeHeadMap = new HashMap();
        for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFeeDetails) {
            FeeHeadVO feeHeadVO = new FeeHeadVO();
            feeHeadVO.setHeadId(academicYearFeeDetail.getFeeHead().getId());
            feeHeadVO.setHeadName(academicYearFeeDetail.getFeeHead().getName());
            feeHeadVO.setHeadFee(academicYearFeeDetail.getAmount());
            feeHeadVO.setInstallments(new ArrayList());
            feeHeadMap.put(academicYearFeeDetail.getFeeHead().getId(), feeHeadVO);
        }
        FeeHeadVO busFeeHeadVO = new FeeHeadVO();
        busFeeHeadVO.setHeadId(UserSessionManager.getUserSession(this.webApplicationContext).getBusFeeHead().getId());
        busFeeHeadVO.setHeadName(UserSessionManager.getUserSession(this.webApplicationContext).getBusFeeHead().getName());
        if (busFee != null) {
            busFeeHeadVO.setHeadFee(busFee.getRs());
        } else {
            busFeeHeadVO.setHeadFee(Long.valueOf(0));
        }
        busFeeHeadVO.setInstallments(new ArrayList());
        feeHeadMap.put(UserSessionManager.getUserSession(this.webApplicationContext).getBusFeeHead().getId(), busFeeHeadVO);
        if (feeDiscounts != null) {
            for (FeeDiscount feeDiscount : feeDiscounts) {
            	FeeHeadVO  feeHeadVO = (FeeHeadVO) feeHeadMap.get(feeDiscount.getFeeHead().getId());
                feeHeadVO.setFeeDiscountId(feeDiscount.getId());
                feeHeadVO.setDiscount(feeDiscount.getAmount());
            }
        }
        for (CustomizeInstallment customizeInstallment : customizeInstallments) {
            if (customizeInstallment.getCustomizeInstallmentDetails() != null) {
                for (CustomizeInstallmentDetail customizeInstallmentDetail : customizeInstallment.getCustomizeInstallmentDetails()) {
                	FeeHeadVO   feeHeadVO = (FeeHeadVO) feeHeadMap.get(customizeInstallmentDetail.getFeeHead().getId());
                    InstallmentFeeVO installmentFeeVO = new InstallmentFeeVO();
                    installmentFeeVO.setInstallmentId(customizeInstallment.getInstallment().getId());
                    installmentFeeVO.setInstallmentFeeId(customizeInstallmentDetail.getId());
                    installmentFeeVO.setAmount(customizeInstallmentDetail.getAmount());
                    feeHeadVO.getInstallments().add(installmentFeeVO);
                }
            }
        }
        if (!(feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
            	FeeHeadVO feeHeadVO = (FeeHeadVO) feeHeadMap.get(feeTransactionDetail.getFeeHead().getId());
                if (feeHeadVO != null && feeHeadVO.getInstallments() != null) {
                    for (InstallmentFeeVO installmentFeeVO2 : feeHeadVO.getInstallments()) {
                        if (feeTransactionDetail.getInstallment().getId().equals(installmentFeeVO2.getInstallmentId())) {
                            installmentFeeVO2.setDeposited(installmentFeeVO2.getDeposited() + feeTransactionDetail.getAmount().longValue());
                            break;
                        }
                    }
                }
            }
        }
        List<FeeHeadVO> custInstllments = new ArrayList(feeHeadMap.values());
        for (FeeHeadVO feeHeadVO2 : custInstllments) {
            if (installmentCount > feeHeadVO2.getInstallments().size()) {
                for (int i = feeHeadVO2.getInstallments().size(); i < installmentCount; i++) {
                	InstallmentFeeVO  installmentFeeVO2 = new InstallmentFeeVO();
                    installmentFeeVO2.setInstallmentId(Long.valueOf((long) (i + 1)));
                    feeHeadVO2.getInstallments().add(installmentFeeVO2);
                }
            }
        }
        Collections.sort(custInstllments, new BeanComparator("headName", new NullComparator()));
        if (custInstllments == null) {
            return custInstllments;
        }
        for (FeeHeadVO feeHeadVO22 : custInstllments) {
            if (feeHeadVO22.getInstallments() != null) {
                Collections.sort(feeHeadVO22.getInstallments(), new BeanComparator("installmentId", new NullComparator()));
            }
        }
        return custInstllments;
    }

    public List<InstallmentDueDateVO> prepareCustomizeInstallmentDueDate(List<CustomizeInstallment> customizeInstallments, int installments) {
        List<InstallmentDueDateVO> customizeDueDateVOs = new ArrayList();
        for (CustomizeInstallment customizeInstallment : customizeInstallments) {
            InstallmentDueDateVO customizeDueDateVO = new InstallmentDueDateVO();
            customizeDueDateVO.setCustomizeInstallmentId(customizeInstallment.getId());
            customizeDueDateVO.setDueDate(customizeInstallment.getDueDate());
            if (customizeInstallment.getDueDate() != null) {
                customizeDueDateVO.setDueDateStr(DateUtil.formatDate(customizeInstallment.getDueDate(), "dd-MMM-yyyy"));
            }
            if (customizeInstallment.getLateFeeRule() != null) {
                customizeDueDateVO.setLatefeeRuleId(customizeInstallment.getLateFeeRule().getId());
            }
            customizeDueDateVO.setCustomizeInstallmentId(customizeInstallment.getId());
            customizeDueDateVOs.add(customizeDueDateVO);
        }
        if (installments > customizeDueDateVOs.size()) {
            for (int i = 0; i < installments - customizeDueDateVOs.size(); i++) {
                customizeDueDateVOs.add(new InstallmentDueDateVO());
            }
        }
        return customizeDueDateVOs;
    }

    public List<FeeHeadVO> prepareCustomizedInstallments(AcademicYearFee academicYearFee, int installmentCount, BusFee busFee, List<FeeTransactionDetail> feeTransactionDetails) {
        if (academicYearFee == null) {
            return null;
        }
        if (academicYearFee.getAcademicYearFeeDetails() == null) {
            return null;
        }
        List<InstallmentFeeVO> installments;
        Map<Long, FeeHeadVO> feeHeadMap = new HashMap();
        for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFee.getAcademicYearFeeDetails()) {
            FeeHeadVO feeHeadVO = new FeeHeadVO();
            feeHeadVO.setHeadId(academicYearFeeDetail.getFeeHead().getId());
            feeHeadVO.setHeadName(academicYearFeeDetail.getFeeHead().getName());
            feeHeadVO.setHeadFee(academicYearFeeDetail.getAmount());
            installments = new ArrayList();
            for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFee.getAcademicYearFeeInstallments()) {
                if (academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails() != null) {
                    for (AcademicYearFeeInstallmentDetail academicYearFeeInstallmentDetail : academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                        if (academicYearFeeInstallmentDetail.getFeeHead().getId().equals(feeHeadVO.getHeadId())) {
                            InstallmentFeeVO installmentFeeVO = new InstallmentFeeVO();
                            installmentFeeVO.setInstallmentId(academicYearFeeInstallment.getInstallment().getId());
                            installmentFeeVO.setAmount(academicYearFeeInstallmentDetail.getAmount());
                            installments.add(installmentFeeVO);
                        }
                    }
                }
            }
            feeHeadVO.setInstallments(installments);
            feeHeadMap.put(academicYearFeeDetail.getFeeHead().getId(), feeHeadVO);
        }
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(academicYearFee.getAcademicYear().getId());
        FeeHeadVO busFeeHeadVO = new FeeHeadVO();
        busFeeHeadVO.setHeadId(UserSessionManager.getUserSession(this.webApplicationContext).getBusFeeHead().getId());
        busFeeHeadVO.setHeadName(UserSessionManager.getUserSession(this.webApplicationContext).getBusFeeHead().getName());
        if (busFee != null) {
            busFeeHeadVO.setHeadFee(busFee.getRs());
        } else {
            busFeeHeadVO.setHeadFee(Long.valueOf(0));
        }
        installments = new ArrayList();
        for (AcademicYearFeeInstallment academicYearFeeInstallment2 : academicYearFee.getAcademicYearFeeInstallments()) {
        	InstallmentFeeVO installmentFeeVO = new InstallmentFeeVO();
            installmentFeeVO.setInstallmentId(academicYearFeeInstallment2.getInstallment().getId());
            if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeInstallment.getSetupType())) {
                for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                    if (academicYearFeeInstallment2.getInstallment().getId().equals(busFeeInstallmentDetail.getInstallment().getId())) {
                        if (busFee == null || busFee.getRs() == null || busFeeInstallmentDetail.getFeePercent() == null) {
                            installmentFeeVO.setAmount(Long.valueOf(0));
                        } else {
                            installmentFeeVO.setAmount(Long.valueOf((busFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100));
                        }
                    }
                }
            } else if (BusFeeInstallment.SETUP_TYPE_MANUAL.equals(busFeeInstallment.getSetupType()) && busFee != null && busFee.getBusFeeDetails() != null) {
                for (BusFeeDetail busFeeDetail : busFee.getBusFeeDetails()) {
                    if (academicYearFeeInstallment2.getInstallment().getId().equals(busFeeDetail.getInstallment().getId())) {
                        installmentFeeVO.setAmount(busFeeDetail.getFee());
                        break;
                    }
                }
            }
            installments.add(installmentFeeVO);
        }
        busFeeHeadVO.setInstallments(installments);
        feeHeadMap.put(UserSessionManager.getUserSession(this.webApplicationContext).getBusFeeHead().getId(), busFeeHeadVO);
        if (!(feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
            	FeeHeadVO  feeHeadVO = (FeeHeadVO) feeHeadMap.get(feeTransactionDetail.getFeeHead().getId());
                if (feeHeadVO != null && feeHeadVO.getInstallments() != null) {
                    for (InstallmentFeeVO installmentFeeVO2 : feeHeadVO.getInstallments()) {
                        if (feeTransactionDetail.getInstallment().getId().equals(installmentFeeVO2.getInstallmentId())) {
                            installmentFeeVO2.setDeposited(installmentFeeVO2.getDeposited() + feeTransactionDetail.getAmount().longValue());
                            break;
                        }
                    }
                }
            }
        }
        List<FeeHeadVO> custInstllments = new ArrayList(feeHeadMap.values());
        for (FeeHeadVO feeHeadVO2 : custInstllments) {
            if (installmentCount > feeHeadVO2.getInstallments().size()) {
                for (int i = feeHeadVO2.getInstallments().size(); i < installmentCount; i++) {
                	InstallmentFeeVO  installmentFeeVO2 = new InstallmentFeeVO();
                    installmentFeeVO2.setInstallmentId(Long.valueOf((long) (i + 1)));
                    feeHeadVO2.getInstallments().add(installmentFeeVO2);
                }
            }
        }
        Collections.sort(custInstllments, new BeanComparator("headName", new NullComparator()));
        if (custInstllments == null) {
            return custInstllments;
        }
        for (FeeHeadVO feeHeadVO22 : custInstllments) {
            if (feeHeadVO22.getInstallments() != null) {
                Collections.sort(feeHeadVO22.getInstallments(), new BeanComparator("installmentId", new NullComparator()));
            }
        }
        return custInstllments;
    }

    public List<InstallmentDueDateVO> prepareAcademicYearFeeDueDate(List<AcademicYearFeeInstallment> academicYearFeeInstallments, int installments) {
        List<InstallmentDueDateVO> customizeDueDateVOs = new ArrayList();
        for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
            InstallmentDueDateVO customizeDueDateVO = new InstallmentDueDateVO();
            customizeDueDateVO.setDueDate(academicYearFeeInstallment.getDueDate());
            if (academicYearFeeInstallment.getDueDate() != null) {
                customizeDueDateVO.setDueDateStr(DateUtil.formatDate(academicYearFeeInstallment.getDueDate(), "dd-MMM-yyyy"));
            }
            if (academicYearFeeInstallment.getLateFeeRule() != null) {
                customizeDueDateVO.setLatefeeRuleId(academicYearFeeInstallment.getLateFeeRule().getId());
            }
            customizeDueDateVOs.add(customizeDueDateVO);
        }
        if (installments > customizeDueDateVOs.size()) {
            for (int i = 0; i < installments - customizeDueDateVOs.size(); i++) {
                customizeDueDateVOs.add(new InstallmentDueDateVO());
            }
        }
        return customizeDueDateVOs;
    }

    public List<LateFeeRuleVO> preppareLateFeeRuleToDisplay(List<LateFeeRule> lateFeeRules) {
        List<LateFeeRuleVO> lateFeeRuleVOs = new ArrayList();
        if (lateFeeRules != null) {
            for (LateFeeRule lateFeeRule : lateFeeRules) {
                LateFeeRuleVO lateFeeRuleVO = new LateFeeRuleVO();
                lateFeeRuleVO.setId(lateFeeRule.getId());
                lateFeeRuleVO.setName(lateFeeRule.getName());
                lateFeeRuleVO.setRule(lateFeeRule.getRule());
                lateFeeRuleVOs.add(lateFeeRuleVO);
            }
        }
        return lateFeeRuleVOs;
    }
}
