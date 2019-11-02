package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-30
 */
public class AmdFactory implements AbstractFactory {
    @Override
    public Cpu createCpu() {

        System.out.println("amd create cpu");
        return new AmdCpu(129);
    }

    @Override
    public Mainboard createMainboard() {
        System.out.println("amd create mainboard");
        return new IntelMainBoard(129);
    }
}
