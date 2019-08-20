package com.zhouzhixiang.redis.redis.r2_1_1_login_cookie_cache;

import com.zhouzhixiang.redis.redis.ZhouzhixiangRedisApplication;
import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-10 22:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZhouzhixiangRedisApplication.class)
@WebAppConfiguration
public class Main {

    @Autowired
    private LoginCookieCache loginCookieCache;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testLogin() {
        String token = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        for (int i = 0; i < 40; i++) {
            String itemId = UUID.randomUUID().toString();
            loginCookieCache.updateToken(token, userId, itemId);
        }
    }

    @Test
    public void test1() {
        Long aLong = redisUtil.zRemoveRangeByScore("viewed:8a1a0f03-b13e-40f6-ba76-e72ffac077d4", 0, -1);
        System.out.println(aLong);
    }

}
