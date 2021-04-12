package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.checker;

import com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.resp.ServiceResponse;

/**
 * 检查器接口
 * @author zhouzhixiang
 * @Date 2020-12-04
 */
public interface IChecker {

    ServiceResponse doCheck() throws Exception;
}
