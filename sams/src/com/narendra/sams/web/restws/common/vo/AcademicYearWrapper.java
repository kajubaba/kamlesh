package com.narendra.sams.web.restws.common.vo;

import java.util.List;

public class AcademicYearWrapper {
    private List<AcademicYearVO> academicYears;
    private Long selectedAcademicYear;

    public Long getSelectedAcademicYear() {
        return this.selectedAcademicYear;
    }

    public void setSelectedAcademicYear(Long selectedAcademicYear) {
        this.selectedAcademicYear = selectedAcademicYear;
    }

    public List<AcademicYearVO> getAcademicYears() {
        return this.academicYears;
    }

    public void setAcademicYears(List<AcademicYearVO> academicYears) {
        this.academicYears = academicYears;
    }
}
