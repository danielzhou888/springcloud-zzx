package gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-08 09:27
 */
@RestController
public class GateWayController {



    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        System.out.println("=======================================success");
        return Mono.just("fallback");
    }
}
