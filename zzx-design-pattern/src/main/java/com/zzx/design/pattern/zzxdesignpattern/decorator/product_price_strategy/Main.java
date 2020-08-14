package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy;

import com.sun.tools.corba.se.idl.constExpr.Or;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.factory.PromotionFactory;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * 装饰器模式：优化电商系统中的商品价格策略
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
@Slf4j
public class Main {


    public static void main(String[] args) {

        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(1);
        userCoupon.setSku("3333");
        userCoupon.setUserId(1);
        userCoupon.setCoupon(new BigDecimal(10));

        UserRedPacket userRedPacket = new UserRedPacket();
        userRedPacket.setId(4);
        userRedPacket.setSku("3333");
        userRedPacket.setUserId(1);
        userRedPacket.setRedPacket(new BigDecimal(20));



        Map<PromotionType, SupportPromotions> supportPromotionsMap = new HashMap<>();

        SupportPromotions couponPromotion = new SupportPromotions();
        couponPromotion.setId(1);
        couponPromotion.setPriority(1);
        couponPromotion.setPromotionType(PromotionType.COUPON);
        couponPromotion.setUserCoupon(userCoupon);

        SupportPromotions redPacketPromotion = new SupportPromotions();
        redPacketPromotion.setId(2);
        redPacketPromotion.setUserRedPacket(userRedPacket);
        redPacketPromotion.setPriority(2);
        redPacketPromotion.setPromotionType(PromotionType.REDPACKED);

        supportPromotionsMap.put(PromotionType.REDPACKED, redPacketPromotion);
        supportPromotionsMap.put(PromotionType.COUPON, couponPromotion);

        Product product = new Product();
        product.setSku("3333");
        product.setPrice(new BigDecimal(99));
        product.setName("飞利浦剃须刀");
        product.setSupportPromotions(supportPromotionsMap);

        Order order = new Order();
        order.setId(1111);
        order.setOrderNo("1111");

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(222);
        orderDetail.setProduct(product);
        orderDetail.setOrderId(1111);

        List<OrderDetail> list = new ArrayList<>();
        list.add(orderDetail);

        order.setList(list);

        BigDecimal totalPay = new BigDecimal(0);
        for (OrderDetail detail : order.getList()) {
            BigDecimal payMoney = PromotionFactory.getPayMoney(orderDetail);
            detail.setPayMoney(payMoney);
            totalPay = totalPay.add(payMoney);
        }
        log.info("最终支付金额 = {}", totalPay.intValue());
    }

    public void test() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        Collections.addAll(list1, list2);
        Collections.sort(list1);

    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        List<Integer> aList = new ArrayList();
        List<Integer> bList = new ArrayList();
        while (list1 != null) {
            aList.add(list1.val);
            list1 = list1.next;
        }
        while (list2 != null) {
            bList.add(list2.val);
            list2 = list2.next;
        }
        aList.addAll(bList);
        Collections.sort(aList);
        ListNode head = new ListNode(0);
        for (int i = 0; i < aList.size(); i++) {
            if (i == 0) {
                head.val = (int) aList.get(i);
            } else {
                ListNode temp = new ListNode(aList.get(i));
                head.next = temp;
                head = head.next;
            }
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}

