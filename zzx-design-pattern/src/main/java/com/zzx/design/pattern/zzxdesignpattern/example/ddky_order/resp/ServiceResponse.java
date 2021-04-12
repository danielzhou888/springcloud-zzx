package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.resp;

import java.io.Serializable;

/***
 * 响应类
 * @author zhouzhixiang
 */
public class ServiceResponse<T> implements Serializable {

	private static final long serialVersionUID = 7775562059696652911L;
	private boolean success = true;
	private T data;
	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		success = code == 0 ? true : false;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public ServiceResponse() {
		this.code = 0;
		this.msg = "success";
	}

	public ServiceResponse(T data) {
		super();
		this.data = data;
	}

	public ServiceResponse(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public ServiceResponse(int code, T data) {
		this.code = code;
		this.data = data;
		if (code != 0) {
			success = false;
		}
	}

	public void setError() {
		this.code = 1;
		this.msg = "fail";
	}
	

}
