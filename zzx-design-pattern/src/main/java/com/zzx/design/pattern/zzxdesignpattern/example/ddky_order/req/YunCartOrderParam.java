package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.req;

/**
 * 云购物车订单提交参数
 * @author zhouzhixiang
 * @Date 2020-12-04
 */
public class YunCartOrderParam extends BaseOrderParam {

    private String idCard;

    private Integer appointIndex;  // 预约时间：小时（24h）

    private Object appointDate;

    private String appointTime;

    private String channelInfo;  // 促销渠道记录CPS

    private String mcpInfo; // 全民营销信息



}
