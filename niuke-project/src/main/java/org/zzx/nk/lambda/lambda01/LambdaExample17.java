package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将字符串列表按字母顺序排序
 * 问题：给定一个字符串列表，将所有字符串按字母顺序排序。
 */
public class LambdaExample17 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> sortedList = list.stream()
                                      .sorted()
                                      .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
        // 输出: apple, banana, cherry, date
    }
}