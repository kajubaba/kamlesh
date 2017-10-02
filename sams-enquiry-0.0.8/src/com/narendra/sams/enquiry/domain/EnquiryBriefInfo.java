package com.narendra.sams.enquiry.domain;

public class EnquiryBriefInfo {
    private Long enquiryId;
    private String fatherName;
    private String gender;
    private String studentFirstName;
    private String studentLastName;

    public EnquiryBriefInfo(Long enquiryId, String studentFirstName, String studentLastName, String gender, String fatherName) {
        this.enquiryId = enquiryId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.gender = gender;
        this.fatherName = fatherName;
    }

    public Long getEnquiryId() {
        return this.enquiryId;
    }

    public void setEnquiryId(Long enquiryId) {
        this.enquiryId = enquiryId;
    }

    public String getStudentFirstName() {
        return this.studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return this.studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
