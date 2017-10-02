package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.FollowupSuggestion;
import java.util.List;

public interface FollowupSuggestionDAO {
    List<FollowupSuggestion> getFollowupSuggestions(Long l);
}
