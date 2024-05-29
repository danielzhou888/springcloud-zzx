package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 统计列表中每个字符串的长度
 * 问题：给定一个字符串列表，统计每个字符串的长度。
 */
public class LambdaExample18 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Map<String, Integer> lengthMap = list.stream()
                                             .collect(Collectors.toMap(Function.identity(), String::length));
        lengthMap.forEach((k, v) -> System.out.println(k + ": " + v));
        // 输出: apple: 5, banana: 6, cherry: 6
    }
}