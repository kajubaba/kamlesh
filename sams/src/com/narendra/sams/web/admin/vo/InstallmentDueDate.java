package com.narendra.sams.web.admin.vo;

import java.util.Date;

public class InstallmentDueDate {
    private Date dueDate;
    private String dueDateStr;
    private Long installmentId;
    private Long lateFeeRuleId;

    public Long getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public String getDueDateStr() {
        return this.dueDateStr;
    }

    public void setDueDateStr(String dueDateStr) {
        this.dueDateStr = dueDateStr;
    }

    public Long getLateFeeRuleId() {
        return this.lateFeeRuleId;
    }

    public void setLateFeeRuleId(Long lateFeeRuleId) {
        this.lateFeeRuleId = lateFeeRuleId;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
