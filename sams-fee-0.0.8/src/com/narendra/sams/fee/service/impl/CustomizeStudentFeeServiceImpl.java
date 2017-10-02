package com.narendra.sams.fee.service.impl;

import com.narendra.sams.admission.dao.CustomizeStudentFeeDAO;
import com.narendra.sams.admission.domain.CustomizeFee;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.domain.FeeCustomizeComments;
import com.narendra.sams.admission.domain.FeeDiscount;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.dao.StudentFeeDAO;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.fee.service.StudentFeeService;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CustomizeStudentFeeServiceImpl implements CustomizeStudentFeeService {
    private CustomizeStudentFeeDAO customizeStudentFeeDAO;
    private StudentFeeDAO studentFeeDAO;
    private StudentFeeService studentFeeService;
    private UserViewDAO userViewDAO;

    public StudentFeeService getStudentFeeService() {
        return this.studentFeeService;
    }

    public void setStudentFeeService(StudentFeeService studentFeeService) {
        this.studentFeeService = studentFeeService;
    }

    public StudentFeeDAO getStudentFeeDAO() {
        return this.studentFeeDAO;
    }

    public void setStudentFeeDAO(StudentFeeDAO studentFeeDAO) {
        this.studentFeeDAO = studentFeeDAO;
    }

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public CustomizeStudentFeeDAO getCustomizeStudentFeeDAO() {
        return this.customizeStudentFeeDAO;
    }

    public void setCustomizeStudentFeeDAO(CustomizeStudentFeeDAO customizeStudentFeeDAO) {
        this.customizeStudentFeeDAO = customizeStudentFeeDAO;
    }

    public void save(List<FeeDiscount> feeDiscounts, Boolean isCustomized, List<CustomizeInstallmentDetail> customizeInstallmentDetails, List<CustomizeInstallment> updateList, List<CustomizeInstallment> addList, List<CustomizeInstallment> customizeInstallments, FeeCustomizeComments feeCustomizeComments, Long userId) {
        saveOrUpdateStudentDiscount(feeDiscounts, userId);
        if (isCustomized.booleanValue()) {
            updateCustomizeFee(customizeInstallmentDetails, userId);
            updateCustomizeInstallmentDueDate(updateList);
            saveCustomizeInstallments(addList, userId);
        } else {
            saveCustomizeInstallments(customizeInstallments, userId);
        }
        saveOrUpdateComments(feeCustomizeComments);
    }

    public void saveOrUpdateStudentDiscount(List<FeeDiscount> feeDiscounts, Long userId) {
        if (feeDiscounts != null) {
            UserView user = this.userViewDAO.loadUser(userId);
            Date dateTime = DateUtil.getSystemDateTime();
            for (FeeDiscount feeDiscount : feeDiscounts) {
                if (feeDiscount.getId() != null) {
                    feeDiscount.setModifiedBy(user);
                    feeDiscount.setModifiedDate(dateTime);
                } else {
                    feeDiscount.setCreatedBy(user);
                    feeDiscount.setCreatedDate(dateTime);
                    feeDiscount.setModifiedBy(user);
                    feeDiscount.setModifiedDate(dateTime);
                }
            }
        }
        this.customizeStudentFeeDAO.saveOrUpdateStudentDiscount(feeDiscounts);
    }

    public List<FeeDiscount> getFeeDiscounts(Long studentId, Long academicYearFeeId) {
        return this.customizeStudentFeeDAO.getFeeDiscounts(studentId, academicYearFeeId);
    }

    public void saveCustomizeInstallments(List<CustomizeInstallment> customizeInstallments, Long userId) {
        if (customizeInstallments != null) {
            UserView user = this.userViewDAO.loadUser(userId);
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                customizeInstallment.setCreatedBy(user);
                customizeInstallment.setModifiedBy(user);
                customizeInstallment.setCreatedDate(DateUtil.getSystemDateTime());
                customizeInstallment.setModifiedDate(DateUtil.getSystemDateTime());
            }
            this.customizeStudentFeeDAO.saveCustomizeInstallments(customizeInstallments);
            for (CustomizeInstallment customizeInstallment2 : customizeInstallments) {
                List<FeeTransaction> feeTransactions = this.studentFeeDAO.getFeeTransactionsOnAcademicYearFee(customizeInstallment2.getStudent().getId(), customizeInstallment2.getAcademicYearFee().getId(), customizeInstallment2.getInstallment().getId());
                if (!(feeTransactions == null || feeTransactions.isEmpty())) {
                    for (FeeTransaction feeTransaction : feeTransactions) {
                        feeTransaction.setAcademicYearFeeInstallment(null);
                        feeTransaction.setCustomizeInstallment(customizeInstallment2);
                    }
                }
                DaysOverdue daysOverdue = this.studentFeeService.getDaysOverdue(customizeInstallment2.getStudent().getId(), customizeInstallment2.getAcademicYearFee().getAcademicYear().getId(), customizeInstallment2.getInstallment().getId());
                if (daysOverdue != null) {
                    daysOverdue.setAcademicYearFeeInstallment(null);
                    daysOverdue.setCustomizeInstallment(customizeInstallment2);
                }
            }
        }
    }

    public void updateCustomizeFee(List<CustomizeInstallmentDetail> customizeInstallmentDetails, Long userId) {
        this.customizeStudentFeeDAO.updateCustomizeFee(customizeInstallmentDetails, userId);
    }

    public List<CustomizeInstallment> getCustomizeInstallments(Long studentId, Long academicYearId, Long courseYearId, Short installmentId) {
        return this.customizeStudentFeeDAO.getCustomizeInstallments(studentId, academicYearId, courseYearId, installmentId);
    }

    public void updateCustomizeInstallmentDueDate(List<CustomizeInstallment> customizeInstallments) {
        this.customizeStudentFeeDAO.updateCustomizeInstallmentDueDate(customizeInstallments);
    }

    public CustomizeFee getCustomizeFee(Long studentId, Long academicYearId, Long courseYearId) {
        return this.customizeStudentFeeDAO.getCustomizeFee(studentId, academicYearId, courseYearId);
    }

    public void saveCustomizeFee(CustomizeFee customizeFee, Long userId) {
        UserView user = this.userViewDAO.loadUser(userId);
        customizeFee.setCreatedBy(user);
        customizeFee.setModifiedBy(user);
        customizeFee.setCreatedDate(DateUtil.getSystemDateTime());
        customizeFee.setModifiedDate(DateUtil.getSystemDateTime());
        saveCustomizeFee(customizeFee, userId);
    }

    public FeeCustomizeComments getComments(Long studentId, Long academicYearFeeId) {
        return this.customizeStudentFeeDAO.getComments(studentId, academicYearFeeId);
    }

    public void saveOrUpdateComments(FeeCustomizeComments feeCustomizeComments) {
        this.customizeStudentFeeDAO.saveOrUpdateComments(feeCustomizeComments);
    }

    public CustomizeInstallment getCustomizeInstallment(Long id) {
        return this.customizeStudentFeeDAO.getCustomizeInstallment(id);
    }

    public List<FeeCustomizeComments> getComments(Long studentId, Collection<Long> academicYearIds) {
        return this.customizeStudentFeeDAO.getComments(studentId, academicYearIds);
    }

    public List<Date> getAdjustedInstallmentDueDates(Long academicYearId) {
        return this.customizeStudentFeeDAO.getAdjustedInstallmentDueDates(academicYearId);
    }

    public void deleteFeeAdjustment(Long studentId, Long academicYearFeeId) {
        this.customizeStudentFeeDAO.deleteFeeAdjustment(studentId, academicYearFeeId);
        this.customizeStudentFeeDAO.deleteFeeAdjustmentComments(studentId, academicYearFeeId);
        this.customizeStudentFeeDAO.deleteFeeAdjustmentDiscount(studentId, academicYearFeeId);
    }

    public Boolean isCourseYearFeeAdjusted(Long courseYearSettingId, Short admissionTypeId) {
        List<CustomizeInstallment> customizeInstallments = this.customizeStudentFeeDAO.getAdjustedInstallmentOfCourseYearSetting(courseYearSettingId, admissionTypeId);
        if (customizeInstallments == null || customizeInstallments.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
