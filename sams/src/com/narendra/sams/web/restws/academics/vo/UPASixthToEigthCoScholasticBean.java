package com.narendra.sams.web.restws.academics.vo;

public class UPASixthToEigthCoScholasticBean {
    private String aspectName;
    private String skillName;
    private String term1Grade;
    private String term2Grade;

    public UPASixthToEigthCoScholasticBean(String aspectName, String skillName, String term1Grade, String term2Grade) {
        this.aspectName = aspectName;
        this.skillName = skillName;
        this.term1Grade = term1Grade;
        this.term2Grade = term2Grade;
    }

    public String getAspectName() {
        return this.aspectName;
    }

    public void setAspectName(String aspectName) {
        this.aspectName = aspectName;
    }

    public String getSkillName() {
        return this.skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getTerm1Grade() {
        return this.term1Grade;
    }

    public void setTerm1Grade(String term1Grade) {
        this.term1Grade = term1Grade;
    }

    public String getTerm2Grade() {
        return this.term2Grade;
    }

    public void setTerm2Grade(String term2Grade) {
        this.term2Grade = term2Grade;
    }
}
