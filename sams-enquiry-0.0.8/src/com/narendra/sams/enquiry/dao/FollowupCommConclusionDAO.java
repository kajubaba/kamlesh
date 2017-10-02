package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.FollowupCommConclusion;
import java.util.List;

public interface FollowupCommConclusionDAO {
    List<FollowupCommConclusion> getFollowupConclusions(Long l);
}
