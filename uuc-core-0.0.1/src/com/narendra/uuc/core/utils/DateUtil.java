package com.narendra.uuc.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String dd_MMM_yyyy = "dd-MMM-yyyy";
    public static final String dd_MMM_yyyy_hh_mm_ss = "dd-MMM-yyyy HH:mm:ss";

    public static Date getSystemDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static Date getSystemDate() {
        return Calendar.getInstance().getTime();
    }

    public static Date parseDate(String dateStr, String dateFormat) throws ParseException {
        return new SimpleDateFormat(dateFormat).parse(dateStr);
    }

    public static String formatDate(Date date, String dateFormat) throws ParseException {
        return new SimpleDateFormat(dateFormat).format(date);
    }

    public static Date closeToNextDate(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(10, 23);
        cal.add(12, 59);
        cal.add(13, 59);
        return cal.getTime();
    }

    public static Date makeStartDate(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal.getTime();
    }

    public static Date makeEndDate(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }

    public static Date oneMonthBefore(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, -1);
        return cal.getTime();
    }
}
