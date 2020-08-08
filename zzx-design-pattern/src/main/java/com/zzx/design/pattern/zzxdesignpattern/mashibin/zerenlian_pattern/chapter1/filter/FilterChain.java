package com.zzx.design.pattern.zzxdesignpattern.mashibin.zerenlian_pattern.chapter1.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
public class FilterChain implements Filter {

    private List<Filter> filterList = new ArrayList<>();

    private int index = 0;

    @Override
    public String doFilter(String message) {
        if (index == filterList.size()) {
            return message;
        }
        index++;
        Filter filter = filterList.get(index);
        return filter.doFilter(message);
    }
}
