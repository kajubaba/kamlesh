package com.narendra.sams.web.restws.admission.vo;

public class AcademicYearClassVO {
    private String affiliatedTo;
    private Long classId;
    private String className;

    public Long getClassId() {
        return this.classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAffiliatedTo() {
        return this.affiliatedTo;
    }

    public void setAffiliatedTo(String affiliatedTo) {
        this.affiliatedTo = affiliatedTo;
    }
}
