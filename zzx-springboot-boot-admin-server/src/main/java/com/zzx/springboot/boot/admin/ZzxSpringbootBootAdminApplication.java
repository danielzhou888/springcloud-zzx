package com.zzx.springboot.boot.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAdminServer
public class ZzxSpringbootBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzxSpringbootBootAdminApplication.class, args);
    }

}
