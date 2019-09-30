package com.appy.android.appycore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormat {

    private static final String ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String UTC_TIMEZONE_NAME = "UTC";

    public static String createHourDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(date);
    }

    public static String createDayDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(date);
    }

    public static String getDay(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.getDefault());
        return sdf.format(date);
    }

    public static String getYesterday() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return createDayDate(date);
    }

    public static boolean isToday(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.getDefault());
        Date c = Calendar.getInstance().getTime();
        String currentDay = sdf.format(c);
        String day = sdf.format(date);
        return day.equals(currentDay);
    }

    public static boolean isNow(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
        Date c = Calendar.getInstance().getTime();
        String currentDay = sdf.format(c);
        String day = sdf.format(date);
        return day.equals(currentDay);
    }

    public static Date stringToDate(String s) {
        Date day = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            day = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static SimpleDateFormat provideDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET, Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE_NAME));
        return simpleDateFormat;
    }

    public static String timestampToDateString(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000);
        return android.text.format.DateFormat.format("dd-MM-yyyy", cal).toString();

    }
}
