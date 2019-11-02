package com.zzx.design.pattern.zzxdesignpattern.observer;

/**
 * 被观察者基类
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-28
 */
public interface BaseSubject {

    /**
     * 添加订阅者
     * @author        zhangqiang
     * @date          2019-10-28 23:53
     * @return
     */
    void add(BaseObserver observer);

    /**
     * 删除订阅者
     * @author        zhangqiang
     * @date          2019-10-28 23:53
     * @return
     */
    void delete(BaseObserver observer);

    /**
     * 通知订阅者更新消息
     * @author        zhangqiang
     * @date          2019-10-28 23:52
     * @return
     */
    void notify(String message);
}
