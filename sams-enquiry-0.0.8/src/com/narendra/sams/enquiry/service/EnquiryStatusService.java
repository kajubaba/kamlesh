package com.narendra.sams.enquiry.service;

import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import java.util.List;

public interface EnquiryStatusService {
    Long addStatus(EnquiryStatus enquiryStatus, Long l) throws DuplicateNameFoundException;

    void deleteStatus(Long l);

    List<EnquiryStatus> getAllActiveEnquiryStatus(Long l);

    List<EnquiryStatus> getAllStatusList(Long l);

    EnquiryStatus getEnquiryStatus(Long l);

    EnquiryStatus getStatusByName(String str, Long l);

    void updateStatus(EnquiryStatus enquiryStatus, Long l) throws DuplicateNameFoundException;
}
