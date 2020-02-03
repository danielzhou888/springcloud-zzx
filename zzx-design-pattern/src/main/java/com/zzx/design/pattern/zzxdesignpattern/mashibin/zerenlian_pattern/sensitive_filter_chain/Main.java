package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain;

import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FaceFilter;
import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FilterChain;
import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.SensitiveFilter;

/**
 * 敏感信息过滤-》责任链设计模式
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-02
 */
public class Main {

    public static void main(String[] args) {
        String msg = "你好，软蛋，@，<script/>，我在北京上班";
        com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FilterChain fc = new com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FilterChain();
        fc.addFilter(new com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.HtmlFilter())
                .addFilter(new FaceFilter());
        FilterChain fc2 = new FilterChain();
        fc2.addFilter(new SensitiveFilter());
        fc.addFilter(fc2);
        String result = new MessageProsessor(msg, fc).process();
        System.out.println(result);
    }
}
