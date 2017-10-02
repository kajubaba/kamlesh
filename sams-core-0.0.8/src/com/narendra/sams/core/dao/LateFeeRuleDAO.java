package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface LateFeeRuleDAO {
    Long addLateFeeRule(LateFeeRule lateFeeRule, Long l) throws DuplicateNameFoundException;

    List<LateFeeRule> getAllLateFeeRules(Long l);

    LateFeeRule getLateFeeRule(Long l);

    Boolean isLateFeeRuleExist(Long l, String str);

    LateFeeRule loadLateFeeRuleByName(Long l, String str);

    void updateLateFeeRule(LateFeeRule lateFeeRule, Long l) throws DuplicateNameFoundException;
}
