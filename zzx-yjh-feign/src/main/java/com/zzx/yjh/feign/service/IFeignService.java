package com.zzx.yjh.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
@FeignClient(name = "fangjia-eureka-client-slave1", fallback = FeignFallback.class)
public interface IFeignService {

    @RequestMapping(value = "/eurekaClient/slave1/hello", method = RequestMethod.GET)
    String hello(@RequestParam(value = "name") String name);
}
