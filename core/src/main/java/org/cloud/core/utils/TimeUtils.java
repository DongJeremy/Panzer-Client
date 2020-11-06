package org.cloud.core.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtils {
    private static final Time localTime = new Time("Asia/Hong_Kong");

    public static long getStamp() {
        return Calendar.getInstance().getTimeInMillis() / 1000;
    }

    public static long getStampAll() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String getAll() {
        localTime.setToNow();
        return localTime.format("%Y-%m-%d %H:%M:%S");
    }

    public static String getNoChar() {
        localTime.setToNow();
        return localTime.format("%Y%m%d%H%M%S");
    }

    public static String getDate() {
        localTime.setToNow();
        return localTime.format("%Y-%m-%d");
    }

    public static String getTime() {
        localTime.setToNow();
        return localTime.format("%H:%M:%S");
    }

    public static String getYear() {
        localTime.setToNow();
        return localTime.format("%Y");
    }

    public static String getMouth() {
        localTime.setToNow();
        return localTime.format("%m");
    }

    public static String getDay() {
        localTime.setToNow();
        return localTime.format("%d");
    }

    public static String getHour() {
        localTime.setToNow();
        return localTime.format("%H");
    }

    public static String getMinute() {
        localTime.setToNow();
        return localTime.format("%M");
    }

    public static String getSeconds() {
        localTime.setToNow();
        return localTime.format("%S");
    }

    public static String stamp2Time(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j * 1000));
    }

    public static String getWeek() {
        return "";
    }

    public static String time2Stamp(String str) {
        try {
            return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime() / 1000);
        } catch (Exception unused) {
            return "";
        }
    }
}
