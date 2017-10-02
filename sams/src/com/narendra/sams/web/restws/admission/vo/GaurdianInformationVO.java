package com.narendra.sams.web.restws.admission.vo;

public class GaurdianInformationVO {
    private AddressVO address;
    private Long annaulIncome;
    private String contactNo1;
    private String contactNo2;
    private Long id;
    private String name;
    private String occupation;
    private String relationWithStudent;
    private Long studentId;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public Long getAnnaulIncome() {
        return this.annaulIncome;
    }

    public void setAnnaulIncome(Long annaulIncome) {
        this.annaulIncome = annaulIncome;
    }

    public AddressVO getAddress() {
        return this.address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public String getRelationWithStudent() {
        return this.relationWithStudent;
    }

    public void setRelationWithStudent(String relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
