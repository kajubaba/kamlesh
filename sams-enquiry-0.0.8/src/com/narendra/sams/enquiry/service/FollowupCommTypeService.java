package com.narendra.sams.enquiry.service;

import com.narendra.sams.enquiry.domain.FollowupCommType;
import java.util.List;

public interface FollowupCommTypeService {
    List<FollowupCommType> getFollowupTypes(Long l);
}
