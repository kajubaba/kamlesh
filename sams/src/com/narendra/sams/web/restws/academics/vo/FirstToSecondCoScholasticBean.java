package com.narendra.sams.web.restws.academics.vo;

public class FirstToSecondCoScholasticBean {
    private String activity;
    private String skill;
    private String subActivity;
    private String term1;
    private String term2;
    private String term3;

    public FirstToSecondCoScholasticBean(String activity, String subActivity, String skill, String term1, String term2, String term3) {
        this.activity = activity;
        this.subActivity = subActivity;
        this.skill = skill;
        this.term1 = term1;
        this.term2 = term2;
        this.term3 = term3;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSubActivity() {
        return this.subActivity;
    }

    public void setSubActivity(String subActivity) {
        this.subActivity = subActivity;
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

    public String getTerm3() {
        return this.term3;
    }

    public void setTerm3(String term3) {
        this.term3 = term3;
    }
}
