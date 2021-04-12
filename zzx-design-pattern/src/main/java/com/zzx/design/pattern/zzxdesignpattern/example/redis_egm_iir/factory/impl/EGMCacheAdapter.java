package com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory.impl;

import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory.ICacheAdapter;
import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.util.EGM;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
public class EGMCacheAdapter implements ICacheAdapter {

    private EGM egm = new EGM();

    @Override
    public String get(String key) {
        return egm.gain(key);
    }

    @Override
    public void set(String key, String value) {
        egm.set(key, value);
    }

    @Override
    public void setExpire(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    @Override
    public void delete(String key) {
        egm.delete(key);
    }
}
