package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.UserView;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class FeeTransaction implements Serializable {
    private static final long serialVersionUID = -6427461522075528323L;
    private UserView UserView;
    private AcademicYear academicYear;
    private AcademicYearFeeInstallment academicYearFeeInstallment;
    private String bankBranchName;
    private String chequeDDBankName;
    private Date chequeDDDate;
    private String chequeDDNo;
    private String comments;
    private CustomizeInstallment customizeInstallment;
    private String depositedBy;
    private FeeRecieptHeader feeRecieptHeader;
    private Set<FeeTransactionDetail> feeTransactionDetails;
    private Long id;
    private Institute institute;
    private Date paymentDate;
    private String paymentMode;
    private Long recieptNo;
    private Student student;
    private AcademicYearClass studentClass;
    private String transactionId;
    private Date transactionTime;

    public Long getRecieptNo() {
        return this.recieptNo;
    }

    public void setRecieptNo(Long recieptNo) {
        this.recieptNo = recieptNo;
    }

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

    public AcademicYearClass getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(AcademicYearClass studentClass) {
        this.studentClass = studentClass;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionTime() {
        return this.transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Set<FeeTransactionDetail> getFeeTransactionDetails() {
        return this.feeTransactionDetails;
    }

    public void setFeeTransactionDetails(Set<FeeTransactionDetail> feeTransactionDetails) {
        this.feeTransactionDetails = feeTransactionDetails;
    }

    public UserView getUser() {
        return this.UserView;
    }

    public void setUser(UserView UserView) {
        this.UserView = UserView;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public long getFeeSum() {
        long sum = 0;
        if (!(this.feeTransactionDetails == null || this.feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : this.feeTransactionDetails) {
                sum += feeTransactionDetail.getAmount().longValue();
            }
        }
        return sum;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public AcademicYearFeeInstallment getAcademicYearFeeInstallment() {
        return this.academicYearFeeInstallment;
    }

    public void setAcademicYearFeeInstallment(AcademicYearFeeInstallment academicYearFeeInstallment) {
        this.academicYearFeeInstallment = academicYearFeeInstallment;
    }

    public CustomizeInstallment getCustomizeInstallment() {
        return this.customizeInstallment;
    }

    public void setCustomizeInstallment(CustomizeInstallment customizeInstallment) {
        this.customizeInstallment = customizeInstallment;
    }

    public FeeRecieptHeader getFeeRecieptHeader() {
        return this.feeRecieptHeader;
    }

    public void setFeeRecieptHeader(FeeRecieptHeader feeRecieptHeader) {
        this.feeRecieptHeader = feeRecieptHeader;
    }

    public String getDepositedBy() {
        return this.depositedBy;
    }

    public void setDepositedBy(String depositedBy) {
        this.depositedBy = depositedBy;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getChequeDDNo() {
        return this.chequeDDNo;
    }

    public void setChequeDDNo(String chequeDDNo) {
        this.chequeDDNo = chequeDDNo;
    }

    public Date getChequeDDDate() {
        return this.chequeDDDate;
    }

    public void setChequeDDDate(Date chequeDDDate) {
        this.chequeDDDate = chequeDDDate;
    }

    public String getChequeDDBankName() {
        return this.chequeDDBankName;
    }

    public void setChequeDDBankName(String chequeDDBankName) {
        this.chequeDDBankName = chequeDDBankName;
    }

    public String getBankBranchName() {
        return this.bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
