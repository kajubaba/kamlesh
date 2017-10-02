package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.CourseYearType;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.admin.vo.FeeHeadWiseInstallmentVO;
import com.narendra.sams.web.admin.vo.InstallmentDetailVO;
import com.narendra.sams.web.admin.vo.InstallmentDueDate;
import com.narendra.sams.web.restws.form.ClassHeadFeeForm;
import com.narendra.sams.web.restws.form.HeadFeeForm;
import com.narendra.sams.web.restws.vo.AcademicSessionClassDetailVO;
import com.narendra.sams.web.restws.vo.CourseInstallmentsVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class AcademicSessionClassDetailVOMapper {
    public static List<AcademicSessionClassDetailVO> prepareAcademicSessionClassDetailVOs(List<CourseYearSetting> courseYearSettings) {
        List<AcademicSessionClassDetailVO> academicSessionClassDetailVOs = new ArrayList();
        if (!(courseYearSettings == null || courseYearSettings.isEmpty())) {
            for (CourseYearSetting courseYearSetting : courseYearSettings) {
                academicSessionClassDetailVOs.add(prepareAcademicSessionClassDetailVO(courseYearSetting));
            }
        }
        return academicSessionClassDetailVOs;
    }

    public static AcademicSessionClassDetailVO prepareAcademicSessionClassDetailVO(CourseYearSetting courseYearSetting) {
        if (courseYearSetting == null) {
            return null;
        }
        AcademicSessionClassDetailVO academicSessionClassDetailVO = new AcademicSessionClassDetailVO();
        academicSessionClassDetailVO.setId(courseYearSetting.getId());
        academicSessionClassDetailVO.setAcademicYearId(courseYearSetting.getAcademicYear().getId());
        academicSessionClassDetailVO.setIntake(courseYearSetting.getIntake());
        academicSessionClassDetailVO.setIsActive(courseYearSetting.getActive());
        academicSessionClassDetailVO.setTypeId(courseYearSetting.getCourseYearType().getId());
        if (!(courseYearSetting.getAcademicYearFees() == null || courseYearSetting.getAcademicYearFees().isEmpty())) {
            for (AcademicYearFee academicYearFee : courseYearSetting.getAcademicYearFees()) {
                if (AdmissionType.NEW_ADMISSION_ID.equals(academicYearFee.getAdmissionType().getId())) {
                    academicSessionClassDetailVO.setNewAdmissionFee(academicYearFee.getTotalFee());
                    if (academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty()) {
                        academicSessionClassDetailVO.setNewAdmissionFeeInstallmentConfigured("No");
                    } else {
                        academicSessionClassDetailVO.setNewAdmissionFeeInstallmentConfigured("Yes");
                    }
                } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(academicYearFee.getAdmissionType().getId())) {
                    academicSessionClassDetailVO.setRegularAdmissionFee(academicYearFee.getTotalFee());
                    if (academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty()) {
                        academicSessionClassDetailVO.setRegularAdmissionFeeInstallmentConfigured("No");
                    } else {
                        academicSessionClassDetailVO.setRegularAdmissionFeeInstallmentConfigured("Yes");
                    }
                }
            }
        }
        academicSessionClassDetailVO.setType(courseYearSetting.getCourseYearType().getName());
        if (courseYearSetting.getCourseYear().getCourse().getDuration().shortValue() == (short) 1 && CourseYearType.TYPE_YEAR.equals(courseYearSetting.getCourseYearType().getId()) && courseYearSetting.getAcademicYearClasses().size() == 1) {
            for (AcademicYearClass academicYearClass : courseYearSetting.getAcademicYearClasses()) {
                academicSessionClassDetailVO.setName(academicYearClass.getDisplayName());
            }
        } else {
            academicSessionClassDetailVO.setName(new StringBuilder(String.valueOf(courseYearSetting.getCourseYear().getCourse().getDisplayName())).append(", ").append(courseYearSetting.getCourseYear().getName()).append(" Yr.").toString());
        }
        academicSessionClassDetailVO.setFeeDetails(prepareFeeDetails(courseYearSetting.getAcademicYearFees()));
        return academicSessionClassDetailVO;
    }

    private static List<ClassHeadFeeForm> prepareFeeDetails(Set<AcademicYearFee> academicYearFees) {
        if (academicYearFees == null) {
            return null;
        }
        Map<Long, ClassHeadFeeForm> feeDetailMap = new LinkedHashMap();
        for (AcademicYearFee academicYearFee : academicYearFees) {
            ClassHeadFeeForm classHeadFeeForm;
            HeadFeeForm headFeeForm;
            if (AdmissionType.NEW_ADMISSION_ID.equals(academicYearFee.getAdmissionType().getId())) {
                for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFee.getAcademicYearFeeDetails()) {
                    classHeadFeeForm = (ClassHeadFeeForm) feeDetailMap.get(academicYearFeeDetail.getFeeHead().getId());
                    if (classHeadFeeForm == null) {
                        classHeadFeeForm = new ClassHeadFeeForm();
                    }
                    classHeadFeeForm.setHeadId(academicYearFeeDetail.getFeeHead().getId());
                    classHeadFeeForm.setFeeHead(academicYearFeeDetail.getFeeHead().getName());
                    headFeeForm = new HeadFeeForm();
                    headFeeForm.setId(academicYearFeeDetail.getId());
                    headFeeForm.setFee(academicYearFeeDetail.getAmount());
                    classHeadFeeForm.setNewAdmissionFee(headFeeForm);
                    feeDetailMap.put(classHeadFeeForm.getHeadId(), classHeadFeeForm);
                }
            } else {
                for (AcademicYearFeeDetail academicYearFeeDetail2 : academicYearFee.getAcademicYearFeeDetails()) {
                    classHeadFeeForm = (ClassHeadFeeForm) feeDetailMap.get(academicYearFeeDetail2.getFeeHead().getId());
                    if (classHeadFeeForm == null) {
                        classHeadFeeForm = new ClassHeadFeeForm();
                    }
                    classHeadFeeForm.setHeadId(academicYearFeeDetail2.getFeeHead().getId());
                    classHeadFeeForm.setFeeHead(academicYearFeeDetail2.getFeeHead().getName());
                    headFeeForm = new HeadFeeForm();
                    headFeeForm.setId(academicYearFeeDetail2.getId());
                    headFeeForm.setFee(academicYearFeeDetail2.getAmount());
                    classHeadFeeForm.setRegularAdmissionFee(headFeeForm);
                    feeDetailMap.put(classHeadFeeForm.getHeadId(), classHeadFeeForm);
                }
            }
        }
        List<ClassHeadFeeForm> feeDetails = new ArrayList(feeDetailMap.values());
        Collections.sort(feeDetails, new BeanComparator("feeHead", new NullComparator()));
        return feeDetails;
    }

    public static List<ClassHeadFeeForm> prepareBlankFeeDetails(List<FeeHead> feeHeads) {
        if (feeHeads == null) {
            return null;
        }
        List<ClassHeadFeeForm> classHeadFeeForms = new ArrayList();
        for (FeeHead feeHead : feeHeads) {
            ClassHeadFeeForm classHeadFeeForm = new ClassHeadFeeForm();
            classHeadFeeForm.setHeadId(feeHead.getId());
            classHeadFeeForm.setFeeHead(feeHead.getName());
            classHeadFeeForm.setNewAdmissionFee(new HeadFeeForm());
            classHeadFeeForm.setRegularAdmissionFee(new HeadFeeForm());
            classHeadFeeForms.add(classHeadFeeForm);
        }
        return classHeadFeeForms;
    }

    public static CourseInstallmentsVO prepareAcademicSessionInstallments(AcademicYearFee academicYearFee, Long selectedInstallmentCount, Short admissionTypeId) {
        if (academicYearFee == null) {
            return null;
        }
        CourseInstallmentsVO courseInstallmentDetailVO = new CourseInstallmentsVO();
        courseInstallmentDetailVO.setAdmissionTypeId(admissionTypeId);
        courseInstallmentDetailVO.setCourseYearSettingId(academicYearFee.getCourseYearSetting().getId());
        courseInstallmentDetailVO.setIntake(academicYearFee.getCourseYearSetting().getIntake());
        courseInstallmentDetailVO.setIsActive(academicYearFee.getCourseYearSetting().getActive());
        courseInstallmentDetailVO.setCourseType(academicYearFee.getCourseYearSetting().getCourseYearType().getName());
        courseInstallmentDetailVO.setAcademicYearFeeId(academicYearFee.getId());
        if (academicYearFee.getCourseYearSetting().getCourseYear().getCourse().getDuration().shortValue() == (short) 1 && CourseYearType.YEAR_DB_ID.equals(academicYearFee.getCourseYearSetting().getCourseYearType().getId()) && academicYearFee.getCourseYearSetting().getAcademicYearClasses().size() == 1) {
            for (AcademicYearClass academicYearClass : academicYearFee.getCourseYearSetting().getAcademicYearClasses()) {
                courseInstallmentDetailVO.setCourseName(academicYearClass.getDisplayName());
            }
        } else {
            courseInstallmentDetailVO.setCourseName(new StringBuilder(String.valueOf(academicYearFee.getCourseYearSetting().getCourseYear().getCourse().getDisplayName())).append(", ").append(academicYearFee.getCourseYearSetting().getCourseYear().getName()).append(" Yr.").toString());
        }
        Map<Long, FeeHeadWiseInstallmentVO> feeHeadInstallmentMap = new LinkedHashMap();
        for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFee.getAcademicYearFeeDetails()) {
            FeeHeadWiseInstallmentVO vo = (FeeHeadWiseInstallmentVO) feeHeadInstallmentMap.get(academicYearFeeDetail.getFeeHead().getId());
            if (vo == null) {
                vo = new FeeHeadWiseInstallmentVO();
            }
            vo.setFeeHeadId(academicYearFeeDetail.getFeeHead().getId());
            vo.setFeeHeadName(academicYearFeeDetail.getFeeHead().getName());
            vo.setAmount(academicYearFeeDetail.getAmount());
            InstallmentDetailVO installmentDetailVO;
            if (academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty()) {
                int defaultInstallmentCount = 2;
                if (selectedInstallmentCount == null) {
                    vo.setInstallmentDetailVOs(new InstallmentDetailVO[2]);
                } else {
                    vo.setInstallmentDetailVOs(new InstallmentDetailVO[selectedInstallmentCount.intValue()]);
                    defaultInstallmentCount = selectedInstallmentCount.intValue();
                }
                for (int i = 1; i <= defaultInstallmentCount; i++) {
                    installmentDetailVO = new InstallmentDetailVO();
                    installmentDetailVO.setInstallmentId(new Long((long) i));
                    installmentDetailVO.setAmount(Long.valueOf(0));
                    vo.getInstallmentDetailVOs()[i - 1] = installmentDetailVO;
                }
            } else {
                int noOfInstallments;
                if (selectedInstallmentCount == null) {
                    vo.setInstallmentDetailVOs(new InstallmentDetailVO[academicYearFee.getAcademicYearFeeInstallments().size()]);
                    noOfInstallments = academicYearFee.getAcademicYearFeeInstallments().size();
                } else {
                    vo.setInstallmentDetailVOs(new InstallmentDetailVO[selectedInstallmentCount.intValue()]);
                    noOfInstallments = selectedInstallmentCount.intValue();
                }
                BeanComparator beanComparator = new BeanComparator("installment.id", new NullComparator());
                List<AcademicYearFeeInstallment> list = new ArrayList(academicYearFee.getAcademicYearFeeInstallments());
                Collections.sort(list, beanComparator);
                for (AcademicYearFeeInstallment academicYearFeeInstallment : list) {
                    if (academicYearFeeInstallment.getInstallment().getId().longValue() <= ((long) noOfInstallments)) {
                        for (AcademicYearFeeInstallmentDetail academicYearFeeInstallmentDetail : academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                            if (academicYearFeeInstallmentDetail.getFeeHead().getId().equals(academicYearFeeDetail.getFeeHead().getId())) {
                                installmentDetailVO = new InstallmentDetailVO();
                                installmentDetailVO.setAcademicYearFeeInstallmentId(academicYearFeeInstallment.getId());
                                installmentDetailVO.setAcademicYearFeeInstallmentDetailId(academicYearFeeInstallmentDetail.getId());
                                installmentDetailVO.setInstallmentId(Long.valueOf(academicYearFeeInstallment.getInstallment().getId().longValue()));
                                installmentDetailVO.setAmount(academicYearFeeInstallmentDetail.getAmount());
                                vo.getInstallmentDetailVOs()[academicYearFeeInstallment.getInstallment().getId().intValue() - 1] = installmentDetailVO;
                                break;
                            }
                        }
                    }
                }
                if (selectedInstallmentCount != null && selectedInstallmentCount.longValue() > ((long) academicYearFee.getAcademicYearFeeInstallments().size())) {
                    for (Integer i2 = Integer.valueOf(academicYearFee.getAcademicYearFeeInstallments().size()); ((long) i2.intValue()) < selectedInstallmentCount.longValue(); i2 = Integer.valueOf(i2.intValue() + 1)) {
                        installmentDetailVO = new InstallmentDetailVO();
                        installmentDetailVO.setInstallmentId(Long.valueOf(new Long((long) i2.intValue()).longValue() + 1));
                        installmentDetailVO.setAmount(Long.valueOf(0));
                        vo.getInstallmentDetailVOs()[i2.intValue()] = installmentDetailVO;
                    }
                }
            }
            feeHeadInstallmentMap.put(vo.getFeeHeadId(), vo);
        }
        List<FeeHeadWiseInstallmentVO> feeHeadWiseInstallmentVOs = new ArrayList(feeHeadInstallmentMap.values());
        Collections.sort(feeHeadWiseInstallmentVOs, new BeanComparator("feeHeadName", new NullComparator()));
        courseInstallmentDetailVO.setHeadwiseInstallments(feeHeadWiseInstallmentVOs);
        courseInstallmentDetailVO.setInstallmentDueDates(prepareInstallmentDueDates(academicYearFee, selectedInstallmentCount));
        courseInstallmentDetailVO.setInstallments(Long.valueOf((long) courseInstallmentDetailVO.getInstallmentDueDates().size()));
        return courseInstallmentDetailVO;
    }

    private static List<InstallmentDueDate> prepareInstallmentDueDates(AcademicYearFee academicYearFee, Long selectedInstallmentCount) {
        List<InstallmentDueDate> installmentDueDates = new ArrayList();
        InstallmentDueDate installmentDueDate;
        if (academicYearFee != null && academicYearFee.getAcademicYearFeeInstallments() != null && !academicYearFee.getAcademicYearFeeInstallments().isEmpty()) {
            int noOfInstallments;
            if (selectedInstallmentCount == null) {
                noOfInstallments = academicYearFee.getAcademicYearFeeInstallments().size();
            } else {
                noOfInstallments = selectedInstallmentCount.intValue();
            }
            BeanComparator beanComparator = new BeanComparator("installment.id", new NullComparator());
            List<AcademicYearFeeInstallment> academicYearFeeInstallments = new ArrayList(academicYearFee.getAcademicYearFeeInstallments());
            Collections.sort(academicYearFeeInstallments, beanComparator);
            for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                if (academicYearFeeInstallment.getInstallment().getId().longValue() > ((long) noOfInstallments)) {
                    break;
                }
                installmentDueDate = new InstallmentDueDate();
                installmentDueDate.setInstallmentId(academicYearFeeInstallment.getInstallment().getId());
                if (academicYearFeeInstallment.getDueDate() != null) {
                    installmentDueDate.setDueDateStr(DateUtil.formatDate(academicYearFeeInstallment.getDueDate(), "dd-MMM-yyyy"));
                }
                if (academicYearFeeInstallment.getLateFeeRule() != null) {
                    installmentDueDate.setLateFeeRuleId(academicYearFeeInstallment.getLateFeeRule().getId());
                }
                installmentDueDates.add(installmentDueDate);
            }
            if (noOfInstallments > academicYearFee.getAcademicYearFeeInstallments().size()) {
                for (Integer i = Integer.valueOf(academicYearFee.getAcademicYearFeeInstallments().size() + 1); i.intValue() <= noOfInstallments; i = Integer.valueOf(i.intValue() + 1)) {
                    installmentDueDate = new InstallmentDueDate();
                    installmentDueDate.setInstallmentId(Long.valueOf((long) i.intValue()));
                    installmentDueDates.add(installmentDueDate);
                }
            }
        } else if (selectedInstallmentCount != null) {
            for (int i = 0;  i < selectedInstallmentCount.longValue(); i++) {
                installmentDueDate = new InstallmentDueDate();
                installmentDueDate.setInstallmentId(Long.valueOf((long) (i + 1)));
                installmentDueDates.add(installmentDueDate);
            }
        } else {
            for (int i = 0; i < 2; i++) {
                installmentDueDate = new InstallmentDueDate();
                installmentDueDate.setInstallmentId(Long.valueOf((long) (i + 1)));
                installmentDueDates.add(installmentDueDate);
            }
        }
        Collections.sort(installmentDueDates, new BeanComparator("installmentId", new NullComparator()));
        return installmentDueDates;
    }
}
