package org.zzx.nk.lambda.lambda_groupingby01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 案例9：使用 Collectors 进行分组
 * 问题：使用 Collectors.groupingBy 方法对数据进行分组。
 */
public class LambdaAdvanced9 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry");
        
        Map<Character, List<String>> groupedByFirstLetter = list.stream()
                                                                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        
        groupedByFirstLetter.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        // 输出:
        // a: [apple, apricot]
        // b: [banana, blueberry]
        // c: [cherry]
    }

    public void test() {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry");
        Map<Character, List<String>> map = list.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));

        map.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }
}