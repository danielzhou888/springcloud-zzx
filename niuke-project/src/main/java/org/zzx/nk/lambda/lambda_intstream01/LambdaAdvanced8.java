package org.zzx.nk.lambda.lambda_intstream01;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 案例8：使用 IntStream 进行数值流操作
 * 问题：使用 IntStream 进行数值流操作，生成范围内的所有数，并计算其平方和。
 */
public class LambdaAdvanced8 {
    public static void main(String[] args) {
        int sumOfSquares = IntStream.range(1, 6)
                                    .map(n -> n * n)
                                    .sum();
        System.out.println(sumOfSquares); // 输出: 55
    }

    public void test() {
        int sum = IntStream.range(1, 10)
                .map(n -> n * n)
                .sum();
        System.out.println(sum);
    }

    /**
     * 生成数值范围：
     */
    public void test1() {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        evenNumbers.forEach(System.out::println); // 输出所有1到100之间的偶数
    }

    /**
     * 求和：
     */
    public void test2(){
        int sum = IntStream.of(1, 2, 3, 4, 5).sum();
        System.out.println(sum); // 输出：15
    }

    /**
     * 求平均值：
     */
    public void test3() {
        OptionalDouble average = IntStream.of(1, 2, 3, 4, 5).average();
        System.out.println(average.orElse(0)); // 输出：3.0
    }

    /**
     * 最大值和最小值：
     */
    public void test4() {
        OptionalInt max = IntStream.of(1, 5, 3, 9, 2).max();
        System.out.println(max.orElse(0)); // 输出：9

        OptionalInt min = IntStream.of(1, 5, 3, 9, 2).min();
        System.out.println(min.orElse(0)); // 输出：1
    }

    /**
     * 数据转换：
     */
    public void test5() {
        List<String> numbersAsString = IntStream.rangeClosed(1, 5)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
        System.out.println(numbersAsString); // 输出：["1", "2", "3", "4", "5"]
    }
}