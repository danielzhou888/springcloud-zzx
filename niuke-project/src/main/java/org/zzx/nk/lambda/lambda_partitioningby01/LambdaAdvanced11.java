package org.zzx.nk.lambda.lambda_partitioningby01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.zzx.nk.nk04.PrimeCheck.isPrime;

/**
 * 案例11：使用 Collectors.partitioningBy 进行分区
 * 问题：使用 Collectors.partitioningBy 方法将数据按条件分为两部分。
 */
public class LambdaAdvanced11 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        Map<Boolean, List<Integer>> partitioned = list.stream()
                                                      .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        
        partitioned.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        // 输出:
        // false: [1, 3, 5, 7, 9]
        // true: [2, 4, 6, 8, 10]
    }

    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> partitioned = list.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        partitioned.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

    /**
     * 根据奇偶性分组：
     */
    public void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Map<Boolean, List<Integer>> evenOddMap = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Even numbers: " + evenOddMap.get(true));
        System.out.println("Odd numbers: " + evenOddMap.get(false));
    }

    /**
     * 根据字符串长度分组：
     */
    public void test2() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        Map<Boolean, List<String>> lengthMap = words.stream()
                .collect(Collectors.partitioningBy(word -> word.length() > 3));

        System.out.println("Words with length > 3: " + lengthMap.get(true));
        System.out.println("Words with length <= 3: " + lengthMap.get(false));
    }

    /**
     * 根据是否为质数分组：
     */
    public void test3() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, List<Integer>> primeMap = numbers.stream()
                .collect(Collectors.partitioningBy(n -> isPrime(n)));

        System.out.println("Prime numbers: " + primeMap.get(true));
        System.out.println("Composite numbers: " + primeMap.get(false));
    }


    /**
     * 根据是否为大写字母分组：
     */
    public void test4() {
        List<Character> characters = Arrays.asList('A', 'b', 'C', 'd', 'E', 'F');

        Map<Boolean, List<Character>> upperCaseMap = characters.stream()
                .collect(Collectors.partitioningBy(Character::isUpperCase));

        System.out.println("Uppercase characters: " + upperCaseMap.get(true));
        System.out.println("Lowercase characters: " + upperCaseMap.get(false));
    }

    /**
     * 根据是否包含特定字符分组：
     */
    public void test5() {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");

        Map<Boolean, List<String>> containsA = words.stream()
                .collect(Collectors.partitioningBy(word -> word.contains("a")));

        System.out.println("Words containing 'a': " + containsA.get(true));
        System.out.println("Words not containing 'a': " + containsA.get(false));
    }


}