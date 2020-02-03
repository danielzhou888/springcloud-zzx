package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-04
 */
public interface VehicleManner {

    /**
     * 开始启动交通工具前，需要做的准备工作
     * @author        zhangqiang
     * @date          2019-11-04 00:56
     * @return
     */
    void preGo();

    void go(Address address);
}
