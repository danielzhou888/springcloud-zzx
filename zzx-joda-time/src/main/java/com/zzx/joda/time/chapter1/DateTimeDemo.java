package com.zzx.joda.time.chapter1;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Zhou / zzx
 * @Date 2019-05-27 00:28
 **/
public class DateTimeDemo {

    @Test
    public void t1() {
        DateTime dateTime = new DateTime(2019, 5, 27, 0, 0, 0);
        System.out.println(dateTime);

        // 以 JDK 的方式向某一个瞬间加上 90 天并输出结果
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        SimpleDateFormat sdf =
            new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss.SSS");
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        System.out.println(sdf.format(calendar.getTime()));

        // 以 Joda 的方式向某一个瞬间加上 90 天并输出结果
        String s = dateTime.plusDays(90).toString();
        System.out.println(s);

        // 距离 当前时间 45 天之后的某天在下一个月的当前周的最后一天的日期。
        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue());


        dateTime = new DateTime(calendar);
        dateTime = new DateTime(new Date());

        String timeStr = "2019-05-27T00:00:00.000+08:00";
        dateTime = new DateTime(timeStr);
        timeStr = "2019-05-2";
        dateTime = new DateTime(timeStr);

        // ==========================



    }

    /**
     * 局部时间
     */
    @Test
    public void t2() {
        // 取系统时间
        DateTime dt1 = new DateTime();
        System.out.println(dt1);

        // 通过java.util.Date生成
        DateTime dt2 = new DateTime();
        System.out.println(dt2);

        // 指定年月日点分秒生成
        DateTime dt3 = new DateTime(2019, 5, 20, 11, 12, 4, 0);
        System.out.println(dt3);

        // ISO8601形式生成
        DateTime dt4 = new DateTime("2019-05-27");
        System.out.println(dt4);
        DateTime dt5 = new DateTime("2019-05-27T00:00:00.000+08:00");

        // 只需要年月日
        LocalDate localDate = new LocalDate(2019, 5, 27);
        System.out.println(localDate);

        // 只需要时分秒毫秒
        LocalTime localTime = new LocalTime(14, 33, 26, 0);
        System.out.println(localTime);

    }

    /**
     * 与JDK日期对象转换
     * 许多代码都使用了 JDK Date 和 Calendar 类。但是幸亏有 Joda，可以执行任何必要的日期算法，然后再转换回 JDK 类。
     * 这将两者的优点集中到一起。您在本文中看到的所有 Joda 类都可以从 JDK Calendar 或 Date 创建，正如您在 创建 JodaTime对象 中看到的那样。
     * 出于同样的原因，可以从您所见过的任何 Joda 类创建 JDK Calendar 或 Date。
     */
    @Test
    public void t3() {
        DateTime dateTime = new DateTime();

        Date d1 = new Date(dateTime.getMillis());
        Date d2 = dateTime.toDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime.getMillis());

        calendar.setTimeInMillis(dateTime.getMillis());

        Calendar calendar1 = dateTime.toCalendar(Locale.getDefault());
    }

    /**
     * 日期计算
     */
    @Test
    public void calcuteDate() {
        // 假设在当前的系统日期下，我希望计算上一个月的最后一天。对于这个例子，我并不关心一天中的时间，因为我只需要获得年/月/日
        LocalDate now = new LocalDate(new Date());
        LocalDate lastDayOfPreviousMonth = now.minusMonths(1).dayOfMonth().withMaximumValue();

        // 希望获得任何一年中的第 11 月的第一个星期二的日期，而这天必须是在这个月的第一个星期一之后。
        // .setCopy("Monday") 是整个计算的关键。不管中间LocalDate 值是多少，将其 dayOfWeek 属性设置为 Monday 总是能够四舍五入，
        // 这样的话，在每月的开始再加上 6 天就能够让您得到第一个星期一。再加上一天就得到第一个星期二。Joda 使得执行此类计算变得非常容易。
        LocalDate localDate =
            now.monthOfYear().setCopy(11).dayOfMonth().withMinimumValue().plusDays(6).dayOfWeek().setCopy("Monday")
                .plusDays(1);

        DateTime dt = new DateTime();
        //昨天
        DateTime yesterday = dt.minusDays(1);
        //明天
        DateTime tomorrow = dt.plusDays(1);
        //1个月前
        DateTime before1month = dt.minusMonths(1);
        //3个月后
        DateTime after3month = dt.plusMonths(3);
        //2年前
        DateTime before2year = dt.minusYears(2);
        //5年后
        DateTime after5year = dt.plusYears(5);
    }

    /**
     * 格式化时间
     * 使用 JDK 格式化日期以实现打印是完全可以的，但是我始终认为它应该更简单一些。
     * 这是 Joda 设计者进行了改进的另一个特性。要格式化一个 Joda 对象，调用它的 toString() 方法，
     * 并且如果您愿意的话，传递一个标准的 ISO8601或一个 JDK 兼容的控制字符串，以告诉 JDK 如何执行格式化。
     * 不需要创建单独的 SimpleDateFormat 对象
     * （但是 Joda 的确为那些喜欢自找麻烦的人提供了一个DateTimeFormatter 类）。
     * 调用 Joda 对象的 toString() 方法，仅此而已。
     */
    @Test
    public void formatTime() {
        DateTime dateTime = new DateTime();

        System.out.println(dateTime.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
        System.out.println(dateTime.toString(ISODateTimeFormat.basicDateTime()));
        System.out.println(dateTime.toString(ISODateTimeFormat.basicOrdinalDateTime()));
        System.out.println(dateTime.toString(ISODateTimeFormat.weekDateTime()));

        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
        System.out.println(dateTime1.toString("dd-MM-yyyy HH:mm:ss"));
        System.out.println(dateTime1.toString("yyyy-mm-dd HH:mm:ss"));


    }
}
