package org.zzx.nk.lambda.lambda_averagingInt01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 案例17：使用 Collectors.averagingInt 计算平均值
 * 问题：使用 Collectors.averagingInt 方法计算整数列表的平均值。
 */
public class LambdaAdvanced17 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        
        double average = list.stream()
                             .collect(Collectors.averagingInt(Integer::intValue));
        
        System.out.println(average); // 输出: 3.0
    }
}