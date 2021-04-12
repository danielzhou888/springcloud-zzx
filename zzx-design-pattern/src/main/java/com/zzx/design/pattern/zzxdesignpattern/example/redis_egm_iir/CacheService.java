package com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2020-11-30
 */
public interface CacheService {

    String get(String key);

    void set(String key, String value);

    void setExpire(String key, String value, long timeout, TimeUnit timeUnit);

    void delete(String key);
}
