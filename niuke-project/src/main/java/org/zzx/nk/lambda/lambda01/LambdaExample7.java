package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将字符串列表中每个字符串的首字母大写
 * 问题：给定一个字符串列表，将每个字符串的首字母大写。
 */
public class LambdaExample7 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        List<String> capitalizedList = list.stream()
                                           .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                                           .collect(Collectors.toList());
        capitalizedList.forEach(System.out::println);
        // 输出: Apple, Banana, Cherry
    }
}