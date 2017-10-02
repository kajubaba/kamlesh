package com.narendra.sams.enquiry.service;

import com.narendra.sams.enquiry.domain.FollowupNextAction;
import java.util.List;

public interface FollowupNextActionService {
    List<FollowupNextAction> getFollowupNextActions(Long l);
}
