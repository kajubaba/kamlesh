package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import java.util.Date;
import java.util.List;

public interface EnquiryFollowupListDAO {
    List<EnquiryFolloup> getUpcomingEnquiryFollowups(Long l, Date date, Date date2);

    Long getUpcomingEnquiryFollowupsCount(Long l, Date date, Date date2);
}
