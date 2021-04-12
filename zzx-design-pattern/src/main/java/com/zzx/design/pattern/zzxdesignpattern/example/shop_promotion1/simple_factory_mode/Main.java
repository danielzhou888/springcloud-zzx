package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.simple_factory_mode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

/**
 * 商城促销：简单工厂模式测试
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Main {

    private static double total = 0;

    @Test
    public void testCalcute() {

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            consume("正常收费", 1, 1000);
        });
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            consume("满300返100", 1, 1000);
        });
        CompletableFuture<Double> f3 = f1.thenCombine(f2, (__, tf) -> {
            consume("打8折", 1, 1000);
            return total;
        });
        f3.join();
        log.info("total : "+total);
    }

    public void consume(String type, int num, double price) {
        PromotionSuper promotion = PromotionFactory.createPromotion(type);
        double totalPrice = 0;
        totalPrice = promotion.calcutePrice(price * num);
        total += totalPrice;
        log.info("单价："+price+" 数量："+num+" 合计："+totalPrice);
    }
}
