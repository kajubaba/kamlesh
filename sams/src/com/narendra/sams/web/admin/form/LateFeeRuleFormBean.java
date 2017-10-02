package com.narendra.sams.web.admin.form;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.web.admin.vo.LateFeeRuleVO;
import java.util.List;

public class LateFeeRuleFormBean {
    private Boolean active;
    private String desc;
    private Long id;
    private Institute institute;
    private List<LateFeeRuleVO> lateFeeRules;
    private String name;
    private String rule = "";

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRule() {
        if (getLateFeeRules() != null) {
            int count = 0;
            for (LateFeeRuleVO lateFeeRuleVO : getLateFeeRules()) {
                if (count != 0) {
                    this.rule += "->" + lateFeeRuleVO.toString();
                } else {
                    this.rule += lateFeeRuleVO.toString();
                }
                count++;
            }
        }
        return this.rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<LateFeeRuleVO> getLateFeeRules() {
        return this.lateFeeRules;
    }

    public void setLateFeeRules(List<LateFeeRuleVO> lateFeeRules) {
        this.lateFeeRules = lateFeeRules;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
