package com.zzx.sentinel.distribute.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: 20160219
 * @description: 分页响应
 * @author: zhouzhixiang
 * @date: 2019-12-10
 * @company: 叮当快药科技集团有限公司
 **/
@Data
public class ServiceResponse<T> implements Serializable {

    private static final long serialVersionUID = 6219256383112192228L;

    private int code;
    private String msg;
    private T data;
    private List<T> dataList;
    boolean isSucces = true;
    boolean isDownGrade = false;
    boolean isLocalDownGrade = false;

    public ServiceResponse (int code, String msg) {
        if (code != 0) {
            isSucces = false;
        }
        this.code = code;
        this.msg = msg;
    }

    public ServiceResponse (int code, String msg, boolean isDownGrade) {
        if (code != 0) {
            isSucces = false;
        }
        this.code = code;
        this.msg = msg;
        this.isDownGrade = isDownGrade;
    }

    public ServiceResponse () {
        this.code = 0;
        this.msg = "success";
    }

    public void setCode(int code) {
        if (code != 0) {
            isSucces = false;
        }
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public ServiceResponse downGrade(T t) {
        this.data = t;
        this.code = -2;
        this.msg = "降级了";
        this.isSucces = false;
        this.isDownGrade = true;
        return this;
    }

    public ServiceResponse success(T t) {
        this.data = t;
        this.code = 0;
        this.msg = "success";
        this.isSucces = true;
        return this;
    }

    public ServiceResponse fail() {
        this.code = 1;
        this.msg = "fail";
        this.isSucces = false;
        return this;
    }

    public static ServiceResponse fail(String msg) {
        ServiceResponse result = new ServiceResponse();
        result.setCode(1);
        result.setMsg(msg);
        result.setSucces(false);
        return result;
    }

    public static ServiceResponse fail(int code, String msg) {
        ServiceResponse result = new ServiceResponse();
        result.setCode(1);
        result.setMsg(msg);
        result.setSucces(false);
        return result;
    }
}
