package com.zzx.spring.zzxspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.zzx.spring.zzxspring.flter.*")
public class ZzxSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzxSpringApplication.class, args);
    }

}
