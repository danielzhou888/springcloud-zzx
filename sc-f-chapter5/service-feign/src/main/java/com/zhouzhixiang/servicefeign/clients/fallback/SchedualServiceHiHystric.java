package com.zhouzhixiang.servicefeign.clients.fallback;

import com.zhouzhixiang.servicefeign.clients.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-03-25 13:49
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry, feign default hystric, you are fail, "+ name;
    }
}
