package com.zzx.design.pattern.zzxdesignpattern.Strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import org.junit.jupiter.api.Test;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest

/**
 * 策略模式
 */
public class Main {

    @Test
    public void test1() {
        Double bookPrice = Double.valueOf(20L);
        Member member = new AdvancedMember();
        BookPrice price = new BookPrice(member);
        System.out.println(price.calcute(bookPrice));
//        Optional.ofNullable(price).ifPresent(p -> System.out.println(p.calcute(bookPrice)));
    }
}
