//package com.zhouzhixiang.redis.redis.chapter2;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class SecondKillService {
//
//
//    /***
//     * 抢购代码
//     * @param redisUtil
//     * @param key pronum 首先用客户端设置数量
//     * @return
//     */
//    public boolean seckill(RedisUtil redisUtil, String key) {
//        RedisLock lock = new RedisLock(redisUtil, key, 10000, 20000);
//        try {
//            if (lock.lock()) {
//
//                // 需要加锁的代码
//                String pronum = lock.get("goods:iphoneX");
//                // 修改库存
//                if (Integer.parseInt(pronum) - 1 >= 0) {
//                    System.out.println("抢到了一台iphoneX，还剩余"+(Integer.parseInt(pronum) - 1)+"台");
//                    lock.set("goods:iphoneX", String.valueOf(Integer.parseInt(pronum) - 1));
//                } else {
//                    System.out.println("手残党，建议重新抢购！");
//                }
//                return true;
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            // 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
//            // 操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
//            lock.unlock();
//        }
//        return false;
//    }
//}
