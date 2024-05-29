package org.zzx.nk.lambda.lambda_summingInt01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 案例16：使用 Collectors.summingInt 计算总和
 * 问题：使用 Collectors.summingInt 方法计算整数列表的总和。
 */
public class LambdaAdvanced16 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        
        int sum = list.stream()
                      .collect(Collectors.summingInt(Integer::intValue));
        
        System.out.println(sum); // 输出: 15
    }
}