package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;

/**
 * 计算列表中所有元素的平方和
 * 问题：给定一个整数列表，计算所有元素的平方和
 */
public class LambdaExample3 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int sumOfSquares = list.stream()
                               .map(n -> n * n)
                               .reduce(0, Integer::sum);
        System.out.println(sumOfSquares);
        // 输出: 30
    }
}