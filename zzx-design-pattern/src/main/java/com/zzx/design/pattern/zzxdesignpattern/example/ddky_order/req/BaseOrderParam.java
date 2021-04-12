package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.req;

/**
 * @author zhouzhixiang
 * @Date 2020-12-04
 */
public abstract class BaseOrderParam {

    protected Long orderId;

    protected Long userId;

    protected Integer payType;  // 支付方式：-1 在线支付，1 货到付款，其他 -》 PaymentType

    protected String invoice;

    protected String invoiceCode;

    protected String invoiceType;

    protected String deliverPay;

    protected String deliveryTime;

    protected String voucherId;

    protected Long consigneeId;

    protected Long shopId;

    protected String remark;

    protected String cartItems;

    protected Integer thirdpartyType;

    protected Integer sourceType;

    protected String receiveMobile;  // 收货人手机号码

    protected String cartSource;  // 购物车来源：1-B2C购物车，空就为原始购物车

    protected Integer multipleShopSupport;  // 是否支持多店铺，1-是


}
