package org.zzx.nk.lambda.lambda_toMap01;

import org.apache.commons.lang3.StringUtils;
import org.zzx.nk.lambda.lambda_maxBy01.Person;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 案例20：使用 Collectors.toMap 转换为映射
 * 问题：使用 Collectors.toMap 方法将列表转换为键值映射。
 */
public class LambdaAdvanced20 {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    public void test() {
        List<String> list = Arrays.asList("apple", "banana", "cherry");

        Map<String, Integer> map = list.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));

        map.forEach((k, v) -> System.out.println(k + ": " + v));
        // 输出:
        // apple: 5
        // banana: 6
        // cherry: 6


        Map<String, Integer> map1 = list.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        map1.forEach((k, v) -> System.out.println(k + ": " + v));
    }


    /**
     * 去除重复键：
     */
    public static void test1() {
        List<String> words = Arrays.asList("apple", "banana", "apple", "cat");

        Map<String, Integer> wordCountMap = words.stream()
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

        System.out.println("Word count map: " + wordCountMap); // Word count map: {banana=1, apple=2, cat=1}


        Map<String, Integer> map = words.stream()
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
        System.out.println("Word count map : " + map);

        Map<String, Integer> map1 = words.stream()
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
        System.out.println("Word count map : " + map1);
    }

    /**
     * 自定义键值：
     */
    public static void test2() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog");

        Map<String, String> wordStartsWithMap = words.stream()
                .collect(Collectors.toMap(word -> word.substring(0, 1), Function.identity()));

        System.out.println("Word starts with map: " + wordStartsWithMap); // Word starts with map: {a=apple, b=banana, c=cat, d=dog}

        Map<String, String> map1 = words.stream()
                .collect(Collectors.toMap(word -> word.substring(0, 1), Function.identity(), (o1, o2) -> o2));
        System.out.println("Word starts with map: " + map1);
    }

    /**
     * 数据索引：
     */
    public static void test3() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 20)
        );

        Map<String, Person> personIndexMap = people.stream()
                .collect(Collectors.toMap(Person::getName, Function.identity()));

        System.out.println("Person index map: " + personIndexMap); // Person index map: {Bob=org.zzx.nk.lambda.lambda_maxBy01.Person@4fca772d, Alice=org.zzx.nk.lambda.lambda_maxBy01.Person@9807454, Charlie=org.zzx.nk.lambda.lambda_maxBy01.Person@3d494fbf}

        Map<String, Person> map = people.stream()
                .collect(Collectors.toMap(Person::getName, Function.identity(), (p1, p2) -> p2));
        System.out.println("Person index map: " + map);
    }

    /**
     * 结果定制：
     */
    public static void test4() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog");

        Map<String, Integer> customizedMap = words.stream()
                .collect(Collectors.toMap(word -> word, String::length,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new));

        System.out.println("Customized map: " + customizedMap); // Customized map: {apple=5, banana=6, cat=3, dog=3}


        LinkedHashMap<String, Integer> map1 = words.stream()
                .collect(Collectors.toMap(word -> word, String::length,
                        (existing, replacement) -> existing, LinkedHashMap::new));
        System.out.println("Customized map: " + map1);
    }
}