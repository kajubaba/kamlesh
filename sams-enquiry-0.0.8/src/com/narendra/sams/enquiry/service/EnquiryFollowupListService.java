package com.narendra.sams.enquiry.service;

import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import java.util.Date;
import java.util.List;

public interface EnquiryFollowupListService {
    List<EnquiryFolloup> getUpcomingEnquiryFollowups(Long l, Date date, Date date2);

    Long getUpcomingEnquiryFollowupsCount(Long l, Date date, Date date2);
}
