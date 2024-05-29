package org.zzx.nk.lambda.lambda_premier_01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 案例1：使用 Predicate 进行条件过滤
 * 问题：使用 Predicate 接口进行复杂条件过滤。
 */
public class LambdaAdvanced1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        Predicate<String> startWithA = s -> s.startsWith("a");
        Predicate<String> lengthGreaterThanFive = s -> s.length() > 5;
        
        List<String> filteredList = list.stream()
                                        .filter(startWithA.or(lengthGreaterThanFive))
                                        .collect(Collectors.toList());
        filteredList.forEach(System.out::println);
        // 输出: apple, banana, cherry, elderberry
    }

    public void test() {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        Predicate<String> startWithA = s -> s.startsWith("a");
        Predicate<String> lengthGreaterThanFive = s -> s.length() > 5;
        List<String> filteredList = list.stream()
                .filter(startWithA.and(lengthGreaterThanFive))
                .collect(Collectors.toList());
        filteredList.forEach(System.out::println);
    }
}