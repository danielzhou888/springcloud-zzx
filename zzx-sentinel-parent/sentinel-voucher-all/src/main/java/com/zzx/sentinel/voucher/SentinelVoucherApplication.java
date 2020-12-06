package com.zzx.sentinel.voucher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zzx.sentinel.voucher")
public class SentinelVoucherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelVoucherApplication.class, args);
    }

}
