package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将列表中的所有字符串连接成一个字符串
 * 问题：给定一个字符串列表，将所有字符串连接成一个字符串。
 */
public class LambdaExample13 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        String concatenatedString = list.stream()
                                        .collect(Collectors.joining(", "));
        System.out.println(concatenatedString);
        // 输出: apple, banana, cherry
    }
}