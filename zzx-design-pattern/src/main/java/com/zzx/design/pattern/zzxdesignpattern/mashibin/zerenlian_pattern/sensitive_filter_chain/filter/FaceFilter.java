package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter;

/**
 * 表情包过滤器
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-03
 */
public class FaceFilter implements com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.Filter {
    @Override
    public String doFilter(String message) {
        return message.replace("@", "^V^").replace("&", "^V^");
    }
}
