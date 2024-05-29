package org.zzx.nk.lambda.lambda_maxBy01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 案例18：使用 Collectors.maxBy 找到最大值
 * 问题：使用 Collectors.maxBy 方法找到列表中的最大值。
 */
public class LambdaAdvanced18 {
    public static void main(String[] args) {
        test2();
    }

    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> max = list.stream()
                .collect(Collectors.maxBy(Comparator.naturalOrder()));

        max.ifPresent(System.out::println); // 输出: 5

        Optional<Integer> max1 = list.stream()
                .max(Comparator.naturalOrder());
        max1.ifPresent(System.out::println);
    }

    /**
     * 根据指定条件查找
     */
    public void test1() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        Optional<String> longestWord = words.stream()
                .collect(Collectors.maxBy(Comparator.comparing(String::length)));

        System.out.println("Longest word: " + longestWord.orElse("")); // 输出：Longest word: elephant


        Optional<String> longestWord1 = words.stream()
                .collect(Collectors.maxBy(Comparator.comparing(String::length)));
        System.out.println("Longest word: " + longestWord1.orElse(""));


        Optional<String> longestWord2 = words.stream()
                .collect(Collectors.maxBy(Comparator.comparing(String::length)));
        System.out.println("Longest word: " + longestWord2.orElse(""));
    }

    /**
     * 多条件查找：
     */
    public static void test2() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        Optional<String> longestWord = words.stream()
                .collect(Collectors.maxBy(Comparator.comparing(String::length)
                        .thenComparing(Comparator.reverseOrder())));

        System.out.println("Longest word: " + longestWord.orElse("")); // 输出：Longest word: elephant

        Optional<String> longestWord1 = words.stream()
                .collect(Collectors.maxBy(Comparator.comparing(String::length)
                        .thenComparing(Comparator.reverseOrder())));
        System.out.println("Longest word: " + longestWord1.orElse(""));
    }

    /**
     * 对象属性比较：
     */
    public void test3() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 20)
        );

        Optional<Person> oldestPerson = people.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));

        System.out.println("Oldest person: " + oldestPerson.map(Person::getName).orElse("")); // 输出：Oldest person: Bob


        Optional<Person> oldestPerson1 = people.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));
        System.out.println("Oldest person: " + oldestPerson1.map(Person::getName).orElse(""));

        Optional<Person> oldestPerson2 = people.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));
        System.out.println("Oldest person: " + oldestPerson2.map(Person::getName).orElse(""));
    }

    /**
     * 结果定制：
     */
    public void test4() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

//        String maxWordOrEmpty = words.stream()
//                .collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.naturalOrder()),
//                        opt -> opt.map(s -> "Max word: " + s).orElse("No words found")));
//
//        System.out.println(maxWordOrEmpty); // 输出：Max word: elephant
//
//
//        String finalWord = words.stream()
//                .collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.naturalOrder()),
//                        opt -> opt.map(s -> "Max word: " + s).orElse("No words found")));
//        System.out.println(finalWord);
//
//        words.stream()
//                .collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.naturalOrder()),
//                        opt -> opt.map(s -> "Max word: " + s).orElse("No words found")));
    }
}