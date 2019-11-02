package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-30
 */
public class IntelFactory implements AbstractFactory {
    @Override
    public Cpu createCpu() {
        System.out.println("intel create cpu");
        return new IntelCpu(288);
    }

    @Override
    public Mainboard createMainboard() {
        System.out.println("intel create mainboard");
        return new IntelMainBoard(288);
    }
}
