package com.narendra.sams.web.restws.core.form.mapper;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.web.restws.core.form.EnquiryStatusForm;

public class EnquiryStatusFormToDomainMapper {
    public static EnquiryStatus mapToDomain(EnquiryStatusForm enquiryStatusForm) {
        if (enquiryStatusForm == null) {
            return null;
        }
        EnquiryStatus enquiryStatus = new EnquiryStatus();
        enquiryStatus.setId(enquiryStatusForm.getId());
        enquiryStatus.setName(enquiryStatusForm.getName());
        enquiryStatus.setActive(enquiryStatusForm.isActive());
        return enquiryStatus;
    }

    public static EnquiryStatus mapToDomain(EnquiryStatusForm enquiryStatusForm, Long instituteId) {
        if (enquiryStatusForm == null) {
            return null;
        }
        EnquiryStatus enquiryStatus = new EnquiryStatus();
        enquiryStatus.setId(enquiryStatusForm.getId());
        enquiryStatus.setName(enquiryStatusForm.getName());
        enquiryStatus.setActive(enquiryStatusForm.isActive());
        Institute institute = new Institute();
        institute.setId(instituteId);
        enquiryStatus.setInstitute(institute);
        return enquiryStatus;
    }
}
