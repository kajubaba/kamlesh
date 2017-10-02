package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.UserView;
import java.io.Serializable;
import java.util.Date;

public class FeeDiscount implements Serializable {
    private static final long serialVersionUID = 453630049203057066L;
    private AcademicYearClass academicYearClass;
    private AcademicYearFee academicYearFee;
    private Long amount;
    private UserView createdBy;
    private Date createdDate;
    private FeeHead feeHead;
    private Long id;
    private UserView modifiedBy;
    private Date modifiedDate;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public FeeHead getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(FeeHead feeHead) {
        this.feeHead = feeHead;
    }

    public Long getAmount() {
        if (this.amount == null) {
            return Long.valueOf(0);
        }
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public AcademicYearFee getAcademicYearFee() {
        return this.academicYearFee;
    }

    public void setAcademicYearFee(AcademicYearFee academicYearFee) {
        this.academicYearFee = academicYearFee;
    }
}
