package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的所有字符串转换为小写
 * 问题：给定一个字符串列表，将所有字符串转换为小写字母。
 */
public class LambdaExample19 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("APPLE", "BANANA", "CHERRY");
        List<String> lowerCaseList = list.stream()
                                         .map(String::toLowerCase)
                                         .collect(Collectors.toList());
        lowerCaseList.forEach(System.out::println);
        // 输出: apple, banana, cherry
    }
}