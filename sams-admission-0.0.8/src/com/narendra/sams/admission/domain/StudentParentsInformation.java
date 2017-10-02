package com.narendra.sams.admission.domain;

public class StudentParentsInformation {
    private Address address;
    private Long fatherAnnulaIncome;
    private String fatherContactNo1;
    private String fatherContactNo2;
    private String fatherName;
    private String fatherOccupation;
    private Long motherAnnulaIncome;
    private String motherContactNo1;
    private String motherContactNo2;
    private String motherName;
    private String motherOccupation;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherOccupation() {
        return this.fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getMotherOccupation() {
        return this.motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public Long getFatherAnnulaIncome() {
        return this.fatherAnnulaIncome;
    }

    public void setFatherAnnulaIncome(Long fatherAnnulaIncome) {
        this.fatherAnnulaIncome = fatherAnnulaIncome;
    }

    public Long getMotherAnnulaIncome() {
        return this.motherAnnulaIncome;
    }

    public void setMotherAnnulaIncome(Long motherAnnulaIncome) {
        this.motherAnnulaIncome = motherAnnulaIncome;
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

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
