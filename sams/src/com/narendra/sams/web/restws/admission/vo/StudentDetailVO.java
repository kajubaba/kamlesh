package com.narendra.sams.web.restws.admission.vo;

import java.util.Date;

public class StudentDetailVO {
    private AddressVO address;
    private String admissionScheme;
    private String admissionStatus;
    private String bloodgroup;
    private String busStop;
    private String category;
    private String city;
    private String currentAcademicYear;
    private String currentClass;
    private Date dob;
    private String email;
    private String enrollmentNo;
    private String fatherMobileNo;
    private String fatherName;
    private GaurdianInformationVO gaurdianDetails;
    private String gender;
    private Long id;
    private String mobileNo1;
    private String mobileNo2;
    private String nationality;
    private ParentsInformationVO parentsDetails;
    private String photo;
    private String religion;
    private String status;
    private String studentId;
    private String studentName;
    private StudentPersonalInformationVO studentPersonalInformation;

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

    public String getCurrentClass() {
        return this.currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public String getCurrentAcademicYear() {
        return this.currentAcademicYear;
    }

    public void setCurrentAcademicYear(String currentAcademicYear) {
        this.currentAcademicYear = currentAcademicYear;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdmissionScheme() {
        return this.admissionScheme;
    }

    public void setAdmissionScheme(String admissioScheme) {
        this.admissionScheme = admissioScheme;
    }

    public String getBusStop() {
        return this.busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMobileNo() {
        return this.fatherMobileNo;
    }

    public void setFatherMobileNo(String fatherMobileNo) {
        this.fatherMobileNo = fatherMobileNo;
    }

    public String getEnrollmentNo() {
        return this.enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMobileNo1() {
        return this.mobileNo1;
    }

    public void setMobileNo1(String mobileNo1) {
        this.mobileNo1 = mobileNo1;
    }

    public String getMobileNo2() {
        return this.mobileNo2;
    }

    public void setMobileNo2(String mobileNo2) {
        this.mobileNo2 = mobileNo2;
    }

    public String getReligion() {
        return this.religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getBloodgroup() {
        return this.bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public AddressVO getAddress() {
        return this.address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public ParentsInformationVO getParentsDetails() {
        return this.parentsDetails;
    }

    public void setParentsDetails(ParentsInformationVO parentsDetails) {
        this.parentsDetails = parentsDetails;
    }

    public GaurdianInformationVO getGaurdianDetails() {
        return this.gaurdianDetails;
    }

    public void setGaurdianDetails(GaurdianInformationVO gaurdianDetails) {
        this.gaurdianDetails = gaurdianDetails;
    }

    public StudentPersonalInformationVO getStudentPersonalInformation() {
        return this.studentPersonalInformation;
    }

    public void setStudentPersonalInformation(StudentPersonalInformationVO studentPersonalInformation) {
        this.studentPersonalInformation = studentPersonalInformation;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAdmissionStatus() {
        return this.admissionStatus;
    }

    public void setAdmissionStatus(String admissionStatus) {
        this.admissionStatus = admissionStatus;
    }
}
