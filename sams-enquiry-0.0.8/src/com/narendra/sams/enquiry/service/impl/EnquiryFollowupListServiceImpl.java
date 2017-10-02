package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.dao.EnquiryFollowupListDAO;
import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import com.narendra.sams.enquiry.service.EnquiryFollowupListService;
import java.util.Date;
import java.util.List;

public class EnquiryFollowupListServiceImpl implements EnquiryFollowupListService {
    private EnquiryFollowupListDAO enquiryFollowupListDAO;

    public EnquiryFollowupListDAO getEnquiryFollowupListDAO() {
        return this.enquiryFollowupListDAO;
    }

    public void setEnquiryFollowupListDAO(EnquiryFollowupListDAO enquiryFollowupListDAO) {
        this.enquiryFollowupListDAO = enquiryFollowupListDAO;
    }

    public List<EnquiryFolloup> getUpcomingEnquiryFollowups(Long academicSessionId, Date from, Date to) {
        if (from != null) {
            from = DateUtil.makeStartDate(from);
        }
        if (to != null) {
            to = DateUtil.makeEndDate(to);
        }
        return this.enquiryFollowupListDAO.getUpcomingEnquiryFollowups(academicSessionId, from, to);
    }

    public Long getUpcomingEnquiryFollowupsCount(Long academicSessionId, Date from, Date to) {
        if (from != null) {
            from = DateUtil.makeStartDate(from);
        }
        if (to != null) {
            to = DateUtil.makeEndDate(to);
        }
        return this.enquiryFollowupListDAO.getUpcomingEnquiryFollowupsCount(academicSessionId, from, to);
    }
}
