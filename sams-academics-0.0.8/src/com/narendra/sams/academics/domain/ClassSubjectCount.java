package com.narendra.sams.academics.domain;

public class ClassSubjectCount {
    private Long academicYearId;
    private Long classId;
    private String className;
    private Long subjectCount;

    public ClassSubjectCount(Long classId, String className, Long subjectCount, Long academicYearId) {
        this.classId = classId;
        this.className = className;
        this.subjectCount = subjectCount;
        this.academicYearId = academicYearId;
    }

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

    public Long getSubjectCount() {
        return this.subjectCount;
    }

    public void setSubjectCount(Long subjectCount) {
        this.subjectCount = subjectCount;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
}
