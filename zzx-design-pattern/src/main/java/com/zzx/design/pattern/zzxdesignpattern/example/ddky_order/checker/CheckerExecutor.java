package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.checker;

import com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.enums.ResponseEnum;
import com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.resp.ServiceResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查器执行器
 * @author zhouzhixiang
 * @Date 2020-12-04
 */
@Slf4j
public class CheckerExecutor implements IChecker {

    private List<IChecker> checkerList = new ArrayList<>();

    public CheckerExecutor addChecker(IChecker checker) {
        checkerList.add(checker);
        return this;
    }

    @Override
    public ServiceResponse doCheck() throws Exception {
        ServiceResponse res = new ServiceResponse();
        for (int i = 0; i < checkerList.size(); i++) {
            res = checkerList.get(i).doCheck();
            if (res != null && res.getCode() != ResponseEnum.SUCCESS.getCode()) {
                break;
            }
        }
        return res;
    }
}
