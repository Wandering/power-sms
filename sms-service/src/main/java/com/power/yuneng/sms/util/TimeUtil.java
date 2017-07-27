package com.power.yuneng.sms.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gryang on 16/05/11.
 */
public class TimeUtil {
    public static long strTimeConvertLong(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String longTimeConvertStrDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        return DateFormatUtils.format(calendar, "yyyy-MM-dd");
    }

    public static String longTimeConvertStr(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));

        return DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss");
    }

    public static long dayBegin(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        return calendar.getTimeInMillis();
    }

    public static long dayEnd(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);

        return calendar.getTimeInMillis();
    }

    public static String spaceNumFromNowDay(int space) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) - space);
        return DateFormatUtils.format(calendar, "yyyy-MM-dd");
    }

    public static List<String> getWeekStrDay() {
        List<String> weekSet = new ArrayList<String>();
        for(int i = 6; i >= 0; i--) {
            weekSet.add(spaceNumFromNowDay(i));
        }
        return weekSet;
    }

    public static void main(String[] args) {
        long time = TimeUtil.dayBegin(System.currentTimeMillis());
        System.err.println(time);
        System.err.println(DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss"));
    }
}
