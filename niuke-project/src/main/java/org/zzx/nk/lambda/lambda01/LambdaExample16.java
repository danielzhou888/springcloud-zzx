package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的每个字符串前后加上括号
 * 问题：给定一个字符串列表，将每个字符串前后加上括号。
 */
public class LambdaExample16 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        List<String> bracketedList = list.stream()
                                         .map(s -> "(" + s + ")")
                                         .collect(Collectors.toList());
        bracketedList.forEach(System.out::println);
        // 输出: (apple), (banana), (cherry)
    }
}