package com.narendra.sams.enquiry.domain;

public class StudentPrevClass {
    public static String STATUS_BLANK = "";
    public static String STATUS_FAIL = "fail";
    public static String STATUS_PURSUING = "pursuing";
    public static String STATUS_RESULT_AWAITED = "result awaited";
    public static String STATUS_RESULT_DECLARED = "result declared";
    private String board;
    private String city;
    private String className;
    private Long id;
    private String instituteName;
    private Float percentage;
    private String studentStatus;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstituteName() {
        return this.instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBoard() {
        return this.board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getStudentStatus() {
        return this.studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Float getPercentage() {
        return this.percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}
