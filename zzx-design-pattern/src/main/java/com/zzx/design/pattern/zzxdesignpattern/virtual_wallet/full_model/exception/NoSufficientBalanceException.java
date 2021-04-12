package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.exception;

/**
 * @author zhouzhixiang
 * @Date 2020-05-16
 */
public class NoSufficientBalanceException extends RuntimeException {

    private static final long serialVersionUID = -8805580520544492849L;

    public NoSufficientBalanceException() {
        super("钱包余额不足");
    }

    public NoSufficientBalanceException(String message) {
        super(message);
    }

    public NoSufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSufficientBalanceException(Throwable cause) {
        super(cause);
    }

    protected NoSufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
