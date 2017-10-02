package com.narendra.sams.web.restws.admission.form;

import com.narendra.sams.web.restws.admission.vo.AcademicYearClassVO;
import com.narendra.sams.web.restws.admission.vo.AdmissionCategoryVO;
import com.narendra.sams.web.restws.admission.vo.AffiliationAuthorityVO;
import com.narendra.sams.web.restws.admission.vo.BusStopVO;
import com.narendra.sams.web.restws.admission.vo.StateVO;
import com.narendra.sams.web.restws.vo.AdmissionSchemeVO;
import java.util.List;

public class AdmissionEditForm {
    private Long academicYearClassId;
    private List<AcademicYearClassVO> academicYearClasses;
    private List<AdmissionCategoryVO> admissionCategories;
    private Long admissionSchemeId;
    private List<AdmissionSchemeVO> admissionSchemes;
    private List<AffiliationAuthorityVO> affiliationAuthorities;
    private Long affiliationAuthorityId;
    private Long busStopId;
    private List<BusStopVO> busStops;
    private String city;
    private String district;
    private String dobStr;
    private String emailId;
    private String enrollmentNo;
    private String fatherContact1;
    private String fatherContactNo;
    private String fatherName;
    private String formNo;
    private String gender;
    private Long id;
    private String line1;
    private String line2;
    private String motherName;
    private String samagraId;
    private Long stateId;
    private List<StateVO> states;
    private Long studentCategoryId;
    private String studentContactNo;
    private String studentId;
    private String studentName;
    private String teh;
    private String zipCode;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }

    public String getEnrollmentNo() {
        return this.enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherContactNo() {
        return this.fatherContactNo;
    }

    public void setFatherContactNo(String fatherContactNo) {
        this.fatherContactNo = fatherContactNo;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDobStr() {
        return this.dobStr;
    }

    public void setDobStr(String dobStr) {
        this.dobStr = dobStr;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getStudentCategoryId() {
        return this.studentCategoryId;
    }

    public void setStudentCategoryId(Long studentCategoryId) {
        this.studentCategoryId = studentCategoryId;
    }

    public String getStudentContactNo() {
        return this.studentContactNo;
    }

    public void setStudentContactNo(String studentContactNo) {
        this.studentContactNo = studentContactNo;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeh() {
        return this.teh;
    }

    public void setTeh(String teh) {
        this.teh = teh;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getStateId() {
        return this.stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public String getFormNo() {
        return this.formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public Long getAffiliationAuthorityId() {
        return this.affiliationAuthorityId;
    }

    public void setAffiliationAuthorityId(Long affiliationAuthorityId) {
        this.affiliationAuthorityId = affiliationAuthorityId;
    }

    public List<AdmissionCategoryVO> getAdmissionCategories() {
        return this.admissionCategories;
    }

    public void setAdmissionCategories(List<AdmissionCategoryVO> admissionCategories) {
        this.admissionCategories = admissionCategories;
    }

    public List<AffiliationAuthorityVO> getAffiliationAuthorities() {
        return this.affiliationAuthorities;
    }

    public void setAffiliationAuthorities(List<AffiliationAuthorityVO> affiliationAuthorities) {
        this.affiliationAuthorities = affiliationAuthorities;
    }

    public List<BusStopVO> getBusStops() {
        return this.busStops;
    }

    public void setBusStops(List<BusStopVO> busStops) {
        this.busStops = busStops;
    }

    public List<StateVO> getStates() {
        return this.states;
    }

    public void setStates(List<StateVO> states) {
        this.states = states;
    }

    public List<AcademicYearClassVO> getAcademicYearClasses() {
        return this.academicYearClasses;
    }

    public void setAcademicYearClasses(List<AcademicYearClassVO> academicYearClasses) {
        this.academicYearClasses = academicYearClasses;
    }

    public List<AdmissionSchemeVO> getAdmissionSchemes() {
        return this.admissionSchemes;
    }

    public void setAdmissionSchemes(List<AdmissionSchemeVO> admissionSchemes) {
        this.admissionSchemes = admissionSchemes;
    }

    public String getSamagraId() {
        return this.samagraId;
    }

    public void setSamagraId(String samagraId) {
        this.samagraId = samagraId;
    }

    public String getFatherContact1() {
        return this.fatherContact1;
    }

    public void setFatherContact1(String fatherContact1) {
        this.fatherContact1 = fatherContact1;
    }

    public Long getAdmissionSchemeId() {
        return this.admissionSchemeId;
    }

    public void setAdmissionSchemeId(Long admissionSchemeId) {
        this.admissionSchemeId = admissionSchemeId;
    }
}
