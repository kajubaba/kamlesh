package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.FollowupNextAction;
import java.util.List;

public interface FollowupNextActionDAO {
    List<FollowupNextAction> getFollowupNextActions(Long l);
}
