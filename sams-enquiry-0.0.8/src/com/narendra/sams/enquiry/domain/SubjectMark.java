package com.narendra.sams.enquiry.domain;

public class SubjectMark {
    private Long id;
    private Long marksObtained;
    private StudentPrevClass studentPrevClass;
    private String subject;
    private Long totalMarks;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getTotalMarks() {
        return this.totalMarks;
    }

    public void setTotalMarks(Long totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Long getMarksObtained() {
        return this.marksObtained;
    }

    public void setMarksObtained(Long marksObtained) {
        this.marksObtained = marksObtained;
    }

    public StudentPrevClass getStudentPrevClass() {
        return this.studentPrevClass;
    }

    public void setStudentPrevClass(StudentPrevClass studentPrevClass) {
        this.studentPrevClass = studentPrevClass;
    }
}
