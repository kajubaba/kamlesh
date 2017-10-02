package com.narendra.sams.enquiry.service;

import com.narendra.sams.enquiry.domain.FollowupSuggestion;
import java.util.List;

public interface FollowupSuggestionService {
    List<FollowupSuggestion> getFollowupSuggestions(Long l);
}
