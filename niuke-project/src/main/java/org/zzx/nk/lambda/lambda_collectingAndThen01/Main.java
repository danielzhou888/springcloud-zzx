package org.zzx.nk.lambda.lambda_collectingAndThen01;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-05-29 15:44
 */
public class Main {

    public static void main(String[] args) {
        test2();
    }

    /**
     * 结果转换
     */
    public void test1() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        String concatenatedWords = words.stream()
                .collect(Collectors.collectingAndThen(Collectors.joining(", "), str -> "[" + str + "]"));

        System.out.println("Concatenated words: " + concatenatedWords); // 输出：Concatenated words: [apple, banana, cat, dog, elephant]
    }

    /**
     * 数据处理：
     */
    public static void test2() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        int totalLength = words.stream()
                .collect(Collectors.collectingAndThen(Collectors.summingInt(String::length), len -> len * 2));

        System.out.println("Total length doubled: " + totalLength); // 输出：Total length doubled: 50
    }

    /**
     * 结果定制
     */
    public void test3() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        String result = numbers.stream()
                .collect(Collectors.collectingAndThen(Collectors.summarizingInt(n -> n), summary -> "Summary: " + summary));

        System.out.println(result); // 输出：Summary: IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}
    }

    /**
     * 数据校验
     */
    public void test4() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        List<String> filteredWords = words.stream()
                .filter(word -> word.length() > 3)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.isEmpty() ? Collections.singletonList("No words found") : list));

        System.out.println(filteredWords); // 输出：[apple, banana, elephant]
    }

    public void test5() {
//        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");
//
//        Optional<String> result = words.stream()
//                .collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.naturalOrder()),
//                        opt -> opt.map(s -> "Max word: " + s).orElse("No words found")));
//
//        System.out.println(result); // 输出：Optional[Max word: elephant]
    }
}
