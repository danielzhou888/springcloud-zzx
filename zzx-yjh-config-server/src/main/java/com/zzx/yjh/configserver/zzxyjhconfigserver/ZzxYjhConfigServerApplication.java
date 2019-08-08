package com.zzx.yjh.configserver.zzxyjhconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@RestController
@EnableEurekaClient
public class ZzxYjhConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzxYjhConfigServerApplication.class, args);
    }

}
