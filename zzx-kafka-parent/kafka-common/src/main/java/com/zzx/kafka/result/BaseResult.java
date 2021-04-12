package com.zzx.kafka.result;

import java.io.Serializable;

/**
 * 结果基类
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 3040590282668501422L;
    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isSuccess() {
        return code == 200 ? true : false;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }

}
