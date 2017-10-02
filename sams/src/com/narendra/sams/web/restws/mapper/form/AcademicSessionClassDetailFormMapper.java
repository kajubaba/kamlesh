package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.web.restws.form.ClassHeadFeeForm;
import com.narendra.sams.web.restws.vo.AcademicSessionClassDetailVO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AcademicSessionClassDetailFormMapper {
    public static List<AcademicYearFeeDetail> prepareCourseFeeForUpdate(AcademicSessionClassDetailVO academicSessionClassDetailVO) {
        List<AcademicYearFeeDetail> academicYearFeeDetails = new ArrayList();
        for (ClassHeadFeeForm classHeadFeeForm : academicSessionClassDetailVO.getFeeDetails()) {
            AcademicYearFeeDetail newAdmissionFee = new AcademicYearFeeDetail();
            AcademicYearFeeDetail regularAdmissionFee = new AcademicYearFeeDetail();
            newAdmissionFee.setId(classHeadFeeForm.getNewAdmissionFee().getId());
            newAdmissionFee.setAmount(classHeadFeeForm.getNewAdmissionFee().getFee());
            regularAdmissionFee.setId(classHeadFeeForm.getRegularAdmissionFee().getId());
            regularAdmissionFee.setAmount(classHeadFeeForm.getRegularAdmissionFee().getFee());
            academicYearFeeDetails.add(newAdmissionFee);
            academicYearFeeDetails.add(regularAdmissionFee);
        }
        return academicYearFeeDetails;
    }

    public static List<AcademicYearFee> prepareCourseFeeForAdd(CourseYearSetting courseYearSetting, AcademicSessionClassDetailVO academicSessionClassDetailVO) {
        List<AcademicYearFee> academicYearFees = new ArrayList();
        AdmissionType admissionTypeNew = new AdmissionType();
        admissionTypeNew.setId(AdmissionType.NEW_ADMISSION_ID);
        AcademicYearFee newAcademicYearFee = new AcademicYearFee();
        newAcademicYearFee.setAdmissionType(admissionTypeNew);
        newAcademicYearFee.setCourseYearSetting(courseYearSetting);
        newAcademicYearFee.setAcademicYear(courseYearSetting.getAcademicYear());
        newAcademicYearFee.setCourseYear(courseYearSetting.getCourseYear());
        newAcademicYearFee.setAcademicYearFeeDetails(new HashSet());
        AdmissionType admissionTypeRegular = new AdmissionType();
        admissionTypeRegular.setId(AdmissionType.REGULAR_ADMISSION_ID);
        AcademicYearFee regularAcademicYearFee = new AcademicYearFee();
        regularAcademicYearFee.setAdmissionType(admissionTypeRegular);
        regularAcademicYearFee.setCourseYearSetting(courseYearSetting);
        regularAcademicYearFee.setAcademicYear(courseYearSetting.getAcademicYear());
        regularAcademicYearFee.setCourseYear(courseYearSetting.getCourseYear());
        regularAcademicYearFee.setAcademicYearFeeDetails(new HashSet());
        for (ClassHeadFeeForm classHeadFeeForm : academicSessionClassDetailVO.getFeeDetails()) {
            FeeHead feeHead = new FeeHead();
            feeHead.setId(classHeadFeeForm.getHeadId());
            AcademicYearFeeDetail newAcademicYearFeeDetail = new AcademicYearFeeDetail();
            newAcademicYearFeeDetail.setAmount(classHeadFeeForm.getNewAdmissionFee().getFee());
            newAcademicYearFeeDetail.setFeeHead(feeHead);
            newAcademicYearFeeDetail.setAcademicYearFee(newAcademicYearFee);
            newAcademicYearFee.getAcademicYearFeeDetails().add(newAcademicYearFeeDetail);
            AcademicYearFeeDetail regularAcademicYearFeeDetail = new AcademicYearFeeDetail();
            regularAcademicYearFeeDetail.setAmount(classHeadFeeForm.getRegularAdmissionFee().getFee());
            regularAcademicYearFeeDetail.setFeeHead(feeHead);
            regularAcademicYearFeeDetail.setAcademicYearFee(regularAcademicYearFee);
            regularAcademicYearFee.getAcademicYearFeeDetails().add(regularAcademicYearFeeDetail);
        }
        academicYearFees.add(newAcademicYearFee);
        academicYearFees.add(regularAcademicYearFee);
        return academicYearFees;
    }
}
