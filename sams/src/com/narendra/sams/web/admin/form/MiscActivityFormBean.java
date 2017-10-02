package com.narendra.sams.web.admin.form;

import java.util.List;

public class MiscActivityFormBean {
    private Long academicYearId;
    private List<Long> courseIds;
    private Long fee;
    private List<MiscActivityFeeHeadFormBean> heads;
    private Long id;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFee() {
        return this.fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public List<MiscActivityFeeHeadFormBean> getHeads() {
        return this.heads;
    }

    public void setHeads(List<MiscActivityFeeHeadFormBean> heads) {
        this.heads = heads;
    }

    public List<Long> getCourseIds() {
        return this.courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
}
