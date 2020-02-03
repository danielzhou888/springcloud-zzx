package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain;

import com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.FilterChain;
import lombok.AllArgsConstructor;

/**
 * 信息处理器
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-02
 */
@AllArgsConstructor
public class MessageProsessor {
    private String message;

    private FilterChain filterChain;

    public String process() {

        return filterChain.doFilter(message);
    }
}
