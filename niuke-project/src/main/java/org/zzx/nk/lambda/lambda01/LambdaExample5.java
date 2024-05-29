package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的数字乘以2
 * 问题：给定一个整数列表，将所有数字乘以2。
 */
public class LambdaExample5 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> doubledList = list.stream()
                                        .map(n -> n * 2)
                                        .collect(Collectors.toList());
        doubledList.forEach(System.out::println);
        // 输出: 2, 4, 6, 8
    }
}