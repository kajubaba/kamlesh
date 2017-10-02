package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.enquiry.dao.FollowupSuggestionDAO;
import com.narendra.sams.enquiry.domain.FollowupSuggestion;
import com.narendra.sams.enquiry.service.FollowupSuggestionService;
import java.util.List;

public class FollowupSuggestionServiceImpl implements FollowupSuggestionService {
    private FollowupSuggestionDAO followupSuggestionDAO;

    public FollowupSuggestionDAO getFollowupSuggestionDAO() {
        return this.followupSuggestionDAO;
    }

    public void setFollowupSuggestionDAO(FollowupSuggestionDAO followupSuggestionDAO) {
        this.followupSuggestionDAO = followupSuggestionDAO;
    }

    public List<FollowupSuggestion> getFollowupSuggestions(Long instituteId) {
        return this.followupSuggestionDAO.getFollowupSuggestions(instituteId);
    }
}
