package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.FollowupCommWith;
import java.util.List;

public interface FollowupCommWithDAO {
    List<FollowupCommWith> getCommunicationWith(Long l);
}
