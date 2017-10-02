package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.FeeDiscount;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.FeeAdjustedStudentService;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.fee.vo.CountVO;
import com.narendra.sams.web.restws.fee.vo.FeeAdjustedStudentVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/fee/adjusted/students"})
public class FeeAdjustedStudentListRestController {
    @Autowired
    private AcademicYearFeeService academicYearFeeService;
    @Autowired
    private CustomizeStudentFeeService customizeStudentFeeService;
    @Autowired
    private FeeAdjustedStudentService feeAdjustedStudentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/count"})
    public CountVO count() {
        Long admissionAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        CountVO countVO = new CountVO();
        countVO.setCount(this.feeAdjustedStudentService.getFeeAdjustedStudentCount(admissionAcademicYear));
        countVO.setCountOf("Fee Adjusted Students");
        return countVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<FeeAdjustedStudentVO> studentList(Long academicYearId) {
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        }
        return prepareStudentList(this.feeAdjustedStudentService.getStudentsWhoseFeeIsCustomized(academicYearId, null), academicYearId);
    }

    private List<FeeAdjustedStudentVO> prepareStudentList(List<Student> students, Long academicYearId) {
        List<FeeAdjustedStudentVO> feeAdjustedStudentVOs = new ArrayList();
        if (students != null) {
            for (Student student : students) {
                FeeAdjustedStudentVO feeAdjustedStudentVO = new FeeAdjustedStudentVO();
                feeAdjustedStudentVO.setId(student.getId());
                feeAdjustedStudentVO.setStudentId(student.getStudentId());
                feeAdjustedStudentVO.setStudentName(student.getFullName());
                ClassHistory feeAdjustedClass = student.getActiveClassHistory(academicYearId);
                if (feeAdjustedClass != null) {
                    feeAdjustedStudentVO.setFeeAdjustedClassId(feeAdjustedClass.getId());
                    feeAdjustedStudentVO.setFeeAdjustedClass(StudentInformationUtil.getClassName(feeAdjustedClass.getAcademicYearClass()));
                    if (feeAdjustedClass.getAdmissionScheme() != null) {
                        feeAdjustedStudentVO.setSchemeName(feeAdjustedClass.getAdmissionScheme().getName());
                    } else {
                        feeAdjustedStudentVO.setSchemeName("");
                    }
                    List<FeeDiscount> feeDiscounts = this.customizeStudentFeeService.getFeeDiscounts(student.getId(), this.academicYearFeeService.getAcademicYearFee(academicYearId, feeAdjustedClass.getAcademicYearClass().getCourseYear().getId(), feeAdjustedClass.getAdmissionType().getId()).getId());
                    long discount = 0;
                    if (feeDiscounts != null) {
                        for (FeeDiscount feeDiscount : feeDiscounts) {
                            if (feeDiscount.getAmount() != null) {
                                discount += feeDiscount.getAmount().longValue();
                            }
                        }
                    }
                    feeAdjustedStudentVO.setDiscountGiven(Long.valueOf(discount));
                }
                List<CustomizeInstallment> customizeInstallments = student.getInstallments(academicYearId);
                if (customizeInstallments != null) {
                    feeAdjustedStudentVO.setNoOfInstallments(Long.valueOf((long) customizeInstallments.size()));
                } else {
                    feeAdjustedStudentVO.setNoOfInstallments(Long.valueOf(0));
                }
                feeAdjustedStudentVOs.add(feeAdjustedStudentVO);
            }
        }
        return feeAdjustedStudentVOs;
    }
}
