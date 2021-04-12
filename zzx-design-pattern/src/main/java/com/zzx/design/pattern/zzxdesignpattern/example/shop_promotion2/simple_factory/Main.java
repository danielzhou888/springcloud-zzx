package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Main {

    @Test
    private void test() throws Exception {
        StoreFactory storeFactory = new StoreFactory();
        ICommodity iCommodity = storeFactory.getICommodity(1);
        iCommodity.sendCommodity("1", "2", "1", new HashMap<>());
    }
}
