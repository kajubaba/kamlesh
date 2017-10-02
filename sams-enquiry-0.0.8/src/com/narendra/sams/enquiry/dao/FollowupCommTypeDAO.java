package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.FollowupCommType;
import java.util.List;

public interface FollowupCommTypeDAO {
    List<FollowupCommType> getFollowupTypes(Long l);
}
