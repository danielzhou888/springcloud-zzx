package com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory.impl;

import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory.ICacheAdapter;
import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.util.IIR;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
public class IIRCacheAdapter implements ICacheAdapter {

    private IIR iir = new IIR();

    @Override
    public String get(String key) {
        return iir.get(key);
    }

    @Override
    public void set(String key, String value) {
        iir.set(key, value);
    }

    @Override
    public void setExpire(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key, value, timeout, timeUnit);
    }

    @Override
    public void delete(String key) {
        iir.del(key);
    }
}
