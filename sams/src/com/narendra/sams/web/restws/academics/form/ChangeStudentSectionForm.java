package com.narendra.sams.web.restws.academics.form;

import java.util.Collection;

public class ChangeStudentSectionForm {
    private Long academicYearClassId;
    private Long newSectionId;
    private Long selectedSectionId;
    private Collection<Long> studentIds;

    public Collection<Long> getStudentIds() {
        return this.studentIds;
    }

    public void setStudentIds(Collection<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }

    public Long getNewSectionId() {
        return this.newSectionId;
    }

    public void setNewSectionId(Long newSectionId) {
        this.newSectionId = newSectionId;
    }

    public Long getSelectedSectionId() {
        return this.selectedSectionId;
    }

    public void setSelectedSectionId(Long selectedSectionId) {
        this.selectedSectionId = selectedSectionId;
    }
}
