package com.zzx.geektime.concurrency.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     银行转账案例一
 * </p>
 * <p>
 *     保护有关联关系的多个资源
 * </p>
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Zhou / zzx
 * @Date 2019-05-31 23:15
 **/
public class Main2 {

    public static void main(String[] args) {

    }

    class Account1 {
        private Integer balance;

        /**
         * 不推荐</br>
         * 错误：synchronized修改非静态方式，锁的是当前类实例对象this锁 </br>
         * this锁可以保护自己的余额，但是保护不了别人的余额
         * @param target
         * @param amt
         */
        synchronized void transfer(Account1 target, int amt) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }

    }
/** ====================================================================================*/

    class Account2 {
        private Integer balance;
        private Object lock;

        private Account2() {
        }

        public Account2(Object lock) {
            this.lock = lock;
        }


        /**
         * 不推荐</br>
         * 此方法确实可行，但是有弊端</br>
         * 在创建Account对象的时候，要求必须传同一个lock对象，如果不是，容易出问题；</br>
         * 另外Account对象的代码很有可能分布在多个工程中，传入共享的lock真的很难
         * @param target
         * @param amt
         */
        void transfer(Account2 target, int amt) {
            synchronized (lock) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }

    }

    class Account3 {
        private Integer balance;

        /**
         * 不推荐</br>
         * 此方法确实可行，但是有弊端</br>
         * 类锁，锁粒度太大，导致所有的转账操作都成了串行的了，性能极差
         * @param target
         * @param amt
         */
        void transfer(Account3 target, int amt) {
            synchronized (Account3.class) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }

    }

    class Account4 {
        private Integer balance;

        /**
         * 不推荐</br>
         * 极容易发生死锁</br>
         * @param target
         * @param amt
         */
        void transfer(Account4 target, int amt) {
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
        }

    }

    // =======================================================================

    class Allocator {
        private List<Object> allLockList = new ArrayList<>();

        /**
         * 一次性申请所有资源
         * @param from
         * @param to
         * @return
         */
        synchronized boolean apply(Object from, Object to) {
            if (allLockList.contains(from) || allLockList.contains(to)) {
                return false;
            } else {
                allLockList.add(from);
                allLockList.add(to);
            }
            return true;
        }

        /**
         * 归还资源
         * @param from
         * @param to
         */
        synchronized void free(Object from, Object to) {
            allLockList.remove(from);
            allLockList.remove(to);
        }
    }

    class Account5 {
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
        void transfer(Account5 target, int amt) {
            // 获取所需资源
            while (!allocator.apply(this, target)) {
            }
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
    // =======================================================================
    class Account6 {
        private Integer id;
        private Integer balance;


        /**
         * 非常推荐</br>
         * 破坏循环等待条件，需要对资源进行排序，然后按序申请资源</br>
         * 不存在循环等待了，也不存在死锁问题</br>
         * 同时效率也是相当高
         * @param target
         * @param amt
         */
        void transfer(Account6 target, int amt) {
            Account6 left = this;
            Account6 right = target;
            if (this.id > target.id) {
                left = target;
                right = this;
            }

            // 锁定最小的账户
            synchronized (left) {
                // 锁定最大的账户
                synchronized (right) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        }
    }
}
