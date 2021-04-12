package com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
public interface ICacheAdapter {

    String get(String key);

    void set(String key, String value);

    void setExpire(String key, String value, long timeout, TimeUnit timeUnit);

    void delete(String key);
}
