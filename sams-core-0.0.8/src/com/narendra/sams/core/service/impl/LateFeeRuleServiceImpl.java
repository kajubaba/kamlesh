package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.LateFeeRuleDAO;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.LateFeeRuleService;
import java.util.List;

public class LateFeeRuleServiceImpl implements LateFeeRuleService {
    private LateFeeRuleDAO lateFeeRuleDAO;

    public LateFeeRuleDAO getLateFeeRuleDAO() {
        return this.lateFeeRuleDAO;
    }

    public void setLateFeeRuleDAO(LateFeeRuleDAO lateFeeRuleDAO) {
        this.lateFeeRuleDAO = lateFeeRuleDAO;
    }

    public Long addLateFeeRule(LateFeeRule lateFeeRule, Long userId) throws DuplicateNameFoundException {
        if (!this.lateFeeRuleDAO.isLateFeeRuleExist(lateFeeRule.getInstitute().getId(), lateFeeRule.getName().trim()).booleanValue()) {
            return this.lateFeeRuleDAO.addLateFeeRule(lateFeeRule, userId);
        }
        throw new DuplicateNameFoundException("Late Fee Rule ['" + lateFeeRule.getName() + "'] already exist");
    }

    public void updateLateFeeRule(LateFeeRule lateFeeRule, Long userId) throws DuplicateNameFoundException {
        LateFeeRule loadedLateFeeRule = this.lateFeeRuleDAO.loadLateFeeRuleByName(lateFeeRule.getInstitute().getId(), lateFeeRule.getName().trim());
        if (loadedLateFeeRule == null || loadedLateFeeRule.getId().equals(lateFeeRule.getId())) {
            this.lateFeeRuleDAO.updateLateFeeRule(lateFeeRule, userId);
            return;
        }
        throw new DuplicateNameFoundException("Late Fee Rule ['" + lateFeeRule.getName() + "'] already exists");
    }

    public LateFeeRule getLateFeeRule(Long lateFeeRuleId) {
        return this.lateFeeRuleDAO.getLateFeeRule(lateFeeRuleId);
    }

    public List<LateFeeRule> getAllLateFeeRules(Long instituteId) {
        return this.lateFeeRuleDAO.getAllLateFeeRules(instituteId);
    }
}
