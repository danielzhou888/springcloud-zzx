package com.zzx.yjh.eureka.client.slaveone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SlaveoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlaveoneApplication.class, args);
    }

}
