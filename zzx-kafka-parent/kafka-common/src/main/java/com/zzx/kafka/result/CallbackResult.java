package com.zzx.kafka.result;

import java.io.Serializable;

/**
 * 回调结果基类
 */
public class CallbackResult implements Serializable {

    private static final long serialVersionUID = 6357039523148217171L;
    private int errCode;

    public CallbackResult() {
        this.errCode = 0;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }


    @Override
    public String toString() {
        return "CallbackResult{" +
                "errCode=" + errCode +
                '}';
    }
}
