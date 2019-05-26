package com.zhouzhixiang.redis.redis.chapter1;//package com.zhouzhixiang.redis.chapter1;
//
//import org.junit.Test;
//import redis.clients.jedis.Jedis;
//
//public class GoodsToRedis {
//    @Test
//    public void initGoodsToRedis() {
//        Jedis jedis = RedisUtil.getJedis();
//
//        jedis.set("goods:iphoneX", "100");
//        RedisUtil.returnResource(jedis);
//        RedisUtil.close();
//    }
//}
