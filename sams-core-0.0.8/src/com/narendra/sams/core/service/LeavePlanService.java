package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.LeavePlan;
import java.util.List;

public interface LeavePlanService {
    void addLeavePlan(LeavePlan leavePlan, Long l);

    void deleteLeavePlan(Long l);

    List<LeavePlan> getAllLeavePlans(Long l);

    LeavePlan getLeavePlan(Long l);

    void updateLeavePlanReason(Long l, String str);
}
