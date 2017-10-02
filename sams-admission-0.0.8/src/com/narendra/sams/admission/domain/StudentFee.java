package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.Date;

public class StudentFee {
    private String addresss;
    private Long adminFee;
    private AdmissionScheme admissionScheme;
    private AdmissionType admissionType;
    private Long busFee;
    private BusStop busStop;
    private String busStopInOtherLanguage;
    private String busStopName;
    private ClassHistory classHistory;
    private AcademicYearClass currentClass;
    private StudentStatus currentStatus;
    private Long customizedFee;
    private String fatherContactNo1;
    private String fatherContactNo2;
    private String fatherName;
    private String fatherNameInOtherLanguage;
    private long feePlaceHolder;
    private Boolean isFeeCustomized;
    private Date latestDateDate;
    private String motherContactNo1;
    private String motherContactNo2;
    private long paidFee;
    private long paidFeeInAdvance;
    private long paidLateFee;
    private String studentContactNo1;
    private String studentContactNo2;
    private Long studentDbId;
    private String studentFirstName;
    private String studentId;
    private String studentLastName;
    private String studentMiddleName;
    private String studentNameInOtherLanguage;

    public Boolean getIsFeeCustomized() {
        return this.isFeeCustomized;
    }

    public void setIsFeeCustomized(Boolean isFeeCustomized) {
        this.isFeeCustomized = isFeeCustomized;
    }

    public Long getBusFee() {
        if (this.busFee == null) {
            return Long.valueOf(0);
        }
        return this.busFee;
    }

    public void setBusFee(Long busFee) {
        this.busFee = busFee;
    }

    public BusStop getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public Long getAdminFee() {
        if (this.adminFee == null) {
            return Long.valueOf(0);
        }
        return this.adminFee;
    }

    public void setAdminFee(Long adminFee) {
        this.adminFee = adminFee;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return this.studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentMiddleName() {
        return this.studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return this.studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Long getCustomizedFee() {
        if (this.customizedFee == null) {
            return Long.valueOf(0);
        }
        return this.customizedFee;
    }

    public void setCustomizedFee(Long customizedFee) {
        this.customizedFee = customizedFee;
    }

    public AdmissionType getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public Date getLatestDateDate() {
        return this.latestDateDate;
    }

    public void setLatestDateDate(Date latestDateDate) {
        this.latestDateDate = latestDateDate;
    }

    public long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(long paidFee) {
        this.paidFee = paidFee;
    }

    public long getPaidFeeInAdvance() {
        return this.paidFeeInAdvance;
    }

    public void setPaidFeeInAdvance(long paidFeeInAdvance) {
        this.paidFeeInAdvance = paidFeeInAdvance;
    }

    public long getPaidLateFee() {
        return this.paidLateFee;
    }

    public void setPaidLateFee(long paidLateFee) {
        this.paidLateFee = paidLateFee;
    }

    public long getFeePlaceHolder() {
        return this.feePlaceHolder;
    }

    public void setFeePlaceHolder(long feePlaceHolder) {
        this.feePlaceHolder = feePlaceHolder;
    }

    public StudentStatus getCurrentStatus() {
        return this.currentStatus;
    }

    public AcademicYearClass getCurrentClass() {
        return this.currentClass;
    }

    public void setCurrentClass(AcademicYearClass currentClass) {
        this.currentClass = currentClass;
    }

    public void setCurrentStatus(StudentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getStudentContactNo1() {
        return this.studentContactNo1;
    }

    public void setStudentContactNo1(String studentContactNo1) {
        this.studentContactNo1 = studentContactNo1;
    }

    public String getStudentContactNo2() {
        return this.studentContactNo2;
    }

    public void setStudentContactNo2(String studentContactNo2) {
        this.studentContactNo2 = studentContactNo2;
    }

    public String getFatherContactNo1() {
        return this.fatherContactNo1;
    }

    public void setFatherContactNo1(String fatherContactNo1) {
        this.fatherContactNo1 = fatherContactNo1;
    }

    public String getFatherContactNo2() {
        return this.fatherContactNo2;
    }

    public void setFatherContactNo2(String fatherContactNo2) {
        this.fatherContactNo2 = fatherContactNo2;
    }

    public String getMotherContactNo1() {
        return this.motherContactNo1;
    }

    public void setMotherContactNo1(String motherContactNo1) {
        this.motherContactNo1 = motherContactNo1;
    }

    public String getMotherContactNo2() {
        return this.motherContactNo2;
    }

    public void setMotherContactNo2(String motherContactNo2) {
        this.motherContactNo2 = motherContactNo2;
    }

    public String getAddresss() {
        return this.addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public Long getStudentDbId() {
        return this.studentDbId;
    }

    public void setStudentDbId(Long studentDbId) {
        this.studentDbId = studentDbId;
    }

    public ClassHistory getClassHistory() {
        return this.classHistory;
    }

    public void setClassHistory(ClassHistory classHistory) {
        this.classHistory = classHistory;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public AdmissionScheme getAdmissionScheme() {
        return this.admissionScheme;
    }

    public void setAdmissionScheme(AdmissionScheme admissionScheme) {
        this.admissionScheme = admissionScheme;
    }

    public String getStudentNameInOtherLanguage() {
        return this.studentNameInOtherLanguage;
    }

    public void setStudentNameInOtherLanguage(String studentNameInOtherLanguage) {
        this.studentNameInOtherLanguage = studentNameInOtherLanguage;
    }

    public String getFatherNameInOtherLanguage() {
        return this.fatherNameInOtherLanguage;
    }

    public void setFatherNameInOtherLanguage(String fatherNameInOtherLanguage) {
        this.fatherNameInOtherLanguage = fatherNameInOtherLanguage;
    }

    public String getBusStopInOtherLanguage() {
        return this.busStopInOtherLanguage;
    }

    public void setBusStopInOtherLanguage(String busStopInOtherLanguage) {
        this.busStopInOtherLanguage = busStopInOtherLanguage;
    }

    public String getBusStopName() {
        return this.busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }
}
