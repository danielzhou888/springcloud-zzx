package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.chapter1.processor;

import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FilterChain;
import lombok.AllArgsConstructor;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
@AllArgsConstructor
public class MessageProcessor {

    private FilterChain filterChain;

    private String message;

    public String process() {
        return filterChain.doFilter(message);
    }
}
