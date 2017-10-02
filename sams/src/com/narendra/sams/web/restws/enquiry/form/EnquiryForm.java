package com.narendra.sams.web.restws.enquiry.form;

public class EnquiryForm {
    private Long academicYearClassId;
    private Long affiliationAuthorityId;
    private String city;
    private String dobStr;
    private String fatherContactNo;
    private String fatherName;
    private String fatherOccupation;
    private String gender;
    private Long id;
    private String line1;
    private String line2;
    private String motherContactNo;
    private String motherName;
    private String motherOccupation;
    private Float preClassPercentage;
    private String prevClass;
    private String prevClassBoard;
    private String prevClassCity;
    private String prevClassStatus;
    private String prevInstituteName;
    private Long stateId;
    private String studentContactNo;
    private String studentName;
    private String zipCode;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAffiliationAuthorityId() {
        return this.affiliationAuthorityId;
    }

    public void setAffiliationAuthorityId(Long affiliationAuthorityId) {
        this.affiliationAuthorityId = affiliationAuthorityId;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentContactNo() {
        return this.studentContactNo;
    }

    public void setStudentContactNo(String studentContactNo) {
        this.studentContactNo = studentContactNo;
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

    public String getFatherOccupation() {
        return this.fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherContactNo() {
        return this.motherContactNo;
    }

    public void setMotherContactNo(String motherContactNo) {
        this.motherContactNo = motherContactNo;
    }

    public String getMotherOccupation() {
        return this.motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
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

    public String getPrevClass() {
        return this.prevClass;
    }

    public void setPrevClass(String prevClass) {
        this.prevClass = prevClass;
    }

    public String getPrevInstituteName() {
        return this.prevInstituteName;
    }

    public void setPrevInstituteName(String prevInstituteName) {
        this.prevInstituteName = prevInstituteName;
    }

    public String getPrevClassBoard() {
        return this.prevClassBoard;
    }

    public void setPrevClassBoard(String prevClassBoard) {
        this.prevClassBoard = prevClassBoard;
    }

    public String getPrevClassCity() {
        return this.prevClassCity;
    }

    public void setPrevClassCity(String prevClassCity) {
        this.prevClassCity = prevClassCity;
    }

    public String getPrevClassStatus() {
        return this.prevClassStatus;
    }

    public void setPrevClassStatus(String prevClassStatus) {
        this.prevClassStatus = prevClassStatus;
    }

    public Float getPreClassPercentage() {
        return this.preClassPercentage;
    }

    public void setPreClassPercentage(Float preClassPercentage) {
        this.preClassPercentage = preClassPercentage;
    }
}
