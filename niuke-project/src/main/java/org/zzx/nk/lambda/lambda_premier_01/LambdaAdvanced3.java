package org.zzx.nk.lambda.lambda_premier_01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * 案例3：结合 Function 和 UnaryOperator 进行数据转换
 * 问题：使用 Function 和 UnaryOperator 接口进行数据转换
 */
public class LambdaAdvanced3 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Function<String, String> addBrackets = s -> "[" + s + "]";
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        
        List<String> transformedList = list.stream()
                                           .map(addBrackets.andThen(toUpperCase))
                                           .collect(Collectors.toList());
        transformedList.forEach(System.out::println);
        // 输出: [APPLE], [BANANA], [CHERRY]
    }
}