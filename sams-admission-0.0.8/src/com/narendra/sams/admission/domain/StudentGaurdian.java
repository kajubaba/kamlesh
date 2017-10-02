package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.UserView;
import java.util.Date;
import java.util.Set;

public class StudentGaurdian {
    private Set<GaurdianAddress> addresses;
    private Long annualIncome;
    private String contactNo1;
    private String contactNo2;
    private Date dob;
    private String gender;
    private Long id;
    private Date lasUpdatedDateTime;
    private UserView lastUpdatedBy;
    private String name;
    private String occupation;
    private String relationWithStudent;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public String getRelationWithStudent() {
        return this.relationWithStudent;
    }

    public void setRelationWithStudent(String relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Long getAnnualIncome() {
        return this.annualIncome;
    }

    public void setAnnualIncome(Long annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<GaurdianAddress> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<GaurdianAddress> addresses) {
        this.addresses = addresses;
    }

    public UserView getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(UserView lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLasUpdatedDateTime() {
        return this.lasUpdatedDateTime;
    }

    public void setLasUpdatedDateTime(Date lasUpdatedDateTime) {
        this.lasUpdatedDateTime = lasUpdatedDateTime;
    }
}
