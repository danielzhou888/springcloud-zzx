package org.zzx.nk.lambda.lambda_joinging01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 案例12：使用 Collectors.joining 拼接字符串
 * 问题：使用 Collectors.joining 方法将字符串列表拼接为单一字符串。
 */
public class LambdaAdvanced12 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        
        String result = list.stream()
                            .collect(Collectors.joining(", "));
        
        System.out.println(result); // 输出: apple, banana, cherry
    }
}