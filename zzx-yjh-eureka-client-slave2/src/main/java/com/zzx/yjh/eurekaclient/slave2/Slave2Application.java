package com.zzx.yjh.eurekaclient.slave2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Slave2Application {

    public static void main(String[] args) {
        SpringApplication.run(Slave2Application.class, args);
    }

}
