package com.zzx.geektime.concurrency.chapter1;

/**
 * <p>
 *     银行账户取款、修改密码案例一
 * </p>
 * <p>
 *     细粒度锁：通过使用两把不同的锁，可使取款和修改密码并行，提升性能
 * </p>
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Zhou / zzx
 * @Date 2019-05-31 22:54
 **/
public class Main1 {

    public static void main(String[] args) {

    }

    class Account {
        private Integer balance;
        private String password;
        private final Object balLock =  new Object();
        private final Object passLock = new Object();

        void withdraw(Integer amt) {
            synchronized (balLock) {
                if (this.balance > amt) {
                    this.balance -= amt;
                }
            }
        }

        Integer getBalance() {
            synchronized (balLock) {
                return balance;
            }
        }

        void updatePassword(String pw) {
            synchronized (passLock) {
                this.password = pw;
            }
        }

        String getPassword() {
            synchronized (passLock) {
                return password;
            }
        }
    }
}
