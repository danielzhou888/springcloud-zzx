package com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.impl;

import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.CacheService;

import java.util.concurrent.TimeUnit;

/**
 * 缓存数据操作类
 * @author zhouzhixiang
 * @Date 2020-11-30
 */
public class CacheServiceImpl implements CacheService {


    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void setExpire(String key, String value, long timeout, TimeUnit timeUnit) {

    }

    @Override
    public void delete(String key) {

    }
}
