package com.narendra.sams.academics.domain;

public class ScoreCard {
    private CoScholasticAssessment coScholasticAssessment;
    private ScholasticAssessment scholasticAssessment;

    public ScholasticAssessment getScholasticAssessment() {
        return this.scholasticAssessment;
    }

    public void setScholasticAssessment(ScholasticAssessment scholasticAssessment) {
        this.scholasticAssessment = scholasticAssessment;
    }

    public CoScholasticAssessment getCoScholasticAssessment() {
        return this.coScholasticAssessment;
    }

    public void setCoScholasticAssessment(CoScholasticAssessment coScholasticAssessment) {
        this.coScholasticAssessment = coScholasticAssessment;
    }
}
