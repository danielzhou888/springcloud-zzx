package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

/**
 * 商城促销：策略模式测试
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Main {

    private double total = 0;

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
        PromotionContext promotionContext = null;
        if ("正常收费".equals(type)) {
            promotionContext = new PromotionContext(new NormalPromotion());
        } else if ("满300返100".equals(type)) {
            promotionContext = new PromotionContext(new ReturnPromotion(300, 100));
        } else if ("打8折".equals(type)) {
            promotionContext = new PromotionContext(new DiscountPromotion(0.8));
        }

        double totalPrice = promotionContext.calcute(price * num);
        total += totalPrice;
        log.info("单价："+price+" 数量："+num+" 合计："+totalPrice);
    }

}
