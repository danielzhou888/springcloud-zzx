package com.zhouzhixiang.redis.redis.r2_2_item_cart;

import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 使用cookie实现购物车：将整个购物车都存储到cookie里面<br>
 * 优点：无需对数据库进行写入就可以实现购物车功能<br>
 * 缺点：<br>
 * 1. 程序需要解析和验证cookie，保证cookie格式正确，并且包含的商品都是真正可购买的商品<br>
 * 2. 浏览器每次发送请求都会连着cookie一起发送，如果cookie体积较大，那么请求发送和处理速度可能会降低
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-14 15:19
 **/
public class ItemCart {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加或删除购物车
     * @param session   session
     * @param itemId    商品id
     * @param count     商品数量
     */
    @Test
    public void addToCart(String session, String itemId, Integer count) {
        if (count < 0) {
            redisUtil.hDelete("cart:" + session, itemId);
        } else {
            redisUtil.hPut("cart:" + session, itemId, String.valueOf(count));
        }
    }


}
