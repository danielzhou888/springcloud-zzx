package com.zzx.geektime.concurrency.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     银行转账案例
 * </p>
 * <p>
 *     用"通知等待"机制优化循环等待
 * </p>
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Zhou / zzx
 * @Date 2019-06-01 15:45
 **/
public class Main3 {

    class Account {
        private Integer balance;
        // 必须单列
        private Allocator allocator;
        /**
         * 推荐</br>
         * 通过同时锁定两个资源，避免了获取单一资源等待另一资源导致死锁问题</br>
         * 同时效率也是相当高
         * @param target
         * @param amt
         */
        void transfer(Account target, int amt) {
            // 获取所需资源
            allocator.apply(this, target);
            try {
                // 锁定转出账户
                synchronized (this) {
                    // 锁定转入账户
                    synchronized (target) {
                        if (this.balance > amt) {
                            this.balance -= amt;
                            target.balance += amt;
                        }
                    }
                }
            } finally {
                allocator.free(this, target);
            }
        }
    }

    class Allocator {

        private List<Object> lockList = new ArrayList<>();

        /**
         * 一次申请所有资源
         * @param from
         * @param to
         */
        synchronized void apply(Object from, Object to) {
            while (lockList.contains(from) || lockList.contains(to)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lockList.add(from);
            lockList.add(to);
        }

        synchronized void free(Object from, Object to) {
            lockList.remove(from);
            lockList.remove(to);
            notifyAll();
        }
    }
}
