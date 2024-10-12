package org.zzx.nk.nk06;

import org.apache.commons.lang3.time.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToolkit {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static boolean isBetween(Date targetDate, Date startTime, Date endTime) {
        if (targetDate == null || startTime == null || endTime == null) {
            throw new IllegalArgumentException("请求参数日期字段存在空值");
        }
        targetDate = DateUtils.truncate(targetDate, java.util.Calendar.DAY_OF_MONTH);
        startTime = DateUtils.truncate(startTime, java.util.Calendar.DAY_OF_MONTH);
        endTime = DateUtils.truncate(endTime, java.util.Calendar.DAY_OF_MONTH);

        return !targetDate.before(startTime) && !targetDate.after(endTime);
    }

    public static Date parseDate(String source, String pattern) throws ParseException {
        if (source == null || pattern == null) {
            throw new IllegalArgumentException("Source and pattern should not be null");
        }
        return new SimpleDateFormat(pattern).parse(source);
    }

    public static void main(String[] args) {
        try {
            Date startTime = parseDate("2023-06-19", YYYY_MM_DD);
            Date endTime = parseDate("2023-06-25", YYYY_MM_DD);
            Date targetDate1 = parseDate("2023-06-19", YYYY_MM_DD); // 等于 startTime
            Date targetDate2 = parseDate("2023-06-25", YYYY_MM_DD); // 等于 endTime
            Date targetDate3 = parseDate("2023-06-20", YYYY_MM_DD); // 在范围内
            Date targetDate4 = parseDate("2023-06-26", YYYY_MM_DD); // 超出范围

            System.out.println(isBetween(targetDate1, startTime, endTime)); // true
            System.out.println(isBetween(targetDate2, startTime, endTime)); // true
            System.out.println(isBetween(targetDate3, startTime, endTime)); // true
            System.out.println(isBetween(targetDate4, startTime, endTime)); // false
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
