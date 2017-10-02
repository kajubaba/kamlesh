package com.narendra.sams.web.restws.academics.form;

public class ExamClassSubjectForm {
    private Long academicYearClassId;
    private Integer displaySequenceNo;
    private Long id;
    private Integer maxMarks;
    private String subjectCode;
    private String subjectName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
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
}
