package com.narendra.sams.web.restws.academics.vo;

public class ScoreCardCoScholasticBean {
    private String grade;
    private String indicator;
    private String skill;
    private String subSkill;

    public ScoreCardCoScholasticBean(String skill, String subSkill, String grade, String indicator) {
        this.skill = skill;
        this.subSkill = subSkill;
        this.grade = grade;
        this.indicator = indicator;
    }

    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSubSkill() {
        return this.subSkill;
    }

    public void setSubSkill(String subSkill) {
        this.subSkill = subSkill;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIndicator() {
        return this.indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }
}
