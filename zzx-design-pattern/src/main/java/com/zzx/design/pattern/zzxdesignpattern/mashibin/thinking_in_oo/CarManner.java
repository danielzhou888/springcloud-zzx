package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-04
 */

@AllArgsConstructor
@Data
public class CarManner implements VehicleManner {

    @Override
    public void preGo() {
        System.out.println(" start to 检查油量");
        System.out.println(" start to 加油");
        System.out.println(" start to 系安全带");
        System.out.println(" start to 挂档");
    }

    @Override
    public void go(Address address) {
        this.preGo();
        System.out.println(" start to go " + address.getName());
    }
}
