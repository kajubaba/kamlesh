package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface LateFeeRuleService {
    Long addLateFeeRule(LateFeeRule lateFeeRule, Long l) throws DuplicateNameFoundException;

    List<LateFeeRule> getAllLateFeeRules(Long l);

    LateFeeRule getLateFeeRule(Long l);

    void updateLateFeeRule(LateFeeRule lateFeeRule, Long l) throws DuplicateNameFoundException;
}
