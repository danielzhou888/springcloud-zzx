package org.zzx.nk.lambda.lambda_reduce01;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Description: reduce用法案例
 * @author: 周志祥
 * @create: 2024-05-29 14:45
 */
public class Main {

    /**
     * 求和：
     */
    public void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        System.out.println(sum.orElse(0)); // 输出：15
    }

    /**
     * 求积：
     */
    public void test2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> product = numbers.stream().reduce((a, b) -> a * b);
        System.out.println(product.orElse(1)); // 输出：120
    }

    /**
     * 求最大值：
     */
    public void test3() {
        List<Integer> numbers = Arrays.asList(1, 5, 3, 9, 2);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max.orElse(Integer.MIN_VALUE)); // 输出：9
    }

    /**
     * 连接字符串：
     */
    public void test4() {
        List<String> words = Arrays.asList("Hello", "world", "Java", "is", "awesome");
        String result = words.stream().reduce((a, b) -> a + " " + b).orElse("");
        System.out.println(result); // 输出：Hello world Java is awesome
    }

    /**
     * 计算平均值：
     */
    public void test5() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        double average = numbers.stream().reduce(0.0, (a, b) -> a + b) / numbers.size();
        System.out.println(average); // 输出：3.0
    }
}
