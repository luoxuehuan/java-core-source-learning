package com.java.java_8.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 时间工具类
 *
 * @author: baisong
 * @create: 16/9/21
 */
public class DateUtil {

    //默认此种时间格式
    public static String defaultPattern = "yyyy-MM-dd HH:mm:ss";
    public static ZoneId zone = ZoneId.systemDefault();

    private static Map<String, DateTimeFormatter> dateFormatMap = new HashMap<>();

    static {
        dateFormatMap.put(defaultPattern, DateTimeFormatter.ofPattern(defaultPattern));
    }

    private static DateTimeFormatter getDateFormat(String pattern) {
        DateTimeFormatter dateFormat = dateFormatMap.get(pattern);
        if (dateFormat == null) {
            dateFormat = DateTimeFormatter.ofPattern(pattern);
            dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat;
    }

    /**
     * 计算 time 和 当前的时间差.
     * 历史代码，不要随意修改!!! (采过坑, 白松)
     *
     * @param time
     * @return
     */
    public static long getCurrentDiff(String time) {
        DateTimeFormatter dateFormat = getDateFormat(defaultPattern);
        LocalDateTime localDateTime = LocalDateTime.parse(time,dateFormat);
        return Math.abs(Date.from(localDateTime.atZone(zone).toInstant()).getTime() - new Date().getTime());
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getCurrentDiff("2017-09-29 15:14:40")/1000);
    }

}