package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory;

import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory.bis.IQiYiCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
@Slf4j
public class CardCommodityService implements ICommodity {

    @Autowired
    private IQiYiCardService iQiYiCardService;

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        String mobile = "18949239399";
        iQiYiCardService.grantToken(mobile, bizId);
        log.info("请求参数【爱奇艺】success");
    }
}
