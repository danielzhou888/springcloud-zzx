package com.zzx.design.pattern.zzxdesignpattern.example.decorate_house;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 不同套餐装修 -》 建造者模式
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Main {


    @Test
    public void test() throws Exception {
        Builder builder = new Builder();
        System.out.println(builder.levelOne(132.560).getDetail());
        System.out.println(builder.levelTwo(112.360).getDetail());
        System.out.println(builder.levelThree(122.560).getDetail());
    }

}
