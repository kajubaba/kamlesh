package com.narendra.sams.web.restws.student;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentBankDetails;
import com.narendra.sams.core.domain.Bank;
import com.narendra.sams.web.restws.student.form.StudentBankDetailsForm;
import com.narendra.sams.web.restws.student.vo.StudentBankDetailsVO;

public class StudentPersonalInfoRestControllerHelper {
    public static StudentBankDetails prepareBankDetailsDomain(StudentBankDetailsForm studentBankDetailsForm) {
        StudentBankDetails studentBankDetails = new StudentBankDetails();
        studentBankDetails.setId(studentBankDetailsForm.getBankDetailId());
        studentBankDetails.setBankAcNo(studentBankDetailsForm.getAcNo());
        studentBankDetails.setIfsc(studentBankDetailsForm.getIfsc());
        studentBankDetails.setBranchName(studentBankDetailsForm.getBranchName());
        if (studentBankDetailsForm.getBankId() != null) {
            Bank bank = new Bank();
            bank.setId(studentBankDetailsForm.getBankId());
            studentBankDetails.setBank(bank);
        }
        Student student = new Student();
        student.setId(studentBankDetailsForm.getStudentId());
        studentBankDetails.setStudent(student);
        return studentBankDetails;
    }

    public static StudentBankDetailsVO prepareBankDetailsVO(StudentBankDetails studentBankDetails, Long studentId) {
        StudentBankDetailsVO studentBankDetailsVO = new StudentBankDetailsVO();
        studentBankDetailsVO.setStudentId(studentId);
        if (studentBankDetails != null) {
            studentBankDetailsVO.setBankDetailId(studentBankDetails.getId());
            studentBankDetailsVO.setAcNo(studentBankDetails.getBankAcNo());
            studentBankDetailsVO.setBranchName(studentBankDetails.getBranchName());
            studentBankDetailsVO.setIfsc(studentBankDetails.getIfsc());
            if (studentBankDetails.getBank() != null) {
                studentBankDetailsVO.setBankId(studentBankDetails.getBank().getId());
                studentBankDetailsVO.setBankName(studentBankDetails.getBank().getBankName());
            }
        }
        return studentBankDetailsVO;
    }
}
