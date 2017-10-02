package com.narendra.sams.admission.utils;

import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LateFeeCalculator {
    public static Boolean isDateOverDue(Date date) {
        Date dueDate = DateUtil.makeStartDate(date);
        Date todayDate = DateUtil.makeStartDate(DateUtil.getSystemDate());
        if (dueDate == null || !todayDate.after(dueDate)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static long findOverDueDays(Date startDate, Date endDate) {
        Date start = DateUtil.makeStartDate(startDate);
        Date end = DateUtil.makeStartDate(endDate);
        long diffDays = (end.getTime() - start.getTime()) / 86400000;
        if (diffDays < 0) {
            return 0;
        }
        return diffDays;
    }

    public static long calculateLateFee(long overDueDays, LateFeeRule lateFeeRule) {
        long lateFee = 0;
        if (lateFeeRule != null) {
            List<LateFeeRuleVO> rules = parseLateFeeRule(lateFeeRule.getRule());
            if (!(rules == null || rules.isEmpty())) {
                for (LateFeeRuleVO rule : rules) {
                    lateFee += calculateFine(overDueDays, rule.getFrom(), rule.getTo(), rule.getFineAmount(), rule.getFineRule(), rule.getFinePeriod());
                }
            }
        }
        return lateFee;
    }

    public static long calculateFine(long days, Short from, Short to, Short fineAmount, String fineRule, String finePeriod) {
        if (days < ((long) from.shortValue())) {
            return 0;
        }
        if ("per_day".equals(finePeriod)) {
            long effectiveDays;
            if (to == null || to.shortValue() == (short) 0) {
                effectiveDays = (days - ((long) from.shortValue())) + 1;
            } else if (days >= ((long) to.shortValue())) {
                effectiveDays = (long) ((to.shortValue() - from.shortValue()) + 1);
            } else {
                effectiveDays = (days - ((long) from.shortValue())) + 1;
            }
            if ("rs".equals(fineRule)) {
                return ((long) fineAmount.shortValue()) * effectiveDays;
            }
            if ("%_inst".equals(fineRule)) {
                return 0;
            }
            "%_inst_due".equals(fineRule);
            return 0;
        } else if (!"period".equals(finePeriod)) {
            return 0;
        } else {
            if ("rs".equals(fineRule)) {
                return (long) fineAmount.shortValue();
            }
            if ("%_inst".equals(fineRule)) {
                return 0;
            }
            "%_inst_due".equals(fineRule);
            return 0;
        }
    }

    public static List<LateFeeRuleVO> parseLateFeeRule(String lateFeeRule) {
        if (lateFeeRule == null) {
            return null;
        }
        List<LateFeeRuleVO> lateFeeRuleVOs = new ArrayList();
        String[] rules = lateFeeRule.split("->");
        if (rules == null || rules.length <= 0) {
            return lateFeeRuleVOs;
        }
        for (String rule : rules) {
            LateFeeRuleVO lateFeeRuleVO = new LateFeeRuleVO();
            String[] ruleStr = rule.split(" ");
            lateFeeRuleVO.setFineAmount(Short.valueOf(ruleStr[1]));
            lateFeeRuleVO.setFineRule(ruleStr[2]);
            lateFeeRuleVO.setFinePeriod(ruleStr[3]);
            String[] days = ruleStr[0].split("-");
            lateFeeRuleVO.setFrom(Short.valueOf(days[0]));
            if (!"NA".equals(days[1])) {
                lateFeeRuleVO.setTo(Short.valueOf(days[1]));
            }
            lateFeeRuleVOs.add(lateFeeRuleVO);
        }
        return lateFeeRuleVOs;
    }
}
