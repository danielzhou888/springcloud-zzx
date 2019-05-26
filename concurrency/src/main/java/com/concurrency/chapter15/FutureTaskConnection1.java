package com.concurrency.chapter15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 错误示例
 *
 * @description: 在很多高并发的环境下，往往我们只需要某些任务只执行一次。
 * 这种使用情景FutureTask的特性恰能胜任。举一个例子，假设有一个带key的连接池，
 * 当key存在时，即直接返回key对应的对象；当key不存在时，则创建连接。对于这样的应用场景，
 * 通常采用的方法为使用一个Map对象来存储key和连接池对应的对应关系，典型的代码如下
 * 在例子中，我们通过加锁确保高并发环境下的线程安全，也确保了connection只创建一次，然而却牺牲了性能。
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-14 20:22
 */
public class FutureTaskConnection1 {

    private static Map<String, Connection> connectionPool = new HashMap<>();
    private static ReentrantLock lock = new ReentrantLock();

    public static Connection getConnection(String key) {
        try {
            lock.lock();
            Connection connection = connectionPool.get(key);
            if (connection == null) {
                Connection newConnection = createConnection();
                connectionPool.put(key, newConnection);
                return newConnection;
            }
            return connection;
        } finally {
            lock.unlock();
        }
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
