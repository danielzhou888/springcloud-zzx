package com.zhouzhixiang.redis.redis.lock;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/**
 * 七种方案！探讨Redis分布式锁的正确使用姿势
 * https://juejin.cn/post/6936956908007850014
 */
public class o1_分布式锁实现案例 {

    public static void main(String[] args) {

    }

    /**
     * Redis分布式锁方案三：使用Lua脚本(包含SETNX + EXPIRE两条指令)
     *
     * lua脚本：
     * if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then
     *    redis.call('expire',KEYS[1],ARGV[2])
     * else
     *    return 0
     * end;
     */
    public void testLua() {
        // if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then
        //    redis.call('expire', KEYS[1], ARGV[2])
        // else
        //    return 0
        // end;

//        String lua_scripts = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then" +
//                " redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
//        Object result = jedis.eval(lua_scripts, Collections.singletonList(key_resource_id), Collections.singletonList(values));
//        //判断是否成功
//        return result.equals(1L);
    }

    /**
     * Redis分布式锁方案方案四：SET的扩展命令（SET EX PX NX）
     *
     * 但是呢，这个方案还是可能存在问题：
     *
     * 问题一：锁过期释放了，业务还没执行完。假设线程a获取锁成功，一直在执行临界区的代码。但是100s过去后，它还没执行完。但是，这时候锁已经过期了，此时线程b又请求过来。显然线程b就可以获得锁成功，也开始执行临界区的代码。那么问题就来了，临界区的业务代码都不是严格串行执行的啦。
     * 问题二：锁被别的线程误删。假设线程a执行完后，去释放锁。但是它不知道当前的锁可能是线程b持有的（线程a去释放锁时，有可能过期时间已经到了，此时线程b进来占有了锁）。那线程a就把线程b的锁释放掉了，但是线程b临界区业务代码可能都还没执行完呢。
     *
     * 作者：捡田螺的小男孩
     * 链接：https://juejin.cn/post/6936956908007850014
     * 来源：稀土掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void testSet() {
        // if (jedis.set(key, value, 'NX', 'EX', 100) == 1) { // 加锁
        //    try {
        //       doSomething();
        //    catch (Exception e) {
        //    } finally {
        //       jedis.delete(key); // 释放锁
        //    }
        // }

    }

    /**
     * 方案五：SET EX PX NX + 校验唯一随机值,再删除
     * 既然锁可能被别的线程误删，那我们给value值设置一个标记当前线程唯一的随机数，在删除的时候，校验一下，不就OK了嘛
     *
     */
    public void testSet2() {
        // String lockValue = randomGenerate();
        // if (jedis.set(key, lockValue, 'NX', 'EX', 100) == 1) {
        //      try {
        //          doSomething();
        //      } catch (Exception e) {
        //      } finally {
        //          // 判断是不是当前线程加的锁,是才释放 ->非原子操作，此处可以使用lua脚本替代
        //          if (lockValue.equals(jedis.get(key))) {
        //              jedis.delete(key);
        //          }
        //      }
        // }


        // 为了更严谨，一般也是用lua脚本代替。lua脚本如下：
        // if redis.call('get',KEYS[1]) == ARGV[1] then
        //   return redis.call('del',KEYS[1])
        // else
        //   return 0
        // end;
    }
}
