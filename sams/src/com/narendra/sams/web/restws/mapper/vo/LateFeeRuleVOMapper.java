package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.web.restws.vo.LateFeeRuleVO;
import java.util.ArrayList;
import java.util.List;

public class LateFeeRuleVOMapper {
    public static List<LateFeeRuleVO> prepareLateFeeRuleVOs(List<LateFeeRule> lateFeeRules) {
        if (lateFeeRules == null) {
            return null;
        }
        List<LateFeeRuleVO> lateFeeRuleVOs = new ArrayList();
        for (LateFeeRule lateFeeRule : lateFeeRules) {
            lateFeeRuleVOs.add(prepareLateFeeRuleVO(lateFeeRule));
        }
        return lateFeeRuleVOs;
    }

    public static LateFeeRuleVO prepareLateFeeRuleVO(LateFeeRule lateFeeRule) {
        if (lateFeeRule == null) {
            return null;
        }
        LateFeeRuleVO lateFeeRuleVO = new LateFeeRuleVO();
        lateFeeRuleVO.setId(lateFeeRule.getId());
        lateFeeRuleVO.setName(lateFeeRule.getName());
        lateFeeRuleVO.setActive(lateFeeRule.getActive());
        return lateFeeRuleVO;
    }
}
