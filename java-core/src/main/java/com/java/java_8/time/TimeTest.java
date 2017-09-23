package com.java.java_8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by hulb on 17/8/12.
 */
public class TimeTest {
    public static void main(String[] arg) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        now.format(dateTimeFormatter);
        System.out.println(now.format(dateTimeFormatter));
        LocalDate parse = LocalDate.parse("2017-07-02");
        System.out.println(tomorrow.toString());
        System.out.println(parse.toString());



    }
}
