package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.enquiry.dao.EnquiryFollowupDAO;
import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import com.narendra.sams.enquiry.service.EnquiryFollowupService;
import java.util.List;

public class EnquiryFollowupServiceImpl implements EnquiryFollowupService {
    private EnquiryFollowupDAO enquiryFollowupDAO;

    public EnquiryFollowupDAO getEnquiryFollowupDAO() {
        return this.enquiryFollowupDAO;
    }

    public void setEnquiryFollowupDAO(EnquiryFollowupDAO enquiryFollowupDAO) {
        this.enquiryFollowupDAO = enquiryFollowupDAO;
    }

    public Long addFollowup(EnquiryFolloup enquiryFolloup, Long userId) {
        return this.enquiryFollowupDAO.addFollowup(enquiryFolloup, userId);
    }

    public List<EnquiryFolloup> getEnquiryFollowups(Long enquiryId) {
        return this.enquiryFollowupDAO.getEnquiryFollowups(enquiryId);
    }

    public EnquiryFolloup getFollowup(Long id) {
        return this.enquiryFollowupDAO.getFollowup(id);
    }

    public void updateFollowup(EnquiryFolloup enquiryFolloup, Long userId) {
        this.enquiryFollowupDAO.updateFollowup(enquiryFolloup, userId);
    }
}
