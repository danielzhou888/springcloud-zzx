package org.zzx.nk.lambda.lambda_function01;

import java.util.function.Function;

/**
 * 案例2：商品价格转换
 * 问题：使用 Function 将商品价格从美元转换为欧元。
 * 风格：通过汇率转换价格。
 */
public class PriceConverter {
    public static void main(String[] args) {
        Function<Double, Double> usdToEur = usd -> usd * 0.85;

        double priceInUsd = 100.0;
        double priceInEur = usdToEur.apply(priceInUsd);

        System.out.println("Price in USD: $" + priceInUsd);
        System.out.println("Price in EUR: €" + priceInEur);
    }

    public void test() {
        double priceInUsd = 100.0;
        double priceInEur = convertMoney((money) -> money * 0.85, priceInUsd);
        System.out.println("....");
    }

    private double convertMoney(Function<Double, Double> function, double priceInUsd) {
        return function.apply(priceInUsd);
    }
}