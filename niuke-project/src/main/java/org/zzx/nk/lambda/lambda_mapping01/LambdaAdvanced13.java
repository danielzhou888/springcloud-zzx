package org.zzx.nk.lambda.lambda_mapping01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 案例13：使用 Collectors.mapping 进行嵌套收集
 * 问题：使用 Collectors.mapping 方法在分组时进行嵌套收集。
 */
public class LambdaAdvanced13 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry");
        
        Map<Character, List<Integer>> groupedAndMapped = list.stream()
                                                             .collect(Collectors.groupingBy(
                                                                 s -> s.charAt(0),
                                                                 Collectors.mapping(String::length, Collectors.toList())
                                                             ));
        
        groupedAndMapped.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        // 输出:
        // a: [5, 7]
        // b: [6, 9]
        // c: [6]
    }

    public void test() {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry");
        Map<Character, List<Integer>> map = list.stream()
                .collect(Collectors.groupingBy(
                        word -> word.charAt(0),
                        Collectors.mapping(String::length, Collectors.toList())
                ));
        map.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

    /**
     * 简单映射转换：
     */
    public void test1() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        Set<Integer> lengths = words.stream()
                .collect(Collectors.mapping(String::length, Collectors.toSet()));

        System.out.println("Word lengths: " + lengths); // 输出：Word lengths: [3, 5, 6, 7]

        List<Integer> lengthList = words.stream()
                .collect(Collectors.mapping(String::length, Collectors.toList()));
        System.out.println("Word lengths: " + lengthList);
    }

    /**
     * 多层嵌套收集：
     */
    public void test2() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        Map<Character, Set<String>> groupedByFirstChar = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0),
                        Collectors.mapping(Function.identity(), Collectors.toSet())));

        System.out.println("Words grouped by first character: " + groupedByFirstChar);


        Map<Character, List<String>> map = words.stream()
                .collect(Collectors.groupingBy(
                        word -> word.charAt(0),
                        Collectors.mapping(Function.identity(), Collectors.toList())
                ));
        System.out.println("Words grouped by first character: " + map);
    }

    /**
     * 复杂数据处理：
     */
    public void test3() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(String::length,
                        Collectors.mapping(s -> s.toUpperCase(), Collectors.toList())));

        System.out.println("Words grouped by length and converted to uppercase: " + groupedByLength);
    }

    /**
     * 数据过滤与转换：
     */
    public void test4() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        List<Integer> lengthsStartingWithA = words.stream()
                .filter(word -> word.startsWith("a"))
                .collect(Collectors.mapping(String::length, Collectors.toList()));

        System.out.println("Lengths of words starting with 'a': " + lengthsStartingWithA);
    }

    /**
     * 定制化数据收集：
     */
    public void test5() {
//        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");
//
//        Map<Character, String> firstWordStartingWithMap = words.stream()
//                .collect(Collectors.mapping(Function.identity(),
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0))));
//
//        System.out.println("First word starting with each character: " + firstWordStartingWithMap);
    }

}