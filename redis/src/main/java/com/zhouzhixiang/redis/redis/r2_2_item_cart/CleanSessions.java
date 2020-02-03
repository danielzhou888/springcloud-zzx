package com.zhouzhixiang.redis.redis.r2_2_item_cart;

import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 存储会话所需内存会随着时间的推移不断增加，需要定期清理会话数据，设计只保存最新的1000万个会话<br>
 * 1. 轮询（休眠1s）<br>
 * 2. 检测会话有序集合是否超限，超限则移除最多100个旧令牌，并从记录用户登录信息的散列删除对应的用户信息，否则休眠1s<br>
 * 3. 删除最近浏览商品记录有序集合<br>
 * 4. 清理旧会话对应的购物车
 *
 * 注意：如果清理函数正在删除某个用户的信息，而这个用户又在同一时间访问网站的话，会产生竞态条件，导致用户信息被错误的删除；<br>
 * 不良影响：会使得用户需要重新登录一次<br>
 * 后期计划：补上竞态条件的解决方案
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-14 14:01
 **/
@Component
public class CleanSessions {

    @Autowired
    private RedisUtil redisUtil;
    private static final Integer MAX_SESSIONS_COUNT = 10000000;


    /**
     * 清理会话
     */
    @Test
    public void cleanSessions() {
        while (true) {
            Long size = redisUtil.zZCard("recent:");
            if (size == null || size < MAX_SESSIONS_COUNT) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else {
                Integer end = Integer.min((int) (size - MAX_SESSIONS_COUNT), 100);
                Set<String> tokens = redisUtil.zRange("recent:", 0, end);
                Set<String> sessionKeys = new HashSet<>();
                if (tokens != null && tokens.size() > 0) {
                    tokens.forEach(token -> {
                        sessionKeys.add("viewed:" + token);
                        sessionKeys.add("cart:" + token);
                    });
                }
                redisUtil.delete(sessionKeys);
                redisUtil.hDelete("login:", tokens);
                redisUtil.zRemove("recent:", tokens);
            }
        }
    }
}
