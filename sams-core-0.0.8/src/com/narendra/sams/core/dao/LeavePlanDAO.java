package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.LeavePlan;
import java.util.List;

public interface LeavePlanDAO {
    void addLeavePlan(LeavePlan leavePlan, Long l);

    void deleteLeavePlan(Long l);

    List<LeavePlan> getAllLeavePlans(Long l);

    LeavePlan getLeavePlan(Long l);

    void updateLeavePlanReason(Long l, String str);
}
