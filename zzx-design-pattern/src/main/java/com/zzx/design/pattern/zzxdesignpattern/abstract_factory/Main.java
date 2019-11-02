package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

import org.junit.Test;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-30
 */
public class Main {

    @Test
    public void test() {

        AbstractFactory factory = new IntelFactory();
        Engineer engineer = new Engineer();
        engineer.makeComputer(factory);
    }
}
