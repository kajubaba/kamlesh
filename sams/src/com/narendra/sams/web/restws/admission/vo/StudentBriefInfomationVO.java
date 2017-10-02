package com.narendra.sams.web.restws.admission.vo;

public class StudentBriefInfomationVO {
    private String admissionScheme;
    private String busStop;
    private String currentAcademicYear;
    private String currentClass;
    private String fatherName;
    private String gender;
    private Long id;
    private String photo;
    private String status;
    private String studentId;
    private String studentName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public void setAdmissionScheme(String admissionScheme) {
        this.admissionScheme = admissionScheme;
    }

    public String getBusStop() {
        return this.busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
