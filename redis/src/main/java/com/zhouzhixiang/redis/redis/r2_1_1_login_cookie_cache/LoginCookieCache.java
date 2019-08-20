package com.zhouzhixiang.redis.redis.r2_1_1_login_cookie_cache;

import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录和Cookie缓存
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-09 22:58
 **/
@Component
public class LoginCookieCache {

    @Autowired
    private RedisUtil redisUtil;

    public String checkToken(String token) {
        return String.valueOf(redisUtil.hGet("login:", token));
    }

    /**
     * 更新token，最近登录时间，最近浏览商品（只保留最新25条）<br>
     * 此函数操作每秒钟至少可以记录20000件商品
     * @param token
     * @param userId
     * @param itemId
     */
    public void updateToken(String token, String userId, String itemId) {
        long timeMillis = System.currentTimeMillis();
        redisUtil.hPut("login:", token, userId);
        redisUtil.zAdd("recent:", token, timeMillis);
        if (!StringUtils.isEmpty(itemId)) {
            redisUtil.zAdd("viewed:" + token, itemId, timeMillis);
            redisUtil.zRemoveRange("viewed:" + token, 0, -26);
        }
    }
}
