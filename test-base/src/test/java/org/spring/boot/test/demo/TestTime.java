package org.spring.boot.test.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/24
 */
public class TestTime {
    public static void main(String[] args) {
        //当前时间Date
        Date now = new Date();
        System.out.println(now);
        //Wed Jan 31 23:32:03 GMT+08:00 2018
        //例如我的环境时区为：(UTC+08:00)北京，重庆，香港特别行政区，乌鲁木齐（+0800）
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        System.out.println(sdf.getTimeZone());
        System.out.println(sdf.format(now));
        //sun.util.calendar.ZoneInfo[id="GMT+08:00",offset=28800000,dstSavings=0,useDaylight=false,transitiOns=0,lastRule=null]
        //2018-01-31T23:32:03.469+0800
    }
}
