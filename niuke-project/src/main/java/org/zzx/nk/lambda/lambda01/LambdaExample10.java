package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 删除列表中长度小于4的字符串
 * 问题：给定一个字符串列表，删除所有长度小于4的字符串。
 */
public class LambdaExample10 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "fig");
        List<String> filteredList = list.stream()
                                        .filter(s -> s.length() >= 4)
                                        .collect(Collectors.toList());
        filteredList.forEach(System.out::println);
        // 输出: apple, banana, cherry
    }
}