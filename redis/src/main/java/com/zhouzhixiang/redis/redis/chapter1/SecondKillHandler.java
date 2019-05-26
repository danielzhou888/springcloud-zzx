package com.zhouzhixiang.redis.redis.chapter1;//package com.zhouzhixiang.redis.chapter1;
//
//import org.springframework.util.CollectionUtils;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Transaction;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
///**
// * 秒杀线程逻辑处理类
// */
//public class SecondKillHandler implements Runnable {
//
//    private String key = ""; // 监视的key 当前秒杀商品的数量
//
//    private Goods goods;
//    private Jedis jedis;
//    private Person person;
//    private final CountDownLatch countDownLatch;
//
//    public SecondKillHandler(Goods goods, Person person, CountDownLatch countDownLatch) {
//        this.goods = goods;
//        this.person = person;
//        this.countDownLatch = countDownLatch;
//        key = "goods:" + goods.getGoodsname();
//    }
//
//    @Override
//    public void run() {
//        Transaction transaction = null;
//        while (true) {
//            try {
//                jedis = RedisUtil.getJedis();
//                if (jedis == null) {
//                    throw new NullPointerException("Jedis is Null");
//                }
//                // watch 监视此key，当事务执行之前这个key发生了改变，事务就会被打断
//                jedis.watch(key);
//                // 当前剩余商品的数量 此处如果直接使用jedis.get(key)会产生线程安全问题，故使用封装的同步的获取方法
////                int currentGoodsCount = Integer.parseInt(RedisUtil.getString(key, jedis));
//                int currentGoodsCount = Integer.parseInt(jedis.get(key));
////                System.out.println("==========================CurrentGoodsCount:::::"+currentGoodsCount);
//                if (currentGoodsCount <= 0) {
//                    System.out.println("商品已抢完，" +person.getUsername()+"--->抢购失败" );
//                    break;
//                }
//                // 开启事务
//                transaction = jedis.multi();
//                // 商品数量减1
//                transaction.incrBy(key, -1);
//                // 执行事务
//                List<Object> exec = transaction.exec();
//
//                if (CollectionUtils.isEmpty(exec)) {
//                    System.out.println(person.getUsername() + "---> 抢购失败，继续抢购");
//                    Thread.sleep(1);
//                } else {
//                    exec.forEach(succ -> {
//                        String successStr = person.getUsername()
//                                + "====> 成功抢购到编号【"+((100 - currentGoodsCount) + 1)+"】商品，"
//                                + "该商品剩余："+succ.toString() ;
//                        System.out.println(successStr);
//                        jedis.set("goodsResult:"+person.getUsername(),successStr);
//                    });
//                    break;
//                }
//            } catch (InterruptedException e) {
//                RedisUtil.returnResource(jedis);
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (transaction != null) {
//                        transaction.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                // 此处需要注意，并发环境下，当前的jedis可能已经被其他线程所归还并且取消监视
//                if (jedis != null) {
//                    jedis.unwatch();
//                    RedisUtil.returnResource(jedis);
//                }
//            }
//        }
//        countDownLatch.countDown();
//    }
//}