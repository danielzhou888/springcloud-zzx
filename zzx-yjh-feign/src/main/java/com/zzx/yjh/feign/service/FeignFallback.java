package com.zzx.yjh.feign.service;

import org.springframework.stereotype.Component;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
@Component
public class FeignFallback implements IFeignService {
    @Override
    public String hello(String name) {
        return "feign -> name = " + name + ", sorry fall back!";
    }
}
