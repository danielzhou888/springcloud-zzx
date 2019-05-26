package com.concurrency.chapter15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: springcloud-zzx
 *
 * @description: 改用ConcurrentHash的情况下，几乎可以避免加锁的操作，性能大大提高。
 *
 * 但是在高并发的情况下有可能出现Connection被创建多次的现象。
 * 为什么呢？因为创建Connection是一个耗时操作，假设多个线程涌入getConnection方法，都发现key对应的键不存在，
 * 于是所有涌入的线程都开始执行conn=createConnection()，只不过最终只有一个线程能将connection插入到map里。
 * 但是这样以来，其它线程创建的的connection就没啥价值，浪费系统开销。
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-14 20:35
 */
public class FutrueTaskConnection2 {
    private static ConcurrentHashMap<String, Connection> connectionPool = new ConcurrentHashMap<>();

    public static Connection getConnection(String key) {
        Connection connection = connectionPool.get(key);
        if (connection == null) {
            connection = createConnection();
            //根据putIfAbsent的返回值判断是否有线程抢先插入了
            Connection returnConnection = connectionPool.putIfAbsent(key, connection);
            if (returnConnection != null) {
                connection = returnConnection;
            }
        } else {
            return connection;
        }
        return connection;
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
