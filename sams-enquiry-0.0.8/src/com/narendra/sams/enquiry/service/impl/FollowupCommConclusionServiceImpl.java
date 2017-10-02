package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.enquiry.dao.FollowupCommConclusionDAO;
import com.narendra.sams.enquiry.domain.FollowupCommConclusion;
import com.narendra.sams.enquiry.service.FollowupCommConclusionService;
import java.util.List;

public class FollowupCommConclusionServiceImpl implements FollowupCommConclusionService {
    private FollowupCommConclusionDAO followupCommConclusionDAO;

    public FollowupCommConclusionDAO getFollowupCommConclusionDAO() {
        return this.followupCommConclusionDAO;
    }

    public void setFollowupCommConclusionDAO(FollowupCommConclusionDAO followupCommConclusionDAO) {
        this.followupCommConclusionDAO = followupCommConclusionDAO;
    }

    public List<FollowupCommConclusion> getFollowupConclusions(Long instituteId) {
        return this.followupCommConclusionDAO.getFollowupConclusions(instituteId);
    }
}
