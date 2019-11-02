package com.zzx.design.pattern.zzxdesignpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信公众号
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-28
 */
public class WechatPublic implements BaseSubject {

    private List<BaseObserver> wechatUsers = new ArrayList<>();

    @Override
    public void add(BaseObserver observer) {
        wechatUsers.add(observer);
    }

    @Override
    public void delete(BaseObserver observer) {
        wechatUsers.remove(observer);
    }

    @Override
    public void notify(String message) {
        wechatUsers.forEach(w -> {
            w.update(message);
        });
    }
}
