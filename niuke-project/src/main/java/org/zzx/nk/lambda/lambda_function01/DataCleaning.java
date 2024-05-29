package org.zzx.nk.lambda.lambda_function01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 案例5：数据清理
 * 问题：使用 Function 对用户输入数据进行清理。
 * 风格：去除字符串中的前后空格并将其转换为小写。
 */
public class DataCleaning {
    public static void main(String[] args) {
        Function<String, String> cleanData = data -> data.trim().toLowerCase();

        List<String> rawData = Arrays.asList("  Alice  ", "  BoB  ", " CHARLIE ");
        List<String> cleanedData = rawData.stream()
                                          .map(cleanData)
                                          .collect(Collectors.toList());

        cleanedData.forEach(System.out::println);
        // 输出: alice, bob, charlie
    }

    public void test() {
        List<String> rawData = Arrays.asList("  Alice  ", "  BoB  ", " CHARLIE ");
        Function<String, String> cleanData = data -> data.trim().toLowerCase();
        List<String> collect = rawData.stream()
                .map(cleanData)
                .collect(Collectors.toList());
    }
}