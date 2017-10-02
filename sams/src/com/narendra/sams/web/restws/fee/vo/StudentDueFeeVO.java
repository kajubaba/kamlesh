package com.narendra.sams.web.restws.fee.vo;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.Date;

public class StudentDueFeeVO {
    private String addresss;
    private Long adminFee;
    private AdmissionType admissionType;
    private String arrivalBusName;
    private Long busFee;
    private BusStop busStop;
    private String busStopInOtherLanguage;
    private String busStopName;
    private Long classHistoryId;
    private AcademicYearClass currentClass;
    private StudentStatus currentStatus;
    private Long customizedFee;
    private String departureBusName;
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
    private String studentClass;
    private String studentContactNo1;
    private String studentContactNo2;
    private Long studentDbId;
    private String studentFullName;
    private String studentId;
    private String studentNameInOtherLanguage;
    private long totalFee;
    private long unPaidFee;

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFullName() {
        return this.studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getStudentContactNo1() {
        return this.studentContactNo1;
    }

    public void setStudentContactNo1(String studentContactNo1) {
        this.studentContactNo1 = studentContactNo1;
    }

    public AdmissionType getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public BusStop getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public Long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(Long busFee) {
        this.busFee = busFee;
    }

    public Long getCustomizedFee() {
        return this.customizedFee;
    }

    public void setCustomizedFee(Long customizedFee) {
        this.customizedFee = customizedFee;
    }

    public Long getAdminFee() {
        return this.adminFee;
    }

    public void setAdminFee(Long adminFee) {
        this.adminFee = adminFee;
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

    public Date getLatestDateDate() {
        return this.latestDateDate;
    }

    public void setLatestDateDate(Date latestDateDate) {
        this.latestDateDate = latestDateDate;
    }

    public Boolean getIsFeeCustomized() {
        return this.isFeeCustomized;
    }

    public void setIsFeeCustomized(Boolean isFeeCustomized) {
        this.isFeeCustomized = isFeeCustomized;
    }

    public AcademicYearClass getCurrentClass() {
        return this.currentClass;
    }

    public void setCurrentClass(AcademicYearClass currentClass) {
        this.currentClass = currentClass;
    }

    public StudentStatus getCurrentStatus() {
        return this.currentStatus;
    }

    public void setCurrentStatus(StudentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public long getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public long getUnPaidFee() {
        return this.unPaidFee;
    }

    public void setUnPaidFee(long unPaidFee) {
        this.unPaidFee = unPaidFee;
    }

    public Long getStudentDbId() {
        return this.studentDbId;
    }

    public void setStudentDbId(Long studentDbId) {
        this.studentDbId = studentDbId;
    }

    public Long getClassHistoryId() {
        return this.classHistoryId;
    }

    public void setClassHistoryId(Long classHistoryId) {
        this.classHistoryId = classHistoryId;
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

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
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

    public String getArrivalBusName() {
        return this.arrivalBusName;
    }

    public void setArrivalBusName(String arrivalBusName) {
        this.arrivalBusName = arrivalBusName;
    }

    public String getDepartureBusName() {
        return this.departureBusName;
    }

    public void setDepartureBusName(String departureBusName) {
        this.departureBusName = departureBusName;
    }

    public String getBusStopName() {
        return this.busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }
}
