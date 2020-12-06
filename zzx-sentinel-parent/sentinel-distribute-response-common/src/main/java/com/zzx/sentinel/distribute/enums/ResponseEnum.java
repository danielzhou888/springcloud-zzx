package com.zzx.sentinel.distribute.enums;

/**
 * <p>响应状态码</p>
 *
 * @author: zhouzhixiang
 * @date: 2020-01-20
 **/
public enum ResponseEnum {

    SUCCESS(0, "success"),
    FAIL(1, "fail"),

    /** 优惠券 */
    CART_CHECK_EXCEPTION(10001, "购物车商品校验异常"),
    EXECUTE_PROMOTION_EXCEPTION(10002, "执行促销异常"),
    /** 优惠券 */

    /** 订单 */
    SUBMIT_ORDER_EXCEPTION(20001, "提交订单异常"),
    CURRENT_VISITOR_LIMIT(20002, "当前访问人数过多，请稍后重试"),
    ORDER_PROCESSING(20003, "订单处理中，请稍后"),
    /** 订单 */

    UNAUTHORIZED(400001, "授权限制了!"),
    ;

    private int code;
    private String name;

    ResponseEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
