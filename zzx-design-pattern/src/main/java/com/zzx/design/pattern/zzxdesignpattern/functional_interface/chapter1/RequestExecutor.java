package com.zzx.design.pattern.zzxdesignpattern.functional_interface.chapter1;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-31
 */
@FunctionalInterface
public interface RequestExecutor {
    Object doExecutor() throws BusinessExcetpion;
}
