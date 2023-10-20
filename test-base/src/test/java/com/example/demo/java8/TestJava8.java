package com.example.demo.java8;

import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Base64;
import org.junit.Test;

/**
 * @author Chris
 * @date 2020/04/24
 * @since 1.0.0
 */
public class TestJava8 {
    @Test
    public void testParallelSort() {
        System.out.println("===== test1 =====");
        int[] ints = {1, 3, 2, 5, 4};
        Arrays.parallelSort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    @Test
    public void testBase64() {
        System.out.println("===== testBase64  =====");
        String base64 = Base64.getEncoder().encodeToString("aaa".getBytes(StandardCharsets.UTF_8));
        System.out.println(base64);
        byte[] bytes = Base64.getDecoder().decode(base64);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    // https://www.cnblogs.com/muscleape/p/9956754.html
    // Java 8的日期和时间类包含LocalDate、LocalTime、Instant、Duration以及Period，这些类都包含在java.time包中;
    @Test
    public void testDateTime() {
        System.out.println("===== testDateTime =====");
        // LocalDate类表示一个具体的日期，但不包含具体时间，也不包含时区信息。可以通过LocalDate的静态方法of()创建一个实例，
        // LocalDate也包含一些方法用来获取年份，月份，天，星期几等：
        LocalDate now = LocalDate.now();
        System.out.println(now); // 2020-04-24
        System.out.println(now.getYear()); // 2020
        System.out.println(now.getMonth()); // APRIL
        System.out.println(now.getMonthValue()); // 4
        System.out.println(now.getDayOfMonth()); // 24

        LocalDate localDate = LocalDate.of(2017, 1, 4);     // 初始化一个日期：2017-01-04
        int year = localDate.getYear();                     // 年份：2017
        Month month = localDate.getMonth();                 // 月份：JANUARY
        int dayOfMonth = localDate.getDayOfMonth();         // 月份中的第几天：4
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();     // 一周的第几天：WEDNESDAY
        int length = localDate.lengthOfMonth();             // 月份的天数：31
        boolean leapYear = localDate.isLeapYear();          // 是否为闰年：false
        System.out.println(year);
        System.out.println(month);
        System.out.println(dayOfMonth);
        System.out.println(dayOfWeek);
        System.out.println(length);
        System.out.println(leapYear);

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime); // 11:21:56.831
        LocalDateTime localDateTime = now.atTime(localTime);
        System.out.println(localDateTime); // 2020-04-24T11:21:56.831

    }
}
