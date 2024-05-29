package org.zzx.nk.lambda.lambda_bifunction01;

import java.util.function.BiFunction;

/**
 * 案例3：计算订单总额
 * 问题：使用 BiFunction 计算订单的总额。
 * 风格：通过传入订单数量和单价计算总金额。
 */
public class OrderTotalCalculator {
    public static void main(String[] args) {
        BiFunction<Integer, Double, Double> calculateTotal = (quantity, pricePerUnit) -> quantity * pricePerUnit;

        int quantity = 5;
        double pricePerUnit = 19.99;
        double totalAmount = calculateTotal.apply(quantity, pricePerUnit);

        System.out.println("Quantity: " + quantity);
        System.out.println("Price per unit: $" + pricePerUnit);
        System.out.println("Total Amount: $" + totalAmount);
    }

    public static void calculateTest() {
        int quantity = 10;
        double pricePerUnit = 19.22;
        double totalPrice = calculate((d1, d2) -> d1 * d2, quantity, pricePerUnit);
    }

    private static double calculate(BiFunction<Integer, Double, Double> biFunction, int quantity, double pricePerUnit) {
        return biFunction.apply(quantity, pricePerUnit);
    }
}