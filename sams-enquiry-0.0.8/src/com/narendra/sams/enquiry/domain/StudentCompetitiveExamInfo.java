package com.narendra.sams.enquiry.domain;

import java.io.Serializable;

public class StudentCompetitiveExamInfo implements Serializable {
    private static final long serialVersionUID = 3166520850393237368L;
    private String examName;
    private Long id;
    private String rollNo;
    private Long totalMarks;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return this.examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getRollNo() {
        return this.rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Long getTotalMarks() {
        return this.totalMarks;
    }

    public void setTotalMarks(Long totalMarks) {
        this.totalMarks = totalMarks;
    }
}
