package com.zzx.design.pattern.zzxdesignpattern.abstract_factory;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-30
 */
public interface AbstractFactory {

    Cpu createCpu();

    Mainboard createMainboard();
}
