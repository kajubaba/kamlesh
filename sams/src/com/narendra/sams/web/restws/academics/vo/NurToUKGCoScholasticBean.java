package com.narendra.sams.web.restws.academics.vo;

public class NurToUKGCoScholasticBean {
    private String activity;
    private String skill;
    private String term1;
    private String term2;

    public NurToUKGCoScholasticBean(String activity, String skill, String term1, String term2) {
        this.activity = activity;
        this.skill = skill;
        this.term1 = term1;
        this.term2 = term2;
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

    public String getTerm1() {
        return this.term1;
    }

    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    public String getTerm2() {
        return this.term2;
    }

    public void setTerm2(String term2) {
        this.term2 = term2;
    }
}
