package com.narendra.sams.admission.domain;

public class StudentTranslation {
    private String city;
    private String fatherName;
    private Long id;
    private String studentId;
    private String studentName;
    private String translatedCity;
    private String translatedFatherName;
    private String translatedName;

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

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTranslatedName() {
        return this.translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getTranslatedFatherName() {
        return this.translatedFatherName;
    }

    public void setTranslatedFatherName(String translatedFatherName) {
        this.translatedFatherName = translatedFatherName;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTranslatedCity() {
        return this.translatedCity;
    }

    public void setTranslatedCity(String translatedCity) {
        this.translatedCity = translatedCity;
    }
}
