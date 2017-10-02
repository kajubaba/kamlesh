package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.Bank;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class StudentBankDetails {
    private Bank bank;
    private String bankAcNo;
    private String branchName;
    private Long id;
    private String ifsc;
    private Date lastModifiedDateTime;
    private UserView modifiedByUser;
    private Student student;

    public String getBankAcNo() {
        return this.bankAcNo;
    }

    public void setBankAcNo(String bankAcNo) {
        this.bankAcNo = bankAcNo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfsc() {
        return this.ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public UserView getModifiedByUser() {
        return this.modifiedByUser;
    }

    public void setModifiedByUser(UserView modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }
}
