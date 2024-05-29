package org.zzx.nk.lambda.lambda_minBy01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 案例19：使用 Collectors.minBy 找到最小值
 * 问题：使用 Collectors.minBy 方法找到列表中的最小值。
 */
public class LambdaAdvanced19 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        
        Optional<Integer> min = list.stream()
                                    .collect(Collectors.minBy(Comparator.naturalOrder()));
        
        min.ifPresent(System.out::println); // 输出: 1

        Optional<Integer> min1 = list.stream()
                .collect(Collectors.minBy(Comparator.naturalOrder()));
        min1.ifPresent(System.out::println);

        Optional<Integer> min2 = list.stream()
                .min(Comparator.naturalOrder());
        min2.ifPresent(System.out::println);
    }
}