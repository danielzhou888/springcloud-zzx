package org.zzx.nk.lambda.lambda_supplier01;

import java.util.function.Supplier;

/**
 * 案例3：单例模式
 * 在单例模式中，我们可以使用 Supplier 来延迟初始化单例实例，只有在第一次访问时才创建实例。
 */
public class Singleton {
    private static Supplier<Singleton> instanceSupplier = Singleton::createInstance;
    private static Singleton instance;

    private Singleton() {
        // 私有构造函数，防止直接实例化
    }

    private static Singleton createInstance() {
        // 模拟创建单例实例
        System.out.println("Creating Singleton instance...");
        return new Singleton();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = instanceSupplier.get();
        }
        return instance;
    }

    public static void main(String[] args) {
        // 第一次访问时创建单例实例
        Singleton singleton1 = Singleton.getInstance();
        // 后续访问使用已创建的单例实例
        Singleton singleton2 = Singleton.getInstance();
    }
}