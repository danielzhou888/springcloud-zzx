package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.chapter1;

import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.MessageProsessor;
import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FaceFilter;
import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FilterChain;
import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.HtmlFilter;
import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.SensitiveFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
@Slf4j
public class Main {
    public static void main(String[] args) {

        String msg = "你好，软蛋，@，<script/>，我在北京上班";
        FilterChain f1 = new FilterChain();
        f1.addFilter(new HtmlFilter())
                .addFilter(new FaceFilter());
        FilterChain f2 = new FilterChain();
        f2.addFilter(new SensitiveFilter());
        f1.addFilter(f2);
        MessageProsessor mp = new MessageProsessor(msg, f1);
        String result = mp.process();
        log.info(result);
    }
}
