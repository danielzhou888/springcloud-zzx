package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

import lombok.AllArgsConstructor;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
@AllArgsConstructor
public class IntelMainBoard implements Mainboard {

    private int pins;

    @Override
    public void installCpu(Cpu cpu) {
        if (cpu != null && pins == cpu.getPins()) {
            System.out.println("intel mainboard is ok with cpu");
        } else {
            System.out.println("intel mainboard is not ok with cpu");
        }
    }
}
