package com.zzx.yjh.gateway.zzxyjhgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ZzxYjhGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzxYjhGatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator myRoute(RouteLocatorBuilder builder) {
//        return builder.routes().route(p -> p
//            .path("*/get")
//            .filters(f -> f.addRequestHeader("zhou", "zhixiang"))
//            .uri("http://fangjia-gateway:8789/route")
//        ).build();
//    }

    @GetMapping("/get")
    public String get() {
        return "fangjia-gateway:get";
    }

    @GetMapping("/route")
    public String route() {
        return "fangjia-gateway:route";
    }

}
