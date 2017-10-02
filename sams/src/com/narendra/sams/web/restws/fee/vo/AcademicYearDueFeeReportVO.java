package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class AcademicYearDueFeeReportVO {
    private Long academicYearId;
    private Long admissionStatus;
    private List<ClassDueFeeVO> classDueFees;
    private String dueDate;

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public List<ClassDueFeeVO> getClassDueFees() {
        return this.classDueFees;
    }

    public void setClassDueFees(List<ClassDueFeeVO> classDueFees) {
        this.classDueFees = classDueFees;
    }

    public Long getAdmissionStatus() {
        return this.admissionStatus;
    }

    public void setAdmissionStatus(Long admissionStatus) {
        this.admissionStatus = admissionStatus;
    }
}
