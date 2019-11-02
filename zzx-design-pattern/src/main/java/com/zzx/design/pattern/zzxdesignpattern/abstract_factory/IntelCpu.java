package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

import lombok.AllArgsConstructor;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */

@AllArgsConstructor
public class IntelCpu implements Cpu {

    private int pins = 0;

    @Override
    public int getPins() {
        return pins;
    }

    @Override
    public void calcute() {
        System.out.println("intel cpu pins is " + pins);
    }
}
