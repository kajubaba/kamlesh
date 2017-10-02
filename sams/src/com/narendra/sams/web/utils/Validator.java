package com.narendra.sams.web.utils;

import com.narendra.sams.core.util.DateUtil;
import java.text.ParseException;
import java.util.Date;

public class Validator {
    public static Date convertToDate(String dateStr) {
        if (!(dateStr == null || dateStr.trim().isEmpty())) {
            try {
                return DateUtil.parseDate(dateStr, "dd-MMM-yyyy");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
