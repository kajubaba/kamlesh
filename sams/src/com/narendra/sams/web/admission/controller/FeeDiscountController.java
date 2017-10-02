package com.narendra.sams.web.admission.controller;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.web.admin.vo.FeeHeadWiseInstallmentVO;
import com.narendra.sams.web.admin.vo.InstallmentDetailVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/feeDiscount"})
public class FeeDiscountController {
    @Autowired
    private AcademicYearFeeService academicYearFeeService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentId}"})
    public String getCustomizeFeeView(@PathVariable Long studentId, Model model) {
        Student student = this.studentService.getStudentById(studentId);
        if (student != null) {
            AcademicYearFee academicYearFee = this.academicYearFeeService.getAcademicYearFee(student.getAcademicYearClass().getAcademicYear().getId(), student.getAcademicYearClass().getCourseYear().getId(), student.getAdmissionType().getId());
            int installmentCount = academicYearFee.getAcademicYearFeeInstallments().size();
            model.addAttribute("custInstllments", prepareCustomizedInstallments(academicYearFee.getAcademicYearFeeDetails(), installmentCount));
            model.addAttribute("installmentCount", Integer.valueOf(installmentCount));
            model.addAttribute("studentId", student.getId());
        }
        return "student_fee_discount_popup";
    }

    public List<FeeHeadWiseInstallmentVO> prepareCustomizedInstallments(Set<AcademicYearFeeDetail> academicYearFeeDetails, int installmentCount) {
        if (academicYearFeeDetails == null) {
            return null;
        }
        Map<Long, FeeHeadWiseInstallmentVO> feeHeadMap = new HashMap();
        for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFeeDetails) {
            FeeHeadWiseInstallmentVO installmentFeeHeadVO = new FeeHeadWiseInstallmentVO();
            installmentFeeHeadVO.setFeeHeadId(academicYearFeeDetail.getFeeHead().getId());
            installmentFeeHeadVO.setFeeHeadName(academicYearFeeDetail.getFeeHead().getName());
            installmentFeeHeadVO.setAmount(academicYearFeeDetail.getAmount());
            InstallmentDetailVO[] installmentDetailVOArr = new InstallmentDetailVO[installmentCount];
            for (int i = 0; i < installmentDetailVOArr.length; i++) {
                installmentDetailVOArr[i] = new InstallmentDetailVO();
            }
            installmentFeeHeadVO.setInstallmentDetailVOs(installmentDetailVOArr);
            feeHeadMap.put(academicYearFeeDetail.getFeeHead().getId(), installmentFeeHeadVO);
        }
        List<FeeHeadWiseInstallmentVO> custInstllments = new ArrayList(feeHeadMap.values());
        Collections.sort(custInstllments, new BeanComparator("feeHeadName", new NullComparator()));
        return custInstllments;
    }
}
