package com.narendra.sams.web.admission.vo;

import java.util.Date;

public class CustomizeDueDateVO {
    private Long customizeInstallmentId;
    private Date dueDate;
    private String dueDateStr;
    private Long latefeeRuleId;

    public Long getLatefeeRuleId() {
        return this.latefeeRuleId;
    }

    public void setLatefeeRuleId(Long latefeeRuleId) {
        this.latefeeRuleId = latefeeRuleId;
    }

    public Long getCustomizeInstallmentId() {
        return this.customizeInstallmentId;
    }

    public void setCustomizeInstallmentId(Long customizeInstallmentId) {
        this.customizeInstallmentId = customizeInstallmentId;
    }

    public String getDueDateStr() {
        return this.dueDateStr;
    }

    public void setDueDateStr(String dueDateStr) {
        this.dueDateStr = dueDateStr;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
