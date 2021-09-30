package com.mogawe.mosurvei.util.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by glenrynaldi on 10/14/15.
 */
public class DateUtil {

    private static DateFormat fullDateFormat;

    static {
        fullDateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
    }

    public static String toFullDate(Date date) {
        return fullDateFormat.format(date);
    }

    public static final String EEEE_DD_MMM_YYYY = "EEEE, dd MMM yyyy";
    public static final String FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("id", "ID"));
        return simpleDateFormat.format(date);
    }

    public static String format(long date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("id", "ID"));
        return simpleDateFormat.format(new Date(date));
    }

    public static Date format(String dateInString, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("id", "ID"));
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getTimeStringFromDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.US);
        return dateFormat.format(date);
    }

    public static String getDateStringFromDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        return dateFormat.format(date);
    }

    public static String getLastMessageTimestamp(Date utcDate) {
        if (utcDate != null) {
            Calendar todayCalendar = Calendar.getInstance();
            Calendar localCalendar = Calendar.getInstance();
            localCalendar.setTime(utcDate);

            if (getDateStringFromDate(todayCalendar.getTime())
                    .equals(getDateStringFromDate(localCalendar.getTime()))) {

                return getTimeStringFromDate(utcDate);

            } else if ((todayCalendar.get(Calendar.DATE) - localCalendar.get(Calendar.DATE)) == 1) {
                return "Yesterday";
            } else {
                return getDateStringFromDate(utcDate);
            }
        } else {
            return null;
        }
    }

}
