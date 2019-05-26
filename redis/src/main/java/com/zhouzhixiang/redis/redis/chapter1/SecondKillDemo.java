package com.zhouzhixiang.redis.redis.chapter1;//package com.zhouzhixiang.redis.chapter1;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.stream.IntStream;
//
///**
// * 秒杀处理测试
// */
//public class SecondKillDemo {
//
//    private static Jedis jedis;
//    protected static final Logger logger = LoggerFactory.getLogger(JedisPool.class);
//
//    public static void main(String[] args) {
//        /** 初始化商品 */
//        try {
//            initGoodsToRedis();
//
//            logger.info("init goods success ........");
//
//            /** 1000线程抢购100个商品 */
//            ExecutorService executorService = Executors.newFixedThreadPool(20);
//            final CountDownLatch countDownLatch = new CountDownLatch(1000);
//            long startTime = System.currentTimeMillis();
//
//            IntStream.rangeClosed(1, 1000).forEach(i -> {
//                executorService.execute(new SecondKillHandler(
//                        new Goods(null, "iphoneX", 9999.00),
//                        new Person("zhouzhixiang-"+i),
//                        countDownLatch));
//            });
//
//            executorService.shutdown();
//            countDownLatch.await();
//            long time = System.currentTimeMillis() - startTime;
//            System.out.println("共耗时：" + time + "毫秒");
//            System.in.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 此处不关闭Jedis连接池，如果关闭，其他线程未执行完毕
//            RedisUtil.close();
//        }
//    }
//
//    /**
//     * 初始化商品数量
//     */
//    public static void initGoodsToRedis() {
//        Jedis jedis = RedisUtil.getJedis();
//        jedis.set("goods:iphoneX", "100");
//        RedisUtil.returnResource(jedis);
//    }
//}