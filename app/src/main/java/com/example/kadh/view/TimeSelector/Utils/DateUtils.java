package com.example.kadh.view.TimeSelector.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liuli on 2015/11/27.
 */
public class DateUtils {

    public static final String DEFAULT_TEMPLATE_DAY = "yyyy-MM-dd";
    public static final String DEFAULT_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
    }


    /**
     * -日期控件
     * * /
     * /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */

    public static Date parse(String strDate, String pattern) {

        if (TextUtil.isEmpty(strDate)) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }


    /**
     * 获取指定月份的最大日期数
     * added by bingli on 2017/9/7
     *
     * @param year  指定年份
     * @param month 指定月份
     * @return 指定月份的最大日期数.
     */
    public static int getMaxDate(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /*获取系统时间 格式为："yyyy/MM/dd "*/
    public static String getCurrentDate(String s) {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(s);
        return sf.format(d);
    }

}
