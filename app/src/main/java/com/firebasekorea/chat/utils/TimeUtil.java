package com.firebasekorea.chat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by namhoonkim on 17/02/2017.
 */

public class TimeUtil {

    private static SimpleDateFormat getTimestampStringFormat(){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static String getDeviceCreatedDateTimeString(int offsetDay, int offsetHour, int offsetMin){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = getTimestampStringFormat();
        formatter.setTimeZone(TimeZone.getDefault());

        cal.add(Calendar.MINUTE, offsetMin); // adds one min
        cal.add(Calendar.HOUR_OF_DAY, offsetHour); // adds one hour
        cal.add(Calendar.DAY_OF_MONTH, offsetDay); // adds one day
        return formatter.format(cal.getTime());

    }

    public static String getDeviceCreatedDateOnlyString(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatterDateOnly = new SimpleDateFormat("yyyy-MM-dd");
        formatterDateOnly.setTimeZone(TimeZone.getDefault());
        return formatterDateOnly.format(cal.getTime());
    }

    public static Long getDeviceCreatedDateOnlyLong(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatterDateOnly = new SimpleDateFormat("yyyyMMdd");
        formatterDateOnly.setTimeZone(TimeZone.getDefault());

        String result = formatterDateOnly.format(cal.getTime());

        return Long.parseLong(result);
    }

    public static String getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int result = cal.get(Calendar.YEAR);
        return result + "";
    }

    public static String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        int result = cal.get(Calendar.MONTH) + 1;
        return result + "";
    }

    public static String getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        int result = cal.get(Calendar.DAY_OF_MONTH);
        return result + "";
    }
}

