package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.LeavePlanDAO;
import com.narendra.sams.core.domain.LeavePlan;
import com.narendra.sams.core.service.LeavePlanService;
import java.util.List;

public class LeavePlanServiceImpl implements LeavePlanService {
    private LeavePlanDAO leavePlanDAO;

    public LeavePlanDAO getLeavePlanDAO() {
        return this.leavePlanDAO;
    }

    public void setLeavePlanDAO(LeavePlanDAO leavePlanDAO) {
        this.leavePlanDAO = leavePlanDAO;
    }

    public void addLeavePlan(LeavePlan leavePlan, Long userId) {
        this.leavePlanDAO.addLeavePlan(leavePlan, userId);
    }

    public LeavePlan getLeavePlan(Long leavePlanId) {
        return getLeavePlan(leavePlanId);
    }

    public void updateLeavePlanReason(Long leavePlanId, String reason) {
        this.leavePlanDAO.updateLeavePlanReason(leavePlanId, reason);
    }

    public void deleteLeavePlan(Long leavePlanId) {
        this.leavePlanDAO.deleteLeavePlan(leavePlanId);
    }

    public List<LeavePlan> getAllLeavePlans(Long instituteId) {
        return this.leavePlanDAO.getAllLeavePlans(instituteId);
    }
}
