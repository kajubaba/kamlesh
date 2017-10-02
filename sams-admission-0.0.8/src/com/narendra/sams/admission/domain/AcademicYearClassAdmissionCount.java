package com.narendra.sams.admission.domain;

public class AcademicYearClassAdmissionCount {
    private Long academicYearClassId;
    private Long academicYearId;
    private Long admissionCount;
    private String name;
    private String studentStatusId;

    public AcademicYearClassAdmissionCount(Long academicYearClassId, String name, Long admissionCount, Long academicYearId, String studentStatusId) {
        this.academicYearClassId = academicYearClassId;
        this.name = name;
        this.admissionCount = admissionCount;
        this.academicYearId = academicYearId;
        this.studentStatusId = studentStatusId;
    }

    public String getStudentStatusId() {
        return this.studentStatusId;
    }

    public void setStudentStatusId(String studentStatusId) {
        this.studentStatusId = studentStatusId;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdmissionCount() {
        return this.admissionCount;
    }

    public void setAdmissionCount(Long admissionCount) {
        this.admissionCount = admissionCount;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
}
