package org.zzx.nk.lambda.lambda_flatmap01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 案例10：使用 flatMap 将嵌套列表展开
 * 问题：使用 flatMap 方法将嵌套列表展开为单一列表。
 */
public class LambdaAdvanced10 {
    public static void main(String[] args) {
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("apple", "banana"),
            Arrays.asList("cherry", "date"),
            Arrays.asList("elderberry", "fig")
        );
        
        List<String> flatList = nestedList.stream()
                                          .flatMap(List::stream)
                                          .collect(Collectors.toList());
        
        flatList.forEach(System.out::println);
        // 输出: apple, banana, cherry, date, elderberry, fig
    }

    public void test() {
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("cherry", "date"),
                Arrays.asList("elderberry", "fig")
        );

        List<String> flatList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        flatList.forEach(System.out::println);
    }

    /**
     * 展开嵌套结构
     */
    public void test1() {
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        List<Integer> flattenedList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(flattenedList); // 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * 扁平化映射：
     */
    public void test2() {
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("cat", "dog"),
                Arrays.asList("elephant", "fox")
        );

        List<String> flattenedList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flattenedList); // 输出：[apple, banana, cat, dog, elephant, fox]
    }

    /**
     * 一对多关系转换：
     */
    public void test3() {
        List<String> words = Arrays.asList("apple", "banana", "cat");

        Map<String, List<Character>> charMap = words.stream()
                .collect(Collectors.toMap(
                        word -> word,
                        word -> word.chars().mapToObj(c -> (char) c).collect(Collectors.toList())
                ));

        System.out.println(charMap); // 输出：{apple=[a, p, p, l, e], banana=[b, a, n, a, n, a], cat=[c, a, t]}
    }

    /**
     * 过滤和转换：
     */
    public void test4() {
        List<String> words = Arrays.asList("apple", "banana", "cat");

        List<Character> filteredChars = words.stream()
                .filter(word -> word.length() > 4)
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toList());

        System.out.println(filteredChars); // 输出：[b, a, n, a, n, a]
    }

    /**
     * 数据扁平化：
     */
    public void test5() {
        int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};

        IntStream flattenedStream = Arrays.stream(matrix).flatMapToInt(Arrays::stream);

        flattenedStream.forEach(System.out::println); // 输出：1 2 3 4 5 6
    }
}