package com.narendra.sams.web.restws.academics.vo;

public class SixToEigthCoScholasticBean {
    private String activity;
    private String descriptor1;
    private String descriptor2;
    private String grade1;
    private String grade2;
    private String skill;

    public SixToEigthCoScholasticBean(String activity, String skill, String grade1, String descriptor1, String grade2, String descriptor2) {
        this.activity = activity;
        this.skill = skill;
        this.grade1 = grade1;
        this.descriptor1 = descriptor1;
        this.grade2 = grade2;
        this.descriptor2 = descriptor2;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getGrade1() {
        return this.grade1;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public String getDescriptor1() {
        return this.descriptor1;
    }

    public void setDescriptor1(String descriptor1) {
        this.descriptor1 = descriptor1;
    }

    public String getGrade2() {
        return this.grade2;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public String getDescriptor2() {
        return this.descriptor2;
    }

    public void setDescriptor2(String descriptor2) {
        this.descriptor2 = descriptor2;
    }
}
