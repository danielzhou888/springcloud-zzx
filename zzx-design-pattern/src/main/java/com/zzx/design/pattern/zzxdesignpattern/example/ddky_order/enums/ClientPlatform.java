/**
 * Copyright (c) 2014, 2016, www.ddky.com. All rights reserved.
 */
package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.enums;


import org.apache.commons.lang.StringUtils;

/**
 * 客户端平台
 * @author zhumingzhou
 * @date 2016年6月20日
 */
public enum ClientPlatform {

	IOS(0, "ios"),
	Android(1, "android"),
	Wechat(2, "微信商城"),
	H5(3, "h5"),
	PC(4, "pc"),
	CC(5, "客服"),
	Import(6, "导入"),
	Api(7, "第三方接口"),
	OFFLINE(8, "线下药店"),
	AREA_ERP(9, "区域ERP"),
	XCX(10, "小程序");
	
	private int code;
	private String title;
	
	private ClientPlatform(int code, String title) {
		this.code = code;
		this.title = title;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	public static ClientPlatform getPlatform(Integer code) {
		for(ClientPlatform platform : ClientPlatform.values()) {
			if(code == platform.getCode())
				return platform;
		}
		return null;
	}
	
	public static ClientPlatform getPlatform(String platform) {
		if(StringUtils.isNotEmpty(platform) ) {
			if(platform.toLowerCase().startsWith("ios")) {
				return ClientPlatform.IOS;
			}
			else if(platform.toLowerCase().startsWith("android")) {
				return ClientPlatform.Android;
			}
			else if(platform.toLowerCase().startsWith("wechat")) {
				return ClientPlatform.Wechat;
			}
			else if(platform.toLowerCase().startsWith("h5")) {
				return ClientPlatform.H5;
			}
			else if(platform.toLowerCase().startsWith("pc")) {
				return ClientPlatform.PC;
			}
			else if(platform.toLowerCase().startsWith("wxxcx")) {
				return ClientPlatform.XCX;
			}
		}
		return ClientPlatform.H5;
	}
	
	public boolean isApp() {
		return this == IOS || this == Android;
	}
}
