package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
public class Engineer {

    private Cpu cpu = null;
    private Mainboard mainboard = null;

    public void makeComputer(AbstractFactory factory) {
        this.cpu = factory.createCpu();
        this.mainboard = factory.createMainboard();

        this.cpu.calcute();
        this.mainboard.installCpu(cpu);
    }
}
