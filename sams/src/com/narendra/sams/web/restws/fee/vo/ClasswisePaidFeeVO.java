package com.narendra.sams.web.restws.fee.vo;

public class ClasswisePaidFeeVO {
    private String ClassName;
    private Long academicYearClassId;
    private String classSem;
    private Long paidAmount;

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
}
