package com.zzx.design.pattern.zzxdesignpattern.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-28
 */
@Data
@AllArgsConstructor
public class WeChatUser implements BaseObserver {

    private String name;

    @Override
    public void update(String message) {
        System.out.println(name + "收到订阅消息:" + message);
    }
}
