package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 过滤器链
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-03
 */
public class FilterChain implements com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.Filter {
    private List<com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.Filter> filters = new ArrayList<>();

    public FilterChain addFilter(com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.sensitive_filter_chain.filter.Filter filter) {
        filters.add(filter);
        return this;
    }

    public String doFilter(String message) {
        AtomicReference<String> s = new AtomicReference<>(message);
        filters.forEach(f -> s.set(f.doFilter(s.get())));
        return s.get();
    }
}
