package com.narendra.sams.core.domain;

public class AcademicYearClassIDGenMethod {
    private AcademicYearClass academicYearClass;
    private Long id;
    private IDGenerationMethod idGenerationMethod;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public IDGenerationMethod getIdGenerationMethod() {
        return this.idGenerationMethod;
    }

    public void setIdGenerationMethod(IDGenerationMethod idGenerationMethod) {
        this.idGenerationMethod = idGenerationMethod;
    }
}
