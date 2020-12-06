package com.zzx.sentinel.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zzx.sentinel.order")
public class SentinelOrderAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelOrderAllApplication.class, args);
    }

}
