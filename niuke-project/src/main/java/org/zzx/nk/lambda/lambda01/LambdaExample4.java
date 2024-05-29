package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的字符串按长度排序
 * 问题：给定一个字符串列表，将所有字符串按长度排序。
 */
public class LambdaExample4 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> sortedList = list.stream()
                                      .sorted((s1, s2) -> s1.length() - s2.length())
                                      .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
        // 输出: date, apple, cherry, banana
    }
}