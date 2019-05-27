package com.zzx.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzx.mybatis.dao")
public class ZzxSpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzxSpringbootMybatisApplication.class, args);
    }

}
