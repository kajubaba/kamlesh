package com.narendra.sams.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String MM_dd_yyyy = "MM/dd/yyyy";
    public static final String dd_MMM_yyyy = "dd-MMM-yyyy";
    public static final String dd_MMM_yyyy_hh_mm_a = "dd-MMM-yyyy hh:mm a";
    public static final String dd_MMM_yyyy_hh_mm_ss = "dd-MMM-yyyy HH:mm:ss";
    public static final String dd_MM_yyyy = "dd/MM/yyyy";

    public static Date getSystemDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static Date getSystemDate() {
        return Calendar.getInstance().getTime();
    }

    public static Date parseDate(String dateStr, String dateFormat) throws ParseException {
        return new SimpleDateFormat(dateFormat).parse(dateStr);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(parseDate("7/4/2014 13:02:23", dd_MMM_yyyy_hh_mm_a));
    }

    public static String formatDate(Date date, String dateFormat) {
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

    public static Date getDateWithoutTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date getTomorrowDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, 1);
        return cal.getTime();
    }
}
