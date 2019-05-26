package com.zhouzhixiang.redis.redis.chapter1;//package com.scmd.redis.chapter1;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.exceptions.JedisConnectionException;
//import redis.clients.jedis.exceptions.JedisException;
//
///**
// * Redis客户端链接工具类
// */
//public class JedisPoolUtil {
//
//    private static JedisPool pool;
//    private static final Logger logger = LoggerFactory.getLogger(JedisPool.class);
//
//    public static void main(String[] args) {
//        Jedis jedis = getJedis();
//        System.out.println(jedis.get("name"));
//
//    }
//
//    /**
//     * Jedis池
//     */
//    public static void createJedisPool() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        //设置最大连接数（100个足够用了，没必要设置太大）
//        config.setMaxTotal(100);
//        //获取Jedis连接的最大等待时间（50秒）
//        config.setMaxWaitMillis(50 * 1000);
//        //在获取Jedis连接时，自动检验连接是否可用
//        config.setTestOnBorrow(true);
//        //在将连接放回池中前，自动检验连接是否有效
//        config.setTestOnReturn(true);
//        //自动测试池中的空闲连接是否都是可用连接
//        config.setTestWhileIdle(true);
//        //最大空闲连接数
//        config.setMaxIdle(10);
//        //创建连接池
//        pool = new JedisPool(config, "10.100.1.46", 6379, 2000, null, 0);
//    }
//
//    /**
//     * 获取一个Jedis对象
//     * @return
//     */
//    public static Jedis getJedis() {
//        Jedis jedis = null;
//        if (pool == null) {
//            poolInit();
//        }
//        int timeoutCount = 0;
//        while (true) {
//            try {
//                if (pool != null) {
//                    jedis = pool.getResource();
////                    jedis.auth(password);
//                    return jedis;
//                }
//            } catch (Exception e) {
//                if (e instanceof JedisConnectionException) {
//                    timeoutCount++;
//                    logger.warn("getJedis timeoutCount={}", timeoutCount);
//                    if (timeoutCount > 3) {
//                        break;
//                    }
//                } else {
//                    logger.warn("jedisInfo ... NumActive=" + pool.getNumActive()
//                            + ", NumIdle=" + pool.getNumIdle()
//                            + ", NumWaiter=" + pool.getNumWaiters()
//                            + ", isClosed=" + pool.isClosed());
//                    logger.error("GetJedis error,", e);
//                    break;
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 多线程环境同步初始化（保证项目中有且仅有一个连接池）
//     */
//    private synchronized static void poolInit() {
//        if (pool == null) {
//            createJedisPool();
//        }
//    }
//
//    /**
//     * 归还一个Jedis连接
//     * @param jedis
//     */
//    public static void returnRes(Jedis jedis) {
//        if (jedis != null) {
//            // 废弃不用
////            pool.returnResourceObject(jedis);
//            //从源码可以分析得到，如果是使用连接池的形式，这个并非真正的close,而是把连接放回连接池中
//            jedis.close();
//        }
//    }
//
//    /**
//     * 关闭连接池
//     */
//    public static void close() {
//        pool.close();
//    }
//
//    public static void set(String key, String value) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            jedis.set(key, value);
//        } catch (Exception e) {
//            logger.error("set operation error, ", e);
//            throw new JedisException(e.getMessage(), e);
//        } finally {
//            returnRes(jedis);
//        }
//    }
//
//    public static String get(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            return jedis.get(key);
//        } catch (Exception e) {
//            logger.error("get operation error, ", e);
//            throw new JedisException(e.getMessage(), e);
//        } finally {
//            returnRes(jedis);
//        }
//    }
//
//    /**
//     * set with expire milliseconds
//     *
//     * @param key
//     * @param value
//     * @param seconds
//     * @return
//     */
//    public static void set(String key, String value, long seconds) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            //* @param nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
//            //     *                     *          if it already exist.
//            //     *                     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
//            jedis.set(key, value, "NX", "EX", seconds);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new JedisException(e.getMessage(),e);
//        } finally {
//            returnRes(jedis);
//        }
//    }
//
//    public static Long incr(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            return jedis.incr(key);
//        } catch (Exception e) {
//            logger.error("incr operation error, ", e);
//            throw new JedisException(e.getMessage(), e);
//        } finally {
//            returnRes(jedis);
//        }
//    }
//}
