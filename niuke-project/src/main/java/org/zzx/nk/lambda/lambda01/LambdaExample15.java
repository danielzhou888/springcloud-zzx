package org.zzx.nk.lambda.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 找出列表中出现次数最多的元素
 * 问题：给定一个列表，找出其中出现次数最多的元素。
 */
public class LambdaExample15 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
        String mostFrequentElement = list.stream()
                                         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                         .entrySet()
                                         .stream()
                                         .max(Map.Entry.comparingByValue())
                                         .map(Map.Entry::getKey)
                                         .orElse(null);
        System.out.println(mostFrequentElement);
        // 输出: apple
    }
}