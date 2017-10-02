package com.narendra.sams.web.admin.vo;

import java.util.List;

public class InstallmentVO {
    private Long academicYearFeeId;
    private List<InstallmentDueDate> dueDates;
    private List<FeeHeadWiseInstallmentVO> installmentFeeHeadVOs;

    public List<FeeHeadWiseInstallmentVO> getInstallmentFeeHeadVOs() {
        return this.installmentFeeHeadVOs;
    }

    public void setInstallmentFeeHeadVOs(List<FeeHeadWiseInstallmentVO> installmentFeeHeadVOs) {
        this.installmentFeeHeadVOs = installmentFeeHeadVOs;
    }

    public Long getAcademicYearFeeId() {
        return this.academicYearFeeId;
    }

    public void setAcademicYearFeeId(Long academicYearFeeId) {
        this.academicYearFeeId = academicYearFeeId;
    }

    public List<InstallmentDueDate> getDueDates() {
        return this.dueDates;
    }

    public void setDueDates(List<InstallmentDueDate> dueDates) {
        this.dueDates = dueDates;
    }
}
