package com.zzx.yjh.gateway3.zzxyjhgateway3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 9983
 */
@SpringBootApplication
@RestController
@RequestMapping("/")
public class ZzxYjhGateway3Application {

    public static void main(String[] args) {
        SpringApplication.run(ZzxYjhGateway3Application.class, args);
    }

}
