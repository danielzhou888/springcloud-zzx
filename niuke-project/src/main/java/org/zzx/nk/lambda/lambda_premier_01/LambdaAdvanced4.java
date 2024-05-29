package org.zzx.nk.lambda.lambda_premier_01;

import java.util.function.BiFunction;

/**
 * 案例4：使用 BiFunction 进行双参数操作
 * 问题：使用 BiFunction 接口进行双参数操作。
 */
public class LambdaAdvanced4 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        int result = multiply.apply(3, 5);
        System.out.println(result); // 输出: 15
    }

    public void test() {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Integer result = multiply.apply(3, 5);
    }

}