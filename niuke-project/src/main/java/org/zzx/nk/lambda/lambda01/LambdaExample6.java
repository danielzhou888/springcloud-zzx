package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;

/**
 * 计算列表中字符串的总长度
 * 问题：给定一个字符串列表，计算所有字符串的总长度。
 */
public class LambdaExample6 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        int totalLength = list.stream()
                              .mapToInt(String::length)
                              .sum();
        System.out.println(totalLength);
        // 输出: 17
    }
}