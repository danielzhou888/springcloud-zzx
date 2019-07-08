package com.zhouzhixiang.redis.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zhouzhixiang.redis.redis")
public class ZhouzhixiangRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhouzhixiangRedisApplication.class, args);
    }
}
