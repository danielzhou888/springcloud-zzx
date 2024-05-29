package org.zzx.nk.lambda.lambda_supplier01;

import java.util.function.Supplier;

/**
 * 案例1：数据库连接
 * 在某些情况下，数据库连接可能是一个昂贵的操作，因此我们可以使用 Supplier 来延迟初始化数据库连接，只有在需要时才创建连接。
 */
public class DatabaseConnection {
    private Supplier<Connection> connectionSupplier = this::createConnection;
    private Connection connection;

    private Connection createConnection() {
        // 模拟创建数据库连接
        System.out.println("Creating database connection...");
        return new Connection();
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = connectionSupplier.get();
        }
        return connection;
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        // 第一次访问时创建连接
        Connection conn1 = db.getConnection();
        // 后续访问使用已创建的连接
        Connection conn2 = db.getConnection();
    }
}

class Connection {
    // 模拟数据库连接类
}