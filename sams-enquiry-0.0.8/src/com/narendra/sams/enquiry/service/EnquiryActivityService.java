package com.narendra.sams.enquiry.service;

import com.narendra.sams.enquiry.domain.EnquiryActivity;
import java.util.List;

public interface EnquiryActivityService {
    void followEnquiry(Long[] lArr, String str, String str2, Long l);

    List<EnquiryActivity> getEnquiryActivities(Long l);

    void updateEnquiryAssignee(Long[] lArr, Long l, String str, Long l2);

    void updateEnquiryOwner(Long[] lArr, Long l, String str, Long l2);

    void updateEnquiryStatus(Long l, Long l2, String str, Long l3);

    void updateEnquiryStatus(Long[] lArr, Long l, String str, Long l2);

    void updateFormDetails(Long l, String str, Long l2);
}
