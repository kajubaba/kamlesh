package com.narendra.sams.academics.domain;

import com.narendra.sams.core.domain.AcademicYearClass;
import java.io.Serializable;

public class ClassSubject implements Serializable {
    private static final long serialVersionUID = 7946426400777907970L;
    private AcademicYearClass academicYearClass;
    private Integer displaySequenceNo;
    private Long id;
    private Boolean isOptional;
    private Integer maxMarks;
    private String subjectCode;
    private String subjectName;

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

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return this.subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getMaxMarks() {
        return this.maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Integer getDisplaySequenceNo() {
        return this.displaySequenceNo;
    }

    public void setDisplaySequenceNo(Integer displaySequenceNo) {
        this.displaySequenceNo = displaySequenceNo;
    }

    public Boolean getIsOptional() {
        return this.isOptional;
    }

    public void setIsOptional(Boolean isOptional) {
        this.isOptional = isOptional;
    }
}
