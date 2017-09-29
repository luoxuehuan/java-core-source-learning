package com.java.java_8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hulb on 17/8/12.
 */
public class TimeTest {


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

    public static void main(String[] arg) {
        //LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeData = "2017-09-29 15:00:18";


        LocalDateTime localDateTime2 = LocalDateTime.parse(timeData,dateTimeFormatter);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime2.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);


        System.out.println(date.getTime());


        //now.format(dateTimeFormatter);


        //System.out.println(now.format(dateTimeFormatter));

        //System.out.println(tomorrow.toString());


       // LocalDate parse = LocalDate.parse("2017-07-02");
        //System.out.println(parse.toString());


        Instant inst1 = Instant.now();
        System.out.println("Inst1 : " + now.toInstant(ZoneOffset.of("+0")));
        //Instant inst2 = inst1.plus(Duration.ofSeconds(10));
        Instant inst2 = localDateTime2.toInstant(ZoneOffset.of("+0"));
        System.out.println("Inst2 : " + inst2);
        System.out.println("Difference in milliseconds : " + Duration.between(Instant.now(), inst2).toSeconds());

        //System.out.println("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());


    }

    public static long getCurrentDiff(String time) {
        DateTimeFormatter dateFormat = getDateFormat(defaultPattern);
        LocalDateTime localDateTime = LocalDateTime.parse(time,dateFormat);
        return Math.abs(Date.from(localDateTime.atZone(zone).toInstant()).getTime() - new Date().getTime());
    }

}
