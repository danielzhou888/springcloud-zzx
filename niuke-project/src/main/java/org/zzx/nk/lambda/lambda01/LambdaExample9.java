package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统计列表中每个字符的出现次数
 * 问题：给定一个字符串列表，统计每个字符的出现次数。
 */
public class LambdaExample9 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Map<Character, Long> charCount = list.stream()
                                             .flatMapToInt(String::chars)
                                             .mapToObj(c -> (char) c)
                                             .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        charCount.forEach((k, v) -> System.out.println(k + ": " + v));
        // 输出: 各字符及其出现次数
    }
}