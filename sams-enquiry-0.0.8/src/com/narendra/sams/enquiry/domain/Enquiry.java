package com.narendra.sams.enquiry.domain;

import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;
import java.util.Set;

public class Enquiry {
    private AcademicYear academicYear;
    private AcademicYearClass academicYearClass;
    private EnquiryAddress address;
    private UserView assignee;
    private Country country;
    private UserView createdBy;
    private Date createdDate;
    private Set<EnquiryActivity> enquiryActivities;
    private EnquiryStatus enquiryStatus;
    private String fatherContactNo;
    private String fatherOccupation;
    private Long formFee;
    private Date formIssueDate;
    private UserView formIssuedBy;
    private String formNo;
    private Long id;
    private Institute institute;
    private UserView modifiedBy;
    private Date modifiedDate;
    private String motherContactNo;
    private String motherName;
    private String motherOccupation;
    private UserView owner;
    private Long receiptNo;
    private Long registrationFee;
    private String registrationNo;
    private State state;
    private StudentCategory studentCategory;
    private String studentContactNo;
    private Date studentDob;
    private String studentDobString;
    private String studentEmailId;
    private String studentEnquiryId;
    private String studentFatherName;
    private String studentFirstName;
    private String studentGender;
    private String studentLastName;
    private String studentMiddleName;
    private StudentPrevClass studentPrevClass;
    private String type;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentEnquiryId() {
        return this.studentEnquiryId;
    }

    public void setStudentEnquiryId(String studentEnquiryId) {
        this.studentEnquiryId = studentEnquiryId;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserView getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public String getStudentFatherName() {
        return this.studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public String getStudentGender() {
        return this.studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public StudentCategory getStudentCategory() {
        return this.studentCategory;
    }

    public void setStudentCategory(StudentCategory studentCategory) {
        this.studentCategory = studentCategory;
    }

    public String getStudentContactNo() {
        return this.studentContactNo;
    }

    public void setStudentContactNo(String studentContactNo) {
        this.studentContactNo = studentContactNo;
    }

    public String getStudentEmailId() {
        return this.studentEmailId;
    }

    public void setStudentEmailId(String studentEmailId) {
        this.studentEmailId = studentEmailId;
    }

    public StudentPrevClass getStudentPrevClass() {
        return this.studentPrevClass;
    }

    public void setStudentPrevClass(StudentPrevClass studentPrevClass) {
        this.studentPrevClass = studentPrevClass;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public EnquiryAddress getAddress() {
        return this.address;
    }

    public void setAddress(EnquiryAddress address) {
        this.address = address;
    }

    public String getRegistrationNo() {
        return this.registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Long getRegistrationFee() {
        return this.registrationFee;
    }

    public void setRegistrationFee(Long registrationFee) {
        this.registrationFee = registrationFee;
    }

    public EnquiryStatus getEnquiryStatus() {
        return this.enquiryStatus;
    }

    public void setEnquiryStatus(EnquiryStatus enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
    }

    public Date getStudentDob() {
        return this.studentDob;
    }

    public void setStudentDob(Date studentDob) {
        this.studentDob = studentDob;
    }

    public String getStudentDobString() {
        return this.studentDobString;
    }

    public void setStudentDobString(String studentDobString) {
        this.studentDobString = studentDobString;
    }

    public UserView getOwner() {
        return this.owner;
    }

    public void setOwner(UserView owner) {
        this.owner = owner;
    }

    public UserView getAssignee() {
        return this.assignee;
    }

    public void setAssignee(UserView assignee) {
        this.assignee = assignee;
    }

    public Set<EnquiryActivity> getEnquiryActivities() {
        return this.enquiryActivities;
    }

    public void setEnquiryActivities(Set<EnquiryActivity> enquiryActivities) {
        this.enquiryActivities = enquiryActivities;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getStudentFullName() {
        return this.studentFirstName + " " + this.studentLastName;
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

    public String getFormNo() {
        return this.formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public Date getFormIssueDate() {
        return this.formIssueDate;
    }

    public void setFormIssueDate(Date formIssueDate) {
        this.formIssueDate = formIssueDate;
    }

    public Long getFormFee() {
        return this.formFee;
    }

    public void setFormFee(Long formFee) {
        this.formFee = formFee;
    }

    public Long getReceiptNo() {
        return this.receiptNo;
    }

    public void setReceiptNo(Long receiptNo) {
        this.receiptNo = receiptNo;
    }

    public UserView getFormIssuedBy() {
        return this.formIssuedBy;
    }

    public void setFormIssuedBy(UserView formIssuedBy) {
        this.formIssuedBy = formIssuedBy;
    }
}
