package com.narendra.sams.core.address.service;

import com.narendra.sams.core.util.DateUtil;
import java.text.ParseException;

public class StopApplicationService {
    private String stopApplicationAfter;

    public String getStopApplicationAfter() {
        return this.stopApplicationAfter;
    }

    public void setStopApplicationAfter(String stopApplicationAfter) {
        this.stopApplicationAfter = stopApplicationAfter;
    }

    public Boolean stopApplication() {
        if (!(this.stopApplicationAfter == null || this.stopApplicationAfter.isEmpty())) {
            try {
                if (DateUtil.getSystemDate().after(DateUtil.parseDate(this.stopApplicationAfter, DateUtil.dd_MM_yyyy))) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return Boolean.FALSE;
    }

    public String getWarningMessage() {
        String msg = "";
        if (!(this.stopApplicationAfter == null || this.stopApplicationAfter.isEmpty())) {
            try {
                if (DateUtil.getSystemDate().after(DateUtil.oneMonthBefore(DateUtil.parseDate(this.stopApplicationAfter, DateUtil.dd_MM_yyyy)))) {
                    return "Your annual maintenance fee for this year is due and application will stop functioning from " + this.stopApplicationAfter + ". Please contact your service provider for payment.";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
