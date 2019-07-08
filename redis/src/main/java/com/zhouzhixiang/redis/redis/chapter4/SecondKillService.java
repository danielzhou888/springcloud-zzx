package com.zhouzhixiang.redis.redis.chapter4;

import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * @program: 秒杀Service
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-05-10 20:16
 */
@Service
public class SecondKillService {

    private RedisLock redisLock;


    public boolean secondKill(RedisUtil redisUtil, String lockey) {
        RedisLock redisLock = new RedisLock(redisUtil, lockey, 60000, 100000);
        try {
            if (redisLock.lock()) {
                String goodsCount = redisUtil.get("goods:iphoneX");
                if (Integer.parseInt(goodsCount) > 0) {
                    System.out.println("抢到了一台iphoneX，还剩余"+(Integer.parseInt("goods:iphoneX") - 1)+"台");
                    redisUtil.set("", String.valueOf(Integer.parseInt("goods:iphoneX") - 1));
                } else {
                    System.out.println("手残党，建议重新抢购！");
                }
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
            // 操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。
            String currentVal = redisLock.getLockeyValue();
            if (Long.parseLong(currentVal) >= System.currentTimeMillis()) {
                redisLock.unlock();
            }
        }
        return false;
    }
}

