package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.chapter1.filter;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
public class HtmlFilter implements Filter {

    @Override
    public String doFilter(String message) {
        return message.replace("<", "[").replace("/>", "]");
    }
}
