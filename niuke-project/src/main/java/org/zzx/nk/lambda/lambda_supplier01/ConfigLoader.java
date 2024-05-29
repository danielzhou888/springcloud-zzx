package org.zzx.nk.lambda.lambda_supplier01;

import java.util.Properties;
import java.util.function.Supplier;

/**
 * 案例2：配置文件加载
 * 在一个复杂的系统中，配置文件可能很大，加载它们可能是一个耗时的操作。我们可以使用 Supplier 来延迟加载配置文件，只有在需要时才加载。
 */
public class ConfigLoader {
    private Supplier<Properties> configSupplier = this::loadConfig;
    private Properties config;

    private Properties loadConfig() {
        // 模拟加载配置文件
        System.out.println("Loading configuration...");
        Properties properties = new Properties();
        properties.setProperty("url", "http://example.com");
        properties.setProperty("timeout", "5000");
        return properties;
    }

    public Properties getConfig() {
        if (config == null) {
            config = configSupplier.get();
        }
        return config;
    }

    public static void main(String[] args) {
        ConfigLoader loader = new ConfigLoader();
        // 第一次访问时加载配置文件
        Properties config1 = loader.getConfig();
        // 后续访问使用已加载的配置文件
        Properties config2 = loader.getConfig();
    }
}