package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的所有元素转换为大写
 * 问题：给定一个字符串列表，将所有字符串转换为大写字母。
 */
public class LambdaExample1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        List<String> upperCaseList = list.stream()
                                          .map(String::toUpperCase)
                                          .collect(Collectors.toList());
        upperCaseList.forEach(System.out::println);
    }
}