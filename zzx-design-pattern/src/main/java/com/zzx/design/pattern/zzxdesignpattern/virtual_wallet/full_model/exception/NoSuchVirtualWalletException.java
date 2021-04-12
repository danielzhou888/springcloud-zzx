package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.exception;

/**
 * @author zhouzhixiang
 * @Date 2020-05-16
 */
public class NoSuchVirtualWalletException extends RuntimeException {

    private static final long serialVersionUID = -1848398218147272820L;

    public NoSuchVirtualWalletException() {
        super("不存在此虚拟钱包");
    }

    public NoSuchVirtualWalletException(String message) {
        super(message);
    }

    public NoSuchVirtualWalletException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchVirtualWalletException(Throwable cause) {
        super(cause);
    }

    protected NoSuchVirtualWalletException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
