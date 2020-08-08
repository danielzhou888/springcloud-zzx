package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.chapter1.filter;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
public class SensitiveFilter implements Filter {

    @Override
    public String doFilter(String message) {
        return message.replace("王八蛋", "*").replace("软蛋", "*").replace("滚犊子", "*");
    }
}
