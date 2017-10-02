package com.narendra.sams.enquiry.domain;

public class ClasswiseEnquiryCount {
    private Long academicSessionId;
    private Long classId;
    private String className;
    private Long enquiryCount;

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

    public Long getEnquiryCount() {
        return this.enquiryCount;
    }

    public void setEnquiryCount(Long enquiryCount) {
        this.enquiryCount = enquiryCount;
    }

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }
}
