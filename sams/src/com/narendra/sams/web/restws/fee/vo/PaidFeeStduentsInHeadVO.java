package com.narendra.sams.web.restws.fee.vo;

import com.narendra.sams.admission.domain.StudentPaidFee;
import java.util.List;

public class PaidFeeStduentsInHeadVO {
    private String headName;
    List<StudentPaidFee> studentPaidFees;

    public String getHeadName() {
        return this.headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public List<StudentPaidFee> getStudentPaidFees() {
        return this.studentPaidFees;
    }

    public void setStudentPaidFees(List<StudentPaidFee> studentPaidFees) {
        this.studentPaidFees = studentPaidFees;
    }
}
