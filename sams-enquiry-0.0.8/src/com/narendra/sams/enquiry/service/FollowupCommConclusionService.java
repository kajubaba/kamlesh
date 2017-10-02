package com.narendra.sams.enquiry.service;

import com.narendra.sams.enquiry.domain.FollowupCommConclusion;
import java.util.List;

public interface FollowupCommConclusionService {
    List<FollowupCommConclusion> getFollowupConclusions(Long l);
}
