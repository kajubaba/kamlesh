package com.narendra.sams.web.admin.controller;

import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.LateFeeRuleService;
import com.narendra.sams.web.admin.form.LateFeeRuleFormBean;
import com.narendra.sams.web.admin.vo.LateFeeRuleVO;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/admin/latefeerule"})
public class LateFeeRuleController {
    @Autowired
    private LateFeeRuleService lateFeeRuleService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public String listRules(Model model) {
        model.addAttribute("rules", this.lateFeeRuleService.getAllLateFeeRules(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
        return "admin_late_fee_rule_list";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/new"})
    public String newRule() {
        return "admin_late_fee_rule_form";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public String addRule(LateFeeRuleFormBean lateFeeRuleFormBean) {
        LateFeeRule lateFeeRule = new LateFeeRule();
        lateFeeRule.setId(lateFeeRuleFormBean.getId());
        lateFeeRule.setName(lateFeeRuleFormBean.getName());
        lateFeeRule.setRule(lateFeeRuleFormBean.getRule());
        lateFeeRule.setActive(Boolean.TRUE);
        lateFeeRule.setDesc(lateFeeRule.getDesc());
        lateFeeRule.setRuleType("STD");
        lateFeeRule.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        Boolean duplicateExist = Boolean.FALSE;
        Long lateFeeRuleId = null;
        if (lateFeeRule.getId() == null) {
            try {
                lateFeeRuleId = this.lateFeeRuleService.addLateFeeRule(lateFeeRule, LoggedinUserAssistant.getLoggedInUserId());
            } catch (DuplicateNameFoundException e) {
                duplicateExist = Boolean.TRUE;
                e.printStackTrace();
            }
        } else {
            try {
                lateFeeRuleId = lateFeeRule.getId();
                this.lateFeeRuleService.updateLateFeeRule(lateFeeRule, LoggedinUserAssistant.getLoggedInUserId());
            } catch (DuplicateNameFoundException e2) {
                duplicateExist = Boolean.TRUE;
                e2.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject();
        if (duplicateExist.booleanValue()) {
            jsonObject.put("status", AjaxStatus.DUPLICATE);
        } else {
            jsonObject.put("status", AjaxStatus.OK);
            jsonObject.put("id", lateFeeRuleId);
        }
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/view"})
    public String viewRule(Long lateFeeRuleId, Model model) {
        LateFeeRule lateFeeRule = this.lateFeeRuleService.getLateFeeRule(lateFeeRuleId);
        List<LateFeeRuleVO> rules = prepare(lateFeeRule.getRule());
        model.addAttribute("lateFeeRule", lateFeeRule);
        model.addAttribute("rules", rules);
        return "admin_late_fee_rule_form";
    }

    public List<LateFeeRuleVO> prepare(String ruleString) {
        if (ruleString == null) {
            return null;
        }
        List<LateFeeRuleVO> lateFeeRuleVOs = new ArrayList();
        String[] rules = ruleString.split("->");
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
