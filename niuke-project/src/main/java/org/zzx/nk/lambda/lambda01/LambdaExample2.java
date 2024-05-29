package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤出列表中的偶数
 * 问题：给定一个整数列表，过滤出所有的偶数。
 */
public class LambdaExample2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenList = list.stream()
                                      .filter(n -> n % 2 == 0)
                                      .collect(Collectors.toList());
        evenList.forEach(System.out::println);
    }
}