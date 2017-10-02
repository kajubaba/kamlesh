package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class AcademicYearTransactionVO {
    private List<FeeTransactionVO> academicYearTransactions;
    private List<AcademicYearVO> academicYears;
    private Long selectedAcademicYearId;

    public Long getSelectedAcademicYearId() {
        return this.selectedAcademicYearId;
    }

    public void setSelectedAcademicYearId(Long selectedAcademicYearId) {
        this.selectedAcademicYearId = selectedAcademicYearId;
    }

    public List<AcademicYearVO> getAcademicYears() {
        return this.academicYears;
    }

    public void setAcademicYears(List<AcademicYearVO> academicYears) {
        this.academicYears = academicYears;
    }

    public List<FeeTransactionVO> getAcademicYearTransactions() {
        return this.academicYearTransactions;
    }

    public void setAcademicYearTransactions(List<FeeTransactionVO> academicYearTransactions) {
        this.academicYearTransactions = academicYearTransactions;
    }
}
