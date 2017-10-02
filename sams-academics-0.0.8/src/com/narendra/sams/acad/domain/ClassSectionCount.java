package com.narendra.sams.acad.domain;

public class ClassSectionCount {
    private Long academicYearId;
    private Long classId;
    private Long sectionCount;
    private String sectionName;

    public ClassSectionCount(Long classId, String sectionName, Long sectionCount, Long academicYearId) {
        this.classId = classId;
        this.sectionName = sectionName;
        this.sectionCount = sectionCount;
        this.academicYearId = academicYearId;
    }

    public Long getClassId() {
        return this.classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Long getSectionCount() {
        return this.sectionCount;
    }

    public void setSectionCount(Long sectionCount) {
        this.sectionCount = sectionCount;
    }
}
