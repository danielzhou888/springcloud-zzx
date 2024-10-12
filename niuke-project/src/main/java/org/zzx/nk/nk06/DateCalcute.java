package org.zzx.nk.nk06;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-07-01 17:04
 */
public class DateCalcute {
    public static void main(String[] args) throws ParseException {
        String date1 = "2024-06-28 10:04:04,740";
        String date2 = "2024-06-28 10:04:05,160";
        // 计算两个时间，并获取毫秒时间
        long diff = getDiffMillis(date1, date2);
        System.out.println("cost time : " + diff);


        long total = 96 + 35 + 42 + 21 + 52 + 45 + 731 + 420;
        System.out.println("total = "+total);
    }

    private static long getDiffMillis(String date1, String date2) throws ParseException {
        // 指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        // 转换为日期格式
        java.util.Date date11 = sdf.parse(date1);
        java.util.Date date22 = sdf.parse(date2);
        // 计算两个日期差值
        long diff = date22.getTime() - date11.getTime();
        // 返回毫秒时间
        return diff;
    }
}
