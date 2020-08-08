package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.exception;

/**
 * 自定义异常
 * @author zhouzhixiang
 * @Date 2020-05-16
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -8805580520544492849L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
