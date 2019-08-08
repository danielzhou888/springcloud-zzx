package com.zzx.yjh.configclient.zzxyjhconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/config-client")
@EnableEurekaClient
@EnableDiscoveryClient
public class ZzxYjhConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzxYjhConfigClientApplication.class, args);
    }

    @Value(value = "${foo}")
    private String foo;
    @Value(value = "${test}")
    private String test;

    @GetMapping("/getFoo")
    public String getFoo() {
        return foo;
    }


    @GetMapping("/getTest")
    public String getTest() {
        return test;
    }
}
