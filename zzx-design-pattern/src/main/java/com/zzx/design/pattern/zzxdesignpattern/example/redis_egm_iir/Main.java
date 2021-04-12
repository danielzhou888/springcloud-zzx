package com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir;

import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory.JDKProxy;
import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory.impl.EGMCacheAdapter;
import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory.impl.IIRCacheAdapter;
import com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.impl.CacheServiceImpl;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.simple_factory_mode.PromotionFactory;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.simple_factory_mode.PromotionSuper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

/**
 * 单机Redis升级集群，工具类的兼容扩展 -》 抽象工厂模式
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Main {


    @Test
    public void testCache() throws Exception {
        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("zhouzhixiang", "1111");
        String zhouzhixiang = proxy_EGM.get("zhouzhixiang");

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("mabaoguo", "2222");
        String mabaoguo = proxy_IIR.get("mabaoguo");
        log.info("zhouzhixiang = {}, mabaoguo = {}", zhouzhixiang, mabaoguo);
    }

}
