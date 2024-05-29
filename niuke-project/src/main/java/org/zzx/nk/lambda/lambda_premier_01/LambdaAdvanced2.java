package org.zzx.nk.lambda.lambda_premier_01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 案例2：自定义 Comparator 进行排序
 * 问题：使用 Comparator 接口按自定义规则进行排序。
 */
public class LambdaAdvanced2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
        list.sort(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
        
        list.forEach(System.out::println);
        // 输出: date, apple, banana, cherry
    }

    public void test() {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
        list.sort(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
    }
}