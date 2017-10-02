package com.narendra.sams.web.restws.form;

import java.util.List;

public class AcademicSessionAdmissionSchemeForm {
    private Long academicSessionId;
    private List<Long> admissionSchemeIds;

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }

    public List<Long> getAdmissionSchemeIds() {
        return this.admissionSchemeIds;
    }

    public void setAdmissionSchemeIds(List<Long> admissionSchemeIds) {
        this.admissionSchemeIds = admissionSchemeIds;
    }
}
