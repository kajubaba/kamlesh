package com.narendra.sams.enquiry.service;

import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import java.util.List;

public interface EnquiryFollowupService {
    Long addFollowup(EnquiryFolloup enquiryFolloup, Long l);

    List<EnquiryFolloup> getEnquiryFollowups(Long l);

    EnquiryFolloup getFollowup(Long l);

    void updateFollowup(EnquiryFolloup enquiryFolloup, Long l);
}
