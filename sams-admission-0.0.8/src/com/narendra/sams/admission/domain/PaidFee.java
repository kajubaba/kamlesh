package com.narendra.sams.admission.domain;

public class PaidFee {
    private String ClassName;
    private Long academicYearClassId;
    private Long academicYearId;
    private String academicYearName;
    private String classSem;
    private Long paidAmount;

    public PaidFee(Long academicYearClassId, String academicYearName, String className, String classSem, Long paidAmount, Long academicYearId) {
        this.academicYearClassId = academicYearClassId;
        this.ClassName = className;
        this.classSem = classSem;
        this.paidAmount = paidAmount;
        this.academicYearId = academicYearId;
        this.academicYearName = academicYearName;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }

    public String getClassName() {
        return this.ClassName;
    }

    public void setClassName(String className) {
        this.ClassName = className;
    }

    public String getClassSem() {
        return this.classSem;
    }

    public void setClassSem(String classSem) {
        this.classSem = classSem;
    }

    public Long getPaidAmount() {
        return this.paidAmount;
    }

    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getAcademicYearName() {
        return this.academicYearName;
    }

    public void setAcademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }
}
