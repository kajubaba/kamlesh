package com.narendra.sams.communication.service.impl;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.communication.domain.InstituteSMSProvider;
import com.narendra.sams.communication.domain.SMSProvider;
import com.narendra.sams.communication.domain.SMSSetting;
import com.narendra.sams.communication.service.SmsProviderService;
import com.narendra.sams.communication.service.SmsSender;
import com.narendra.sams.communication.service.SmsSettingsService;
import com.narendra.sams.core.util.DateUtil;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SmsSenderImpl implements SmsSender {
    private SmsProviderService smsProviderService;
    private SmsSettingsService smsSettingsService;
    private StudentService studentService;

    public StudentService getStudentService() {
        return this.studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public SmsProviderService getSmsProviderService() {
        return this.smsProviderService;
    }

    public void setSmsProviderService(SmsProviderService smsProviderService) {
        this.smsProviderService = smsProviderService;
    }

    public SmsSettingsService getSmsSettingsService() {
        return this.smsSettingsService;
    }

    public void setSmsSettingsService(SmsSettingsService smsSettingsService) {
        this.smsSettingsService = smsSettingsService;
    }

    public void sendFeeDepositSMS(Long studentId, long paidAmount, Date paymentDate) {
        if (studentId != null) {
            Student student = this.studentService.getStudentById(studentId);
            if (student != null) {
                InstituteSMSProvider instituteSMSProvider = this.smsProviderService.getSMSProvider(student.getInstitute().getId());
                if (instituteSMSProvider != null && instituteSMSProvider.getUrl() != null && instituteSMSProvider.getAuthKey() != null && instituteSMSProvider.getSenderId() != null && !"".equals(instituteSMSProvider.getUrl()) && !"".equals(instituteSMSProvider.getAuthKey()) && !"".equals(instituteSMSProvider.getSenderId())) {
                    SMSSetting smsSetting = this.smsSettingsService.getSMSSettings(student.getInstitute().getId(), SMSSetting.FEE_DEPOSIT_MSG);
                    if (smsSetting != null && smsSetting.getIsEnabled().booleanValue()) {
                        Set<String> mobileNumbers = new HashSet();
                        if (smsSetting.getSendToStudent().booleanValue()) {
                            if (!(student.getPhone1() == null || student.getPhone1().isEmpty() || student.getPhone1().length() != 10)) {
                                mobileNumbers.add(student.getPhone1());
                            }
                            if (!(student.getPhone2() == null || student.getPhone2().isEmpty() || student.getPhone2().length() != 10)) {
                                mobileNumbers.add(student.getPhone2());
                            }
                        }
                        if (smsSetting.getSendToFather().booleanValue()) {
                            if (!(student.getFatherContact1() == null || student.getFatherContact1().isEmpty() || student.getFatherContact1().length() != 10)) {
                                mobileNumbers.add(student.getFatherContact1());
                            }
                            if (!(student.getFatherContact2() == null || student.getFatherContact2().isEmpty() || student.getFatherContact2().length() != 10)) {
                                mobileNumbers.add(student.getFatherContact2());
                            }
                        }
                        if (smsSetting.getSendToMother().booleanValue()) {
                            if (!(student.getMotherContact1() == null || student.getMotherContact1().isEmpty() || student.getMotherContact1().length() != 10)) {
                                mobileNumbers.add(student.getMotherContact1());
                            }
                            if (!(student.getMotherContact2() == null || student.getMotherContact2().isEmpty() || student.getMotherContact2().length() != 10)) {
                                mobileNumbers.add(student.getMotherContact2());
                            }
                        }
                        if (!mobileNumbers.isEmpty()) {
                            StringBuffer mobileNos = new StringBuffer("");
                            int count = 1;
                            for (String mobileNo : mobileNumbers) {
                                if (count == 1) {
                                    mobileNos.append(mobileNo);
                                } else {
                                    mobileNos.append(",").append(mobileNo);
                                }
                                count++;
                            }
                            if (SMSProvider.MSG91.equals(instituteSMSProvider.getSmsProviderName())) {
                                String message = "";
                                if ("SGRKL".equals(System.getenv("ENV"))) {
                                    message = "We have received Rs. " + paidAmount + " against tution fee of Ma/Ms. " + student.getFullName() + ". Thanks You - Shree Gurukul";
                                } else {
                                    message = "Dear Student/Parents, we have received Rs. " + paidAmount + " against fee of student " + student.getFullName() + " on " + DateUtil.formatDate(paymentDate, "dd-MMM-yyyy") + ". Please keep paying fee on time to avoid late fee penalties. Thank You.";
                                }
                                MSG91SMSSender.sendFeeDepositSMS(instituteSMSProvider.getUrl(), instituteSMSProvider.getAuthKey(), mobileNos.toString(), message, instituteSMSProvider.getSenderId(), "4");
                            }
                        }
                    }
                }
            }
        }
    }
}
