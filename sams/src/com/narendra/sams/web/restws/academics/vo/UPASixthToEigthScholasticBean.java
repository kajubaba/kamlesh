package com.narendra.sams.web.restws.academics.vo;

public class UPASixthToEigthScholasticBean {
    private String grade1;
    private String grade2;
    private String marks1;
    private String marks2;
    private String marksTotal1;
    private String marksTotal2;
    private String noteBook1;
    private String noteBook2;
    private String perTest1;
    private String perTest2;
    private String subEn1;
    private String subEn2;
    private String subjectName;

    public UPASixthToEigthScholasticBean(String subjectName, String perTest1, String noteBook1, String subEn1, String marks1, String marksTotal1, String grade1, String perTest2, String noteBook2, String subEn2, String marks2, String marksTotal2, String grade2) {
        this.subjectName = subjectName;
        this.perTest1 = perTest1;
        this.noteBook1 = noteBook1;
        this.subEn1 = subEn1;
        this.marks1 = marks1;
        this.marksTotal1 = marksTotal1;
        this.grade1 = grade1;
        this.perTest2 = perTest2;
        this.noteBook2 = noteBook2;
        this.subEn2 = subEn2;
        this.marks2 = marks2;
        this.marksTotal2 = marksTotal2;
        this.grade2 = grade2;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPerTest1() {
        return this.perTest1;
    }

    public void setPerTest1(String perTest1) {
        this.perTest1 = perTest1;
    }

    public String getPerTest2() {
        return this.perTest2;
    }

    public void setPerTest2(String perTest2) {
        this.perTest2 = perTest2;
    }

    public String getNoteBook1() {
        return this.noteBook1;
    }

    public void setNoteBook1(String noteBook1) {
        this.noteBook1 = noteBook1;
    }

    public String getNoteBook2() {
        return this.noteBook2;
    }

    public void setNoteBook2(String noteBook2) {
        this.noteBook2 = noteBook2;
    }

    public String getSubEn1() {
        return this.subEn1;
    }

    public void setSubEn1(String subEn1) {
        this.subEn1 = subEn1;
    }

    public String getSubEn2() {
        return this.subEn2;
    }

    public void setSubEn2(String subEn2) {
        this.subEn2 = subEn2;
    }

    public String getMarks1() {
        return this.marks1;
    }

    public void setMarks1(String marks1) {
        this.marks1 = marks1;
    }

    public String getMarks2() {
        return this.marks2;
    }

    public void setMarks2(String marks2) {
        this.marks2 = marks2;
    }

    public String getGrade1() {
        return this.grade1;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public String getGrade2() {
        return this.grade2;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public String getMarksTotal1() {
        return this.marksTotal1;
    }

    public void setMarksTotal1(String marksTotal1) {
        this.marksTotal1 = marksTotal1;
    }

    public String getMarksTotal2() {
        return this.marksTotal2;
    }

    public void setMarksTotal2(String marksTotal2) {
        this.marksTotal2 = marksTotal2;
    }
}
