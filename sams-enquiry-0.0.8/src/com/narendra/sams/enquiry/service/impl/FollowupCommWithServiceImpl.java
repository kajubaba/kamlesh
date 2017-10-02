package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.enquiry.dao.FollowupCommWithDAO;
import com.narendra.sams.enquiry.domain.FollowupCommWith;
import com.narendra.sams.enquiry.service.FollowupCommWithService;
import java.util.List;

public class FollowupCommWithServiceImpl implements FollowupCommWithService {
    private FollowupCommWithDAO followupCommWithDAO;

    public FollowupCommWithDAO getFollowupCommWithDAO() {
        return this.followupCommWithDAO;
    }

    public void setFollowupCommWithDAO(FollowupCommWithDAO followupCommWithDAO) {
        this.followupCommWithDAO = followupCommWithDAO;
    }

    public List<FollowupCommWith> getCommunicationWith(Long instituteId) {
        return this.followupCommWithDAO.getCommunicationWith(instituteId);
    }
}
