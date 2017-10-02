package com.narendra.sams.web.restws.core;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.service.AcademicYearSettingService;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.fee.service.PaidFeeService;
import com.narendra.sams.web.admin.vo.FeeHeadWiseInstallmentVO;
import com.narendra.sams.web.admin.vo.InstallmentDetailVO;
import com.narendra.sams.web.admin.vo.InstallmentDueDate;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.ClassHeadFeeForm;
import com.narendra.sams.web.restws.form.NotAddedCourseForm;
import com.narendra.sams.web.restws.mapper.form.AcademicSessionClassDetailFormMapper;
import com.narendra.sams.web.restws.mapper.form.CourseYearSettingForm;
import com.narendra.sams.web.restws.mapper.vo.AcademicSessionClassDetailVOMapper;
import com.narendra.sams.web.restws.mapper.vo.CourseVOMapper;
import com.narendra.sams.web.restws.vo.AcademicSessionClassDetailVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.CourseInstallmentsVO;
import com.narendra.sams.web.restws.vo.CourseVO;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/academic-session/class"})
public class AcademicSessionClassDetailRestController {
    @Autowired
    private AcademicYearFeeService academicYearFeeService;
    @Autowired
    private AcademicYearSettingService academicYearSettingService;
    @Autowired
    private AdmissionListService admissionListService;
    @Autowired
    private CustomizeStudentFeeService customizeStudentFeeService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private PaidFeeService paidFeeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/{academicSessionId}"})
    public List<AcademicSessionClassDetailVO> getAcademicSessionClasses(@PathVariable Long academicSessionId) {
        return AcademicSessionClassDetailVOMapper.prepareAcademicSessionClassDetailVOs(this.academicYearSettingService.getInvidualCourseSettings(academicSessionId, null));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/details/{courseYearSettingId}"})
    public AcademicSessionClassDetailVO getAcademicSessionClasseDetail(@PathVariable Long courseYearSettingId) {
        AcademicSessionClassDetailVO academicSessionClassDetailVO = AcademicSessionClassDetailVOMapper.prepareAcademicSessionClassDetailVO(this.academicYearSettingService.getCourseYearSetting(courseYearSettingId));
        if (academicSessionClassDetailVO.getFeeDetails() == null || (academicSessionClassDetailVO.getFeeDetails() != null && academicSessionClassDetailVO.getFeeDetails().isEmpty())) {
            academicSessionClassDetailVO.setIsFeeConfigured(Boolean.FALSE);
            academicSessionClassDetailVO.setFeeDetails(AcademicSessionClassDetailVOMapper.prepareBlankFeeDetails(this.feeHeadService.getAllActiveFeeHeads(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId())));
        } else {
            academicSessionClassDetailVO.setIsFeeConfigured(Boolean.TRUE);
        }
        return academicSessionClassDetailVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/savefee"})
    public AjaxResponse saveClassFee(@RequestBody AcademicSessionClassDetailVO classDetails) {
        if (((ClassHeadFeeForm) classDetails.getFeeDetails().get(0)).getNewAdmissionFee().getId() != null) {
            this.academicYearFeeService.updateCourseYearFeeDetail(AcademicSessionClassDetailFormMapper.prepareCourseFeeForUpdate(classDetails));
        } else {
            this.academicYearFeeService.saveCourseYearFee(AcademicSessionClassDetailFormMapper.prepareCourseFeeForAdd(this.academicYearSettingService.getCourseYearSetting(classDetails.getId()), classDetails));
        }
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/installments/{courseYearSettingId}/{admissionTypeId}"})
    public CourseInstallmentsVO getInstallments(@PathVariable Long courseYearSettingId, @PathVariable Short admissionTypeId, Long installments) {
        AcademicYearFee academicYearFee = null;
        if (AdmissionType.NEW_ADMISSION_ID.equals(admissionTypeId)) {
            academicYearFee = this.academicYearFeeService.getAcademicYearFeeForNewAdmissions(courseYearSettingId);
        } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(admissionTypeId)) {
            academicYearFee = this.academicYearFeeService.getAcademicYearFeeForRegularAdmissions(courseYearSettingId);
        }
        return AcademicSessionClassDetailVOMapper.prepareAcademicSessionInstallments(academicYearFee, installments, admissionTypeId);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/installments"})
    public AjaxResponse saveInstallments(@RequestBody CourseInstallmentsVO classDetails) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        if (this.paidFeeService.isFeePaidInCourseYearSetting(classDetails.getCourseYearSettingId(), classDetails.getAdmissionTypeId()).booleanValue()) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
        } else if (this.customizeStudentFeeService.isCourseYearFeeAdjusted(classDetails.getCourseYearSettingId(), classDetails.getAdmissionTypeId()).booleanValue()) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
        } else {
            List<AcademicYearFeeInstallment> academicYearFeeInstallments = prepareAcademicYearFeeInstallmentsToUpdate(classDetails);
            if (classDetails.getInstallmentDueDates() != null) {
                for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                    for (InstallmentDueDate installmentDueDate : classDetails.getInstallmentDueDates()) {
                        if (installmentDueDate.getInstallmentId().equals(academicYearFeeInstallment.getInstallment().getId())) {
                            LateFeeRule lateFeeRule = new LateFeeRule();
                            lateFeeRule.setId(installmentDueDate.getLateFeeRuleId());
                            academicYearFeeInstallment.setLateFeeRule(lateFeeRule);
                            if (!(installmentDueDate.getDueDateStr() == null || "".equals(installmentDueDate.getDueDateStr()))) {
                                try {
                                    academicYearFeeInstallment.setDueDate(DateUtil.parseDate(installmentDueDate.getDueDateStr(), "dd-MMM-yyyy"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
            this.academicYearFeeService.saveAcademicYearFeeInstallments(academicYearFeeInstallments);
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/installmentsduedateandlatefeerule"})
    public AjaxResponse saveInstallmentsDueDateAndLateFeeRule(@RequestBody CourseInstallmentsVO classDetails) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        List<AcademicYearFeeInstallment> academicYearFeeInstallments = prepareAcademicYearFeeInstallmentsToUpdate(classDetails);
        if (classDetails.getInstallmentDueDates() != null) {
            for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                for (InstallmentDueDate installmentDueDate : classDetails.getInstallmentDueDates()) {
                    if (installmentDueDate.getInstallmentId().equals(academicYearFeeInstallment.getInstallment().getId())) {
                        LateFeeRule lateFeeRule = new LateFeeRule();
                        lateFeeRule.setId(installmentDueDate.getLateFeeRuleId());
                        academicYearFeeInstallment.setLateFeeRule(lateFeeRule);
                        if (!(installmentDueDate.getDueDateStr() == null || "".equals(installmentDueDate.getDueDateStr()))) {
                            try {
                                academicYearFeeInstallment.setDueDate(DateUtil.parseDate(installmentDueDate.getDueDateStr(), "dd-MMM-yyyy"));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        this.academicYearFeeService.updateInstallmentDueDateAndLateFeeRule(academicYearFeeInstallments);
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/not-added-course/{academicSessionId}"})
    public List<CourseVO> getNotAddedCourses(@PathVariable Long academicSessionId) {
        return CourseVOMapper.prepareCourseVOs(this.academicYearSettingService.getNotAddedCourses(academicSessionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add-course-in-academic-session"})
    public AjaxResponse addCoursesInAcademicYear(@RequestBody NotAddedCourseForm notAddedCourseForm) {
        this.academicYearSettingService.addCoursesInAcademicYearWithDefaultSettings(notAddedCourseForm.getAcademicSessionId(), notAddedCourseForm.getCourseIds());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update/course-year"})
    public AjaxResponse updateCourseYear(@RequestBody CourseYearSettingForm courseYearSettingForm) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        CourseYearSetting courseYearSetting = this.academicYearSettingService.getCourseYearSetting(courseYearSettingForm.getCourseYearSettingId());
        if (!courseYearSettingForm.getCourseYearTypeId().equals(courseYearSetting.getCourseYearType().getId())) {
            Collection<Long> classes = new HashSet();
            for (AcademicYearClass academicYearClass : courseYearSetting.getAcademicYearClasses()) {
                classes.add(academicYearClass.getId());
            }
            List<ClassHistory> classHistories = this.admissionListService.getAdmissionsByClass(classes);
            if (classHistories != null && !classHistories.isEmpty()) {
                ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
                return ajaxResponse;
            } else if (this.enquiryService.isEnquiriesExists(classes).booleanValue()) {
                ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
                return ajaxResponse;
            }
        }
        this.academicYearSettingService.saveCourseYearSetting(courseYearSettingForm.getCourseYearSettingId(), courseYearSettingForm.getActive(), courseYearSettingForm.getCourseYearTypeId(), courseYearSettingForm.getIntake());
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    public List<AcademicYearFeeInstallment> prepareAcademicYearFeeInstallmentsToUpdate(CourseInstallmentsVO courseInstallmentDetail) {
        if (courseInstallmentDetail.getHeadwiseInstallments() == null) {
            return null;
        }
        AcademicYearFee academicYearFee = new AcademicYearFee();
        academicYearFee.setId(courseInstallmentDetail.getAcademicYearFeeId());
        Map<Long, AcademicYearFeeInstallment> ademicYearFeeInstallmentMap = new HashMap();
        for (FeeHeadWiseInstallmentVO feeHeadWiseInstallmentVO : courseInstallmentDetail.getHeadwiseInstallments()) {
            for (InstallmentDetailVO installmentDetailVO : feeHeadWiseInstallmentVO.getInstallmentDetailVOs()) {
                AcademicYearFeeInstallment academicYearFeeInstallment = (AcademicYearFeeInstallment) ademicYearFeeInstallmentMap.get(installmentDetailVO.getInstallmentId());
                if (academicYearFeeInstallment == null) {
                    academicYearFeeInstallment = new AcademicYearFeeInstallment();
                    academicYearFeeInstallment.setId(installmentDetailVO.getAcademicYearFeeInstallmentId());
                    academicYearFeeInstallment.setAcademicYearFee(academicYearFee);
                    academicYearFeeInstallment.setAcademicYearFeeInstallmentDetails(new HashSet());
                    Installment installment = new Installment();
                    installment.setId(installmentDetailVO.getInstallmentId());
                    academicYearFeeInstallment.setInstallment(installment);
                    ademicYearFeeInstallmentMap.put(installmentDetailVO.getInstallmentId(), academicYearFeeInstallment);
                }
                AcademicYearFeeInstallmentDetail academicYearFeeInstallmentDetail = new AcademicYearFeeInstallmentDetail();
                academicYearFeeInstallmentDetail.setId(installmentDetailVO.getAcademicYearFeeInstallmentDetailId());
                academicYearFeeInstallmentDetail.setAmount(installmentDetailVO.getAmount());
                academicYearFeeInstallmentDetail.setAcademicYearFeeInstallment(academicYearFeeInstallment);
                FeeHead feeHead = new FeeHead();
                feeHead.setId(feeHeadWiseInstallmentVO.getFeeHeadId());
                academicYearFeeInstallmentDetail.setFeeHead(feeHead);
                academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails().add(academicYearFeeInstallmentDetail);
            }
        }
        return new ArrayList(ademicYearFeeInstallmentMap.values());
    }
}
