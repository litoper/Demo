package com.example.kadh.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class DateUtils {
    private static SimpleDateFormat sf = null;

    public enum DateFormat {
        FORMAT_DD("yyyy-MM-dd"),
        FORMAT_MM("yyyy-MM-dd HH:mm"),
        FORMAT_SS("yyyy-MM-dd HH:mm:ss"),
        FORMAT_SSS("yyyy-MM-dd HH:mm:ss.SSS");

        private String mDateFormat;

        DateFormat(String dateFormat) {
            mDateFormat = dateFormat;
        }

        public String getDateFormat() {
            return mDateFormat;
        }
    }

    /*获取系统时间*/
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate(DateFormat dateFormat) {
        Date d = new Date();
        sf = new SimpleDateFormat(dateFormat.getDateFormat());
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    @SuppressLint("SimpleDateFormat")
    public static String getDateToString(long time, DateFormat dateFormat) {
        Date d = new Date(time);
        sf = new SimpleDateFormat(dateFormat.getDateFormat());
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    @SuppressLint("SimpleDateFormat")
    public static long getStringToDate(String time, DateFormat dateFormat) {
        Date date = new Date();
        sf = new SimpleDateFormat(dateFormat.getDateFormat());
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
