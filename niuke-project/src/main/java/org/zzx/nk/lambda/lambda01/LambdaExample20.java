package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的数字按降序排序
 * 问题：给定一个整数列表，将所有数字按降序排序。
 */
public class LambdaExample20 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9);
        List<Integer> sortedList = list.stream()
                                       .sorted((a, b) -> b - a)
                                       .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
        // 输出: 9, 5, 4, 3, 1, 1
    }
}