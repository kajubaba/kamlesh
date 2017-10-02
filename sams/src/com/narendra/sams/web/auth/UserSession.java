package com.narendra.sams.web.auth;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.InstituteSetting;
import java.util.List;

public class UserSession {
    private FeeHead busFeeHead;
    private InstituteSetting instituteSetting;
    private List<InstituteSetting> institutes;
    private FeeHead lateFeeHead;
    private Institute nonWorkingInstitute;
    private UserDetail userDetail;
    private Long workingInstituteId;

    public UserDetail getUserDetail() {
        return this.userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Institute getWorkingInstitute() {
        return this.instituteSetting.getInstitute();
    }

    public Long getEnquiryAcademicYearId() {
        if (this.instituteSetting.getEnquirySettings().getActiveAcademicYear() == null) {
            return null;
        }
        return this.instituteSetting.getEnquirySettings().getActiveAcademicYear().getId();
    }

    public AcademicYear getAcademicYearOfEnquiry() {
        return this.instituteSetting.getEnquirySettings().getActiveAcademicYear();
    }

    public AcademicYear getAcademicYearOfAdmission() {
        return this.instituteSetting.getAdmissionSettings().getActiveAcademicYear();
    }

    public FeeHead getBusFeeHead() {
        return this.busFeeHead;
    }

    public void setBusFeeHead(FeeHead busFeeHead) {
        this.busFeeHead = busFeeHead;
    }

    public FeeHead getLateFeeHead() {
        return this.lateFeeHead;
    }

    public void setLateFeeHead(FeeHead lateFeeHead) {
        this.lateFeeHead = lateFeeHead;
    }

    public InstituteSetting getInstituteSetting() {
        return this.instituteSetting;
    }

    public Long getWorkingInstituteId() {
        return this.workingInstituteId;
    }

    public void setWorkingInstituteId(Long workingInstituteId) {
        this.workingInstituteId = workingInstituteId;
    }

    public void setInstituteSetting(InstituteSetting instituteSetting) {
        this.instituteSetting = instituteSetting;
    }

    public Institute getNonWorkingInstitute() {
        return this.nonWorkingInstitute;
    }

    public void setNonWorkingInstitute(Institute nonWorkingInstitute) {
        this.nonWorkingInstitute = nonWorkingInstitute;
    }

    public List<InstituteSetting> getInstitutes() {
        return this.institutes;
    }

    public void setInstitutes(List<InstituteSetting> institutes) {
        this.institutes = institutes;
    }
}
