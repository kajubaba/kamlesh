package com.narendra.sams.web.restws.academics.exam.vo;

public class TermAssessmentVO {
    private String displayName;
    private Long displayOrder;
    private Long id;
    private Long maxMarks;
    private String name;
    private Long weightage;

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getWeightage() {
        return this.weightage;
    }

    public void setWeightage(Long weightage) {
        this.weightage = weightage;
    }

    public Long getMaxMarks() {
        return this.maxMarks;
    }

    public void setMaxMarks(Long maxMarks) {
        this.maxMarks = maxMarks;
    }
}
