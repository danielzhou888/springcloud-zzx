package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;

/**
 * 计算列表中所有偶数的乘积
 * 问题：给定一个整数列表，计算所有偶数的乘积。
 */
public class LambdaExample12 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        int productOfEvens = list.stream()
                                 .filter(n -> n % 2 == 0)
                                 .reduce(1, (a, b) -> a * b);
        System.out.println(productOfEvens);
        // 输出: 48
    }
}