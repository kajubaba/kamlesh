package com.narendra.sams.web.admission.vo;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.BusStop;

public class AcademicYearFeeVO {
    private AcademicYear academicYear;
    private AcademicYearClass academicYearClass;
    private AcademicYearFee academicYearFee;
    private AdmissionType admissionType;
    private long busFee;
    private BusStop busStop;
    private Long classHistoryId;
    private String customized;
    private StudentInstallmentsVO studentInstallmentsVO;

    public long totalLatefee() {
        long lateFee = 0;
        for (StudentInstallmentVO studentInstallmentVO : this.studentInstallmentsVO.getInstallments()) {
            lateFee += studentInstallmentVO.getLateFee();
        }
        return lateFee;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public AcademicYearFee getAcademicYearFee() {
        return this.academicYearFee;
    }

    public void setAcademicYearFee(AcademicYearFee academicYearFee) {
        this.academicYearFee = academicYearFee;
    }

    public long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(long busFee) {
        this.busFee = busFee;
    }

    public StudentInstallmentsVO getStudentInstallmentsVO() {
        return this.studentInstallmentsVO;
    }

    public void setStudentInstallmentsVO(StudentInstallmentsVO studentInstallmentsVO) {
        this.studentInstallmentsVO = studentInstallmentsVO;
    }

    public String getCustomized() {
        return this.customized;
    }

    public void setCustomized(String customized) {
        this.customized = customized;
    }

    public AdmissionType getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public Long getClassHistoryId() {
        return this.classHistoryId;
    }

    public void setClassHistoryId(Long classHistoryId) {
        this.classHistoryId = classHistoryId;
    }

    public BusStop getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }
}
