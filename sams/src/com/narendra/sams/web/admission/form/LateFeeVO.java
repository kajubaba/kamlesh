package com.narendra.sams.web.admission.form;

import com.narendra.sams.admission.domain.DaysOverdue;
import java.util.List;

public class LateFeeVO {
    private List<DaysOverdue> daysOverdues;

    public List<DaysOverdue> getDaysOverdues() {
        return this.daysOverdues;
    }

    public void setDaysOverdues(List<DaysOverdue> daysOverdues) {
        this.daysOverdues = daysOverdues;
    }
}
