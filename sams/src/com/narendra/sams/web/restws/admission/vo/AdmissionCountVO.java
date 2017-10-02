package com.narendra.sams.web.restws.admission.vo;

public class AdmissionCountVO {
    private Long admissions;

    public long getAdmissions() {
        if (this.admissions == null) {
            return 0;
        }
        return this.admissions.longValue();
    }

    public void setAdmissions(Long admissions) {
        this.admissions = admissions;
    }
}
