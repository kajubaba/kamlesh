package com.narendra.sams.fee.service.impl;

import com.narendra.sams.admission.dao.FeePaymentDAO;
import com.narendra.sams.admission.dao.StudentDAO;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentIdGeneratorService;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.communication.service.SmsSender;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearAdmissionCount;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.exception.OperationCanNotSucceedException;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.service.InstituteSettingService;
import com.narendra.sams.fee.domain.PayFeeReturn;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.fee.service.FeePaymentService;
import com.narendra.sams.fee.service.StudentActivityService;

public class FeePaymentServiceImpl implements FeePaymentService {
    private AcademicYearFeeService academicYearFeeService;
    private CustomizeStudentFeeService customizeStudentFeeService;
    private FeePaymentDAO feePaymentDAO;
    private InstituteSettingService instituteSettingService;
    private SmsSender smsSender;
    private StudentActivityService studentActivityService;
    private StudentDAO studentDAO;
    private StudentIdGeneratorService studentIdGeneratorService;
    private StudentService studentService;

    public FeePaymentDAO getFeePaymentDAO() {
        return this.feePaymentDAO;
    }

    public void setFeePaymentDAO(FeePaymentDAO feePaymentDAO) {
        this.feePaymentDAO = feePaymentDAO;
    }

    public StudentDAO getStudentDAO() {
        return this.studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentActivityService getStudentActivityService() {
        return this.studentActivityService;
    }

    public void setStudentActivityService(StudentActivityService studentActivityService) {
        this.studentActivityService = studentActivityService;
    }

    public StudentService getStudentService() {
        return this.studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public AcademicYearFeeService getAcademicYearFeeService() {
        return this.academicYearFeeService;
    }

    public void setAcademicYearFeeService(AcademicYearFeeService academicYearFeeService) {
        this.academicYearFeeService = academicYearFeeService;
    }

    public CustomizeStudentFeeService getCustomizeStudentFeeService() {
        return this.customizeStudentFeeService;
    }

    public void setCustomizeStudentFeeService(CustomizeStudentFeeService customizeStudentFeeService) {
        this.customizeStudentFeeService = customizeStudentFeeService;
    }

    public SmsSender getSmsSender() {
        return this.smsSender;
    }

    public void setSmsSender(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public InstituteSettingService getInstituteSettingService() {
        return this.instituteSettingService;
    }

    public void setInstituteSettingService(InstituteSettingService instituteSettingService) {
        this.instituteSettingService = instituteSettingService;
    }

    public StudentIdGeneratorService getStudentIdGeneratorService() {
        return this.studentIdGeneratorService;
    }

    public void setStudentIdGeneratorService(StudentIdGeneratorService studentIdGeneratorService) {
        this.studentIdGeneratorService = studentIdGeneratorService;
    }

    public synchronized PayFeeReturn payFee(FeeTransaction feeTransaction, Long userId) {
        PayFeeReturn payFeeReturn;
        if (feeTransaction == null) {
            payFeeReturn = null;
        } else {
            if (feeTransaction.getCustomizeInstallment() != null) {
                feeTransaction.setAcademicYear(this.customizeStudentFeeService.getCustomizeInstallment(feeTransaction.getCustomizeInstallment().getId()).getAcademicYearFee().getAcademicYear());
            } else {
                feeTransaction.setAcademicYear(this.academicYearFeeService.getAcademicYearFeeInstallment(feeTransaction.getAcademicYearFeeInstallment().getId()).getAcademicYearFee().getAcademicYear());
            }
            Student student = this.studentDAO.getStudentById(feeTransaction.getStudent().getId());
            feeTransaction.setInstitute(student.getInstitute());
            AcademicYearAdmissionCount academicYearAdmissionCount = this.studentDAO.loadAcademicYearAdmissionCount(feeTransaction.getAcademicYear().getId());
            String transactionId = prepareTransactionId(academicYearAdmissionCount.getTransactionCount(), feeTransaction.getAcademicYear());
            feeTransaction.setTransactionId(transactionId);
            InstituteSetting instituteSetting = this.instituteSettingService.getInstituteSetting(student.getInstitute().getId());
            long recieptNo = instituteSetting.getFeeSettings().getLastFeeReceiptNo().longValue() + 1;
            Long dbTransactionId = this.feePaymentDAO.payFee(feeTransaction, userId);
            this.smsSender.sendFeeDepositSMS(student.getId(), feeTransaction.getFeeSum(), feeTransaction.getPaymentDate());
            academicYearAdmissionCount.setTransactionCount(Long.valueOf(academicYearAdmissionCount.getTransactionCount().longValue() + 1));
            instituteSetting.getFeeSettings().setLastFeeReceiptNo(Long.valueOf(recieptNo));
            feeTransaction.setRecieptNo(Long.valueOf(recieptNo));
            if (!StudentStatus.CONFIRMED.equals(student.getStudentStatus().getId())) {
                try {
                    this.studentActivityService.updateStudentStatus(student.getId(), StudentStatus.CONFIRMED, userId, "System automtically confirmed student on fee payment");
                } catch (OperationCanNotSucceedException e) {
                    e.printStackTrace();
                }
            }
            this.studentIdGeneratorService.generateStudentId(student.getId());
            payFeeReturn = new PayFeeReturn();
            payFeeReturn.setRecieptNo(Long.valueOf(recieptNo));
            payFeeReturn.setTransactionId(transactionId);
            payFeeReturn.setDbTransactionId(dbTransactionId);
        }
        return payFeeReturn;
    }

    private synchronized String prepareTransactionId(Long transactionCount, AcademicYear academicYear) {
        StringBuffer sb;
        sb = new StringBuffer();
        long count = transactionCount.longValue() + 1;
        sb.append(academicYear.getName().trim().substring(2, 4));
        if (count < 10) {
            sb.append("0000000");
        } else if (count < 100) {
            sb.append("000000");
        } else if (count < 1000) {
            sb.append("00000");
        } else if (count < 10000) {
            sb.append("0000");
        } else if (count < 100000) {
            sb.append("000");
        } else if (count < 1000000) {
            sb.append("00");
        } else if (count < 10000000) {
            sb.append("0");
        } else if (count < 100000000) {
            sb.append("");
        }
        sb.append(count);
        return sb.toString();
    }
}
