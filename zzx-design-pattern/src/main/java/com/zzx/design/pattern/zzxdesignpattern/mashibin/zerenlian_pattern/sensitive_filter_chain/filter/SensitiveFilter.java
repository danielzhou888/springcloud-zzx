package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter;

/**
 * 敏感词过滤器
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-02
 */
public class SensitiveFilter implements com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.Filter {
    @Override
    public String doFilter(String message) {
        return message.replace("王八蛋", "*").replace("软蛋", "*").replace("滚犊子", "*");
    }
}
