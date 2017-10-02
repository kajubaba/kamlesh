package com.narendra.sams.web.admin.controller;

import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.service.AcademicYearSettingService;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.web.admin.vo.AcademicYearFeeForm;
import com.narendra.sams.web.admin.vo.AcademicyearFeeDetailForm;
import com.narendra.sams.web.admin.vo.NewAdmissionFeeForm;
import com.narendra.sams.web.admin.vo.RegularAdmissionFeeForm;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/admin/academicyear/fee"})
public class AcademicYearFeeController {
    @Autowired
    private AcademicYearFeeService academicYearFeeService;
    @Autowired
    private AcademicYearSettingService academicYearSettingService;
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/popup"})
    public String getCourseYearFeeView(@RequestParam Long courseYearSettingId, Model model) {
        List<AcademicYearFee> academicYearFeeList = this.academicYearFeeService.getAcademicYearFeeForAllAdmissionType(courseYearSettingId);
        Map<Long, AcademicyearFeeDetailForm> feeVOMap = new LinkedHashMap();
        if (academicYearFeeList == null || (academicYearFeeList != null && academicYearFeeList.isEmpty())) {
            List<FeeHead> feeHeads = this.feeHeadService.getAllActiveFeeHeads(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
            if (!(feeHeads == null || feeHeads.isEmpty())) {
                for (FeeHead feeHead : feeHeads) {
                    AcademicyearFeeDetailForm academicyearFeeDetailForm = new AcademicyearFeeDetailForm();
                    academicyearFeeDetailForm.setFeeHeadId(feeHead.getId());
                    academicyearFeeDetailForm.setFeeHeadName(feeHead.getName());
                    feeVOMap.put(feeHead.getId(), academicyearFeeDetailForm);
                }
            }
        } else {
            for (AcademicYearFee academicYearFee : academicYearFeeList) {
                AcademicyearFeeDetailForm feeVO;
                if ("new".equalsIgnoreCase(academicYearFee.getAdmissionType().getName())) {
                    for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFee.getAcademicYearFeeDetails()) {
                        feeVO = (AcademicyearFeeDetailForm) feeVOMap.get(academicYearFeeDetail.getFeeHead().getId());
                        if (feeVO == null) {
                            feeVO = new AcademicyearFeeDetailForm();
                        }
                        feeVO.setFeeHeadId(academicYearFeeDetail.getFeeHead().getId());
                        feeVO.setFeeHeadName(academicYearFeeDetail.getFeeHead().getName());
                        NewAdmissionFeeForm newAdmissionFeeForm = new NewAdmissionFeeForm();
                        newAdmissionFeeForm.setId(academicYearFeeDetail.getId());
                        newAdmissionFeeForm.setAmount(academicYearFeeDetail.getAmount());
                        feeVO.setNewAdmissionFeeForm(newAdmissionFeeForm);
                        feeVOMap.put(feeVO.getFeeHeadId(), feeVO);
                    }
                } else {
                    for (AcademicYearFeeDetail academicYearFeeDetail2 : academicYearFee.getAcademicYearFeeDetails()) {
                        feeVO = (AcademicyearFeeDetailForm) feeVOMap.get(academicYearFeeDetail2.getFeeHead().getId());
                        if (feeVO == null) {
                            feeVO = new AcademicyearFeeDetailForm();
                        }
                        feeVO.setFeeHeadId(academicYearFeeDetail2.getFeeHead().getId());
                        feeVO.setFeeHeadName(academicYearFeeDetail2.getFeeHead().getName());
                        RegularAdmissionFeeForm regularAdmissionFeeForm = new RegularAdmissionFeeForm();
                        regularAdmissionFeeForm.setId(academicYearFeeDetail2.getId());
                        regularAdmissionFeeForm.setAmount(academicYearFeeDetail2.getAmount());
                        feeVO.setRegularAdmissionFeeForm(regularAdmissionFeeForm);
                        feeVOMap.put(feeVO.getFeeHeadId(), feeVO);
                    }
                }
            }
        }
        List<AcademicyearFeeDetailForm> academicyearFeeDetailForms = new ArrayList(feeVOMap.values());
        sortList(academicyearFeeDetailForms);
        model.addAttribute("academicyearFeeDetailForms", academicyearFeeDetailForms);
        model.addAttribute("courseYearSettingId", courseYearSettingId);
        return "admin_fee_popup";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public String saveFeeChanges(Long courseYearSettingId, AcademicYearFeeForm academicYearFeeForm, Model model) {
        CourseYearSetting courseYearSetting = this.academicYearSettingService.getCourseYearSetting(courseYearSettingId);
        if (!(academicYearFeeForm.getAcademicyearFeeDetailForms() == null || academicYearFeeForm.getAcademicyearFeeDetailForms().isEmpty())) {
            if (((AcademicyearFeeDetailForm) academicYearFeeForm.getAcademicyearFeeDetailForms().get(0)).getNewAdmissionFeeForm().getId() != null) {
                updateAcademicYearFee(academicYearFeeForm);
            } else {
                saveAcademicYearFee(courseYearSetting, academicYearFeeForm.getAcademicyearFeeDetailForms());
            }
        }
        List<AcademicYearFee> academicYearFees = this.academicYearFeeService.getAcademicYearFeeForAllAdmissionType(courseYearSettingId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        if (!(academicYearFees == null || academicYearFees.isEmpty())) {
            for (AcademicYearFee academicYearFee : academicYearFees) {
                if (Integer.valueOf(1).shortValue() == academicYearFee.getAdmissionType().getId().shortValue()) {
                    jsonObject.put("newFee", academicYearFee.getTotalFee() + "(N)");
                } else {
                    jsonObject.put("regularFee", academicYearFee.getTotalFee() + "(R)");
                }
            }
        }
        return jsonObject.toString();
    }

    public void saveAcademicYearFee(CourseYearSetting courseYearSetting, List<AcademicyearFeeDetailForm> academicyearFeeDetailForms) {
        List<AcademicYearFee> academicYearFees = new ArrayList();
        AdmissionType admissionTypeNew = new AdmissionType();
        AdmissionType admissionTypeRegular = new AdmissionType();
        admissionTypeNew.setId(Short.valueOf(Integer.valueOf(1).shortValue()));
        admissionTypeRegular.setId(Short.valueOf(Integer.valueOf(2).shortValue()));
        AcademicYearFee newAcademicYearFee = new AcademicYearFee();
        newAcademicYearFee.setAdmissionType(admissionTypeNew);
        newAcademicYearFee.setCourseYearSetting(courseYearSetting);
        newAcademicYearFee.setAcademicYear(courseYearSetting.getAcademicYear());
        newAcademicYearFee.setCourseYear(courseYearSetting.getCourseYear());
        newAcademicYearFee.setAcademicYearFeeDetails(new HashSet());
        AcademicYearFee regularAcademicYearFee = new AcademicYearFee();
        regularAcademicYearFee.setAdmissionType(admissionTypeRegular);
        regularAcademicYearFee.setCourseYearSetting(courseYearSetting);
        regularAcademicYearFee.setAcademicYear(courseYearSetting.getAcademicYear());
        regularAcademicYearFee.setCourseYear(courseYearSetting.getCourseYear());
        regularAcademicYearFee.setAcademicYearFeeDetails(new HashSet());
        for (AcademicyearFeeDetailForm academicyearFeeDetailForm : academicyearFeeDetailForms) {
            FeeHead feeHead = new FeeHead();
            feeHead.setId(academicyearFeeDetailForm.getFeeHeadId());
            AcademicYearFeeDetail newAcademicYearFeeDetail = new AcademicYearFeeDetail();
            newAcademicYearFeeDetail.setAmount(academicyearFeeDetailForm.getNewAdmissionFeeForm().getAmount());
            newAcademicYearFeeDetail.setFeeHead(feeHead);
            newAcademicYearFeeDetail.setAcademicYearFee(newAcademicYearFee);
            newAcademicYearFee.getAcademicYearFeeDetails().add(newAcademicYearFeeDetail);
            AcademicYearFeeDetail regularAcademicYearFeeDetail = new AcademicYearFeeDetail();
            regularAcademicYearFeeDetail.setAmount(academicyearFeeDetailForm.getRegularAdmissionFeeForm().getAmount());
            regularAcademicYearFeeDetail.setFeeHead(feeHead);
            regularAcademicYearFeeDetail.setAcademicYearFee(regularAcademicYearFee);
            regularAcademicYearFee.getAcademicYearFeeDetails().add(regularAcademicYearFeeDetail);
        }
        academicYearFees.add(newAcademicYearFee);
        academicYearFees.add(regularAcademicYearFee);
        this.academicYearFeeService.saveCourseYearFee(academicYearFees);
    }

    public void updateAcademicYearFee(AcademicYearFeeForm academicYearFeeForm) {
        List<AcademicYearFeeDetail> academicYearFeeDetails = new ArrayList();
        for (AcademicyearFeeDetailForm academicyearFeeDetailForm : academicYearFeeForm.getAcademicyearFeeDetailForms()) {
            AcademicYearFeeDetail academicYearFeeDetail1 = new AcademicYearFeeDetail();
            AcademicYearFeeDetail academicYearFeeDetail2 = new AcademicYearFeeDetail();
            academicYearFeeDetail1.setId(academicyearFeeDetailForm.getNewAdmissionFeeForm().getId());
            academicYearFeeDetail1.setAmount(academicyearFeeDetailForm.getNewAdmissionFeeForm().getAmount());
            academicYearFeeDetail2.setId(academicyearFeeDetailForm.getRegularAdmissionFeeForm().getId());
            academicYearFeeDetail2.setAmount(academicyearFeeDetailForm.getRegularAdmissionFeeForm().getAmount());
            academicYearFeeDetails.add(academicYearFeeDetail1);
            academicYearFeeDetails.add(academicYearFeeDetail2);
        }
        this.academicYearFeeService.updateCourseYearFeeDetail(academicYearFeeDetails);
    }

    public void sortList(List<AcademicyearFeeDetailForm> list) {
        Collections.sort(list, new BeanComparator("feeHeadName", new NullComparator()));
    }
}
