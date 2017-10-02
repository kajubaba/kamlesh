package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.enquiry.dao.FollowupNextActionDAO;
import com.narendra.sams.enquiry.domain.FollowupNextAction;
import com.narendra.sams.enquiry.service.FollowupNextActionService;
import java.util.List;

public class FollowupNextActionServiceImpl implements FollowupNextActionService {
    private FollowupNextActionDAO followupNextActionDAO;

    public FollowupNextActionDAO getFollowupNextActionDAO() {
        return this.followupNextActionDAO;
    }

    public void setFollowupNextActionDAO(FollowupNextActionDAO followupNextActionDAO) {
        this.followupNextActionDAO = followupNextActionDAO;
    }

    public List<FollowupNextAction> getFollowupNextActions(Long instituteId) {
        return this.followupNextActionDAO.getFollowupNextActions(instituteId);
    }
}
