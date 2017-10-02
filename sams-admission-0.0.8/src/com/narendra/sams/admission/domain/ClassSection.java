package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYearClass;

public class ClassSection {
    private AcademicYearClass academicYearClass;
    private Long id;
    private String sectionCode;
    private String sectionName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionCode() {
        return this.sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
