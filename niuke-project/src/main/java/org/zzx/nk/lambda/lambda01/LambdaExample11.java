package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将字符串列表中每个字符串反转
 * 问题：给定一个字符串列表，将每个字符串反转。
 */
public class LambdaExample11 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        List<String> reversedList = list.stream()
                                        .map(s -> new StringBuilder(s).reverse().toString())
                                        .collect(Collectors.toList());
        reversedList.forEach(System.out::println);
        // 输出: elppa, ananab, yrrehc
    }
}