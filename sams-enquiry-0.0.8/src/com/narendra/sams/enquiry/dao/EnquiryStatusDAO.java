package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.EnquiryStatus;
import java.util.List;

public interface EnquiryStatusDAO {
    Long addStatus(EnquiryStatus enquiryStatus, Long l);

    void deleteStatus(Long l);

    List<EnquiryStatus> getAllActiveEnquiryStatus(Long l);

    List<EnquiryStatus> getAllStatusList(Long l);

    EnquiryStatus getEnquiryStatus(Long l);

    EnquiryStatus getStatusByName(String str, Long l);

    void updateStatus(EnquiryStatus enquiryStatus, Long l);
}
