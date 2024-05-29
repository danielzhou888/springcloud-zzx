package org.zzx.nk.lambda.lambda_bifunction01;

import java.util.function.BiFunction;

/**
 * 案例1：计算折扣价格
 * 问题：使用 BiFunction 计算商品的折扣价格。
 * 风格：通过传入原价和折扣百分比计算最终价格。
 */
public class DiscountCalculator {
    public static void main(String[] args) {
        BiFunction<Double, Double, Double> calculateDiscount = (price, discount) -> price * (1 - discount / 100);

        double price = 100.0;
        double discount = 15.0;
        double finalPrice = calculateDiscount.apply(price, discount);

        System.out.println("Original Price: $" + price);
        System.out.println("Discount: " + discount + "%");
        System.out.println("Final Price: $" + finalPrice);
    }
}