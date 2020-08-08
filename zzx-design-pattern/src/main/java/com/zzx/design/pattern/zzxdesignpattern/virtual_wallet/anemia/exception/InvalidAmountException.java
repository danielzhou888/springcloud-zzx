package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.exception;

/**
 * @author zhouzhixiang
 * @Date 2020-05-16
 */
public class InvalidAmountException extends RuntimeException {

    private static final long serialVersionUID = -8805580520544492849L;

    public InvalidAmountException() {
        super("非法账户");
    }

    public InvalidAmountException(String message) {
        super(message);
    }

    public InvalidAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAmountException(Throwable cause) {
        super(cause);
    }

    protected InvalidAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
