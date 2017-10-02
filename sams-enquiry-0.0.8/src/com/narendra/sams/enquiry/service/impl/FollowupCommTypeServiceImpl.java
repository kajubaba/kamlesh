package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.enquiry.dao.FollowupCommTypeDAO;
import com.narendra.sams.enquiry.domain.FollowupCommType;
import com.narendra.sams.enquiry.service.FollowupCommTypeService;
import java.util.List;

public class FollowupCommTypeServiceImpl implements FollowupCommTypeService {
    private FollowupCommTypeDAO followupCommTypeDAO;

    public FollowupCommTypeDAO getFollowupCommTypeDAO() {
        return this.followupCommTypeDAO;
    }

    public void setFollowupCommTypeDAO(FollowupCommTypeDAO followupCommTypeDAO) {
        this.followupCommTypeDAO = followupCommTypeDAO;
    }

    public List<FollowupCommType> getFollowupTypes(Long instituteId) {
        return this.followupCommTypeDAO.getFollowupTypes(instituteId);
    }
}
