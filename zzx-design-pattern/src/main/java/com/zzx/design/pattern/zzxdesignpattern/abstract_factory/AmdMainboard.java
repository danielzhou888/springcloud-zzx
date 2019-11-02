package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

import lombok.AllArgsConstructor;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
@AllArgsConstructor
public class AmdMainboard implements Mainboard {

    private int pins;

    @Override
    public void installCpu(Cpu cpu) {
        if (cpu != null && pins == cpu.getPins()) {
            System.out.println("adm board pins is ok with cpu pins");
        } else {
            System.out.println("amd board is not ok with cpu");
        }
    }
}
