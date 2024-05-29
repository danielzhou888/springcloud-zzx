package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;

/**
 * 获取列表中最长的字符串
 * 问题：给定一个字符串列表，找到最长的字符串。
 */
public class LambdaExample8 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        String longestString = list.stream()
                                   .max((s1, s2) -> s1.length() - s2.length())
                                   .orElse("");
        System.out.println(longestString);
        // 输出: banana
    }
}