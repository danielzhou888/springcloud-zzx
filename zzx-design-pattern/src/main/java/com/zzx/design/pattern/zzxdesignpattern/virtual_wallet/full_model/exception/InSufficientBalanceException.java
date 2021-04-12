package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.exception;

/**
 * @author zhouzhixiang
 * @Date 2020-05-16
 */
public class InSufficientBalanceException extends RuntimeException {

    private static final long serialVersionUID = -8805580520544492849L;

    public InSufficientBalanceException() {
        super("账户余额不足");
    }

    public InSufficientBalanceException(String message) {
        super(message);
    }

    public InSufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InSufficientBalanceException(Throwable cause) {
        super(cause);
    }

    protected InSufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
