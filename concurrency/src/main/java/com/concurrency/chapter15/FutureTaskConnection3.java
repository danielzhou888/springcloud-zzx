package com.concurrency.chapter15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: springcloud-zzx
 *
 * @description: FutureTask在高并发环境下确保任务只执行一次
 * 这时最需要解决的问题就是当key不存在时，创建Connection的动作（conn=createConnection();）
 * 能放在connectionPool.putIfAbsent()之后执行，这正是FutureTask发挥作用的时机，
 * 基于ConcurrentHashMap和FutureTask的改造代码如下：
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-14 20:46
 */
public class FutureTaskConnection3 {

    private static ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<String, FutureTask<Connection>>();

    public static Connection getConnection(String key) {
        FutureTask<Connection> connectionFutureTask = connectionPool.get(key);
        try {
            if (connectionFutureTask != null) {
                return connectionFutureTask.get();
            } else {
                Callable<Connection> callable = new Callable<Connection>() {
                    @Override
                    public Connection call() throws Exception {
                        return createConnection();
                    }
                };
                FutureTask<Connection> newTask = new FutureTask<>(callable);
                FutureTask<Connection> returnFt = connectionPool.putIfAbsent(key, newTask);
                if (returnFt == null) {
                    connectionFutureTask = newTask;
                    newTask.run();
                }
                return connectionFutureTask.get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
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
