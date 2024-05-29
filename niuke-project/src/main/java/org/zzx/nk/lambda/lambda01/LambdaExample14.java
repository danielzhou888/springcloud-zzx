package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的数字按照升序排序
 * 问题：给定一个整数列表，将所有数字按照升序排序。
 */
public class LambdaExample14 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9);
        List<Integer> sortedList = list.stream()
                                       .sorted()
                                       .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
        // 输出: 1, 1, 3, 4, 5, 9
    }
}