package com.zzx.tools.refreshIpPv;
 
import org.jsoup.Jsoup;
 
public class CheckIPUtil {
    //测试代理IP是否可用
    public static boolean checkProxy(String ip, Integer port) {
        System.out.println("检查中："+ip);
        try {
            Jsoup.connect("http://www.baidu.com")
                    .timeout(1 * 1000)
                    .proxy(ip, port)
                    .get();
            System.out.println(ip+"可用");
            return true;
        } catch (Exception e) {
            System.out.println("失败，"+ip+"不可用");
            return false;
        }
    }
 
    public static boolean checkProxy(String s){
        String[] strings = s.split(" ");
        return checkProxy(strings[0],Integer.parseInt(strings[1]));
    }
}