package com.zzx.tools.refreshIpPv;
 
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 
public class Controler {
    public static void main(String[] args) {
 
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(8);
        scheduledThreadPool.schedule(SpiderUtil.excutorBulid("https://www.dextools.io/app/pancakeswap/pair-explorer/0x73578e84cff078f2f51fa16826d4238419a1e44b",150),1, TimeUnit.SECONDS);
        scheduledThreadPool.schedule(SpiderUtil.excutorBulid("https://www.dextools.io/app/pancakeswap/pair-explorer/0x73578e84cff078f2f51fa16826d4238419a1e44b",150),1, TimeUnit.SECONDS);
        //scheduledThreadPool.schedule(SpiderUtil.excutorBulid("http://www.1313.cc/shua/refresh.html?url=https://www.dextools.io/app/pancakeswap/pair-explorer/0x73578e84cff078f2f51fa16826d4238419a1e44b",150),1, TimeUnit.SECONDS);
        //scheduledThreadPool.schedule(SpiderUtil.excutorBulid("http://www.1313.cc/shua/refresh.html?url=https://www.dextools.io/app/pancakeswap/pair-explorer/0x73578e84cff078f2f51fa16826d4238419a1e44b",150),1,TimeUnit.SECONDS);
        //scheduledThreadPool.schedule(SpiderUtil.excutorBulid("https://www.xicidaili.com/wt/",150),1,TimeUnit.SECONDS);
        //scheduledThreadPool.schedule(SpiderUtil.excutorBulid("https://www.xicidaili.com/wn/",150),1,TimeUnit.SECONDS);
        //scheduledThreadPool.schedule(SpiderUtil.excutorBulid("https://ip.jiangxianli.com/?page=",150),1,TimeUnit.SECONDS);
 
        //SpiderUtil.spiderUrl("xxxxx");
 
        scheduledThreadPool.schedule(ClickUtil.excutorBuild(5000),30,TimeUnit.SECONDS);
        scheduledThreadPool.schedule(ClickUtil.excutorBuild(5000),60,TimeUnit.SECONDS);
        scheduledThreadPool.schedule(ClickUtil.excutorBuild(5000),90,TimeUnit.SECONDS);

    }
}